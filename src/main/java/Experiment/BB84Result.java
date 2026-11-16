package Experiment;

public class BB84Result {

	// Attributes
    private final int experimentId; // Unique ID for each experiment
    private final double eveProbability; // Intercepting probability
    private final int qubitsSent; 
    private final int siftedBits;
    private final int sampleBits;
    private final int errors;
    private final double qber;
    private final int keyBits;
    private final boolean accepted; // Checks whether the distribution was a success
    private final String privateKey;


    // Constructor
    public BB84Result(int experimentId, double eveProbability, int qubitsSent,
            int siftedBits, int sampleBits, int errors,
            double qber, int keyBits, boolean accepted, String privateKey) {

        this.experimentId = experimentId;
        this.eveProbability = eveProbability;
        this.qubitsSent = qubitsSent;
        this.siftedBits = siftedBits;
        this.sampleBits = sampleBits;
        this.errors = errors;
        this.qber = qber;
        this.keyBits = keyBits;
        this.accepted = accepted; 
        this.privateKey = privateKey;
    }

    public int getExperimentId() {
        return experimentId;
    }

    public double getEveProbability() {
        return eveProbability;
    }

    public int getQubitsSent() {
        return qubitsSent;
    }

    public int getSiftedBits() {
        return siftedBits;
    }

    public int getSampleBits() {
        return sampleBits;
    }

    public int getErrors() {
        return errors;
    }

    public double getQber() {
        return qber;
    }

    public int getKeyBits() {
        return keyBits;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}