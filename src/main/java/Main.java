import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

import Person.Alice;
import Person.Bob;
import Person.Eve;

import Protocols.BB84;

import Experiment.BB84Result;

public class Main {

    private static final int QUBITS_PER_EXPERIMENT = 10_000;
    private static final int REPETITIONS = 100;

    private static final double ERROR_MARGIN = 0.11;

    private static final double[] EVE_PROBABILITIES = {
        0.0, 0.1, 0.2, 0.3, 0.4,
        0.5, 0.6, 0.7, 0.8, 0.9, 1.0
    };

    public static void main(String[] args) {

        Path outputPath = Path.of(
            "results",
            "bb84_results.csv"
        );

        try {
            Files.createDirectories(outputPath.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(
                    outputPath,
                    StandardCharsets.UTF_8)) {

                // CSV header
                writer.write(
                    "experiment_id,"
                    + "eve_probability,"
                    + "qubits_sent,"
                    + "sifted_bits,"
                    + "sample_bits,"
                    + "errors,"
                    + "qber,"
                    + "theoretical_qber,"
                    + "key_bits,"
                    + "accepted"
                );

                writer.newLine();

                int experimentId = 1;

                for (double probability : EVE_PROBABILITIES) {

                    System.out.println(
                        "Eve probability: " + probability
                    );

                    // One BB84 instance for this probability.
                    BB84 protocol = new BB84(
                        QUBITS_PER_EXPERIMENT,
                        ERROR_MARGIN
                    );

                    for (int repetition = 0;
                         repetition < REPETITIONS;
                         repetition++) {

                        Alice alice = new Alice();
                        Bob bob = new Bob();
                        Eve eve = new Eve(probability);

                        BB84Result result = protocol.runExperiment(
                            alice,
                            bob,
                            eve,
                            experimentId
                        );

                        double theoreticalQber = probability / 4.0;

                        writer.write(String.format(
                            Locale.ROOT,
                            "%d,%.2f,%d,%d,%d,%d,%.6f,%.6f,%d,%b",
                            result.getExperimentId(),
                            result.getEveProbability(),
                            result.getQubitsSent(),
                            result.getSiftedBits(),
                            result.getSampleBits(),
                            result.getErrors(),
                            result.getQber(),
                            theoreticalQber,
                            result.getKeyBits(),
                            result.isAccepted()
                        ));

                        writer.newLine();

                        experimentId++;
                    }

                    writer.flush();
                }
            }

            System.out.println("\nAll experiments completed!");
            System.out.println(
                "CSV saved to: " + outputPath.toAbsolutePath()
            );

        } catch (IOException exception) {
            System.err.println("Failed to write CSV:");
            exception.printStackTrace();
        }
    }
}