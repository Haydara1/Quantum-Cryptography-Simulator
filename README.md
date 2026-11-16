# 🔐 Quantum Cryptography Simulator

**An educational Java simulator exploring quantum cryptography, quantum key distribution, and the effects of eavesdropping on secure communication.**

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Python](https://img.shields.io/badge/Python-3776AB?style=for-the-badge&logo=python&logoColor=white)](https://www.python.org/)
[![Jupyter](https://img.shields.io/badge/Jupyter-F37626?style=for-the-badge&logo=jupyter&logoColor=white)](https://jupyter.org/)

---

## 📖 Table of Contents

- [Overview](#-overview)
- [The BB84 Protocol](#-the-bb84-protocol)
  - [The Participants](#the-participants)
  - [How It Works](#how-it-works)
  - [The Intercept-Resend Attack](#the-intercept-resend-attack)
- [Features](#-features)
- [Project Architecture](#-project-architecture)
- [Technologies](#-technologies)
- [Getting Started](#-getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Tests](#running-the-tests)
  - [Running the Simulator](#running-the-simulator)
- [Experimental Analysis](#-experimental-analysis)
  - [Data Collection](#data-collection)
  - [Python Analysis](#python-analysis)
  - [Theoretical Prediction](#theoretical-prediction)
- [Documentation and Resources](#-documentation-and-resources)
- [What I Learned](#-what-i-learned)
- [Limitations](#-limitations)
- [Future Improvements](#-future-improvements)

---

## 🌌 Overview

Quantum cryptography explores how the principles of quantum mechanics can be used to establish secure communication.

Unlike classical cryptography, quantum cryptography can use fundamental physical properties of quantum systems to detect certain forms of eavesdropping.

This project aims to explore these ideas through programming.

The simulator currently focuses on **BB84**, one of the first and most well-known quantum key distribution (QKD) protocols. It models the communication between Alice and Bob, while introducing Eve, an eavesdropper who can intercept and measure transmitted qubits.

The project also explores the statistical behaviour of the protocol through repeated simulations. Experimental results can be exported to CSV and analysed using Python, NumPy, pandas, and Matplotlib.

The main goals are:

- Understand the mathematical foundations of quantum key distribution.
- Translate quantum concepts into an object-oriented Java implementation.
- Simulate the effects of eavesdropping.
- Test the implementation through unit tests.
- Compare experimental results with theoretical predictions.
- Practise scientific computing and statistical data analysis.

## ⚛️ The BB84 Protocol

BB84 was introduced in 1984 by Charles Bennett and Gilles Brassard. It allows two parties to establish a shared secret key using quantum states and a public classical communication channel.

### The Participants

The simulation models three participants:

| Participant | Role |
|---|---|
| **Alice** | Generates and sends quantum bits (qubits). |
| **Bob** | Receives and measures the transmitted qubits. |
| **Eve** | Attempts to intercept and measure the qubits. |

### How It Works

The simplified BB84 protocol follows these steps:

1. **State preparation:** Alice generates random bits and randomly chooses a basis for each qubit.
2. **Quantum transmission:** Alice sends the qubits to Bob through a quantum channel.
3. **Measurement:** Bob independently chooses random measurement bases and measures the received qubits.
4. **Basis reconciliation:** Alice and Bob publicly compare their bases, keeping only the positions where their choices match.
5. **Error estimation:** They compare a sample of the sifted bits to estimate the quantum bit error rate (QBER).
6. **Key generation:** If the estimated error rate is below a chosen threshold, the remaining bits can be retained as potential key material.

### The Two Measurement Bases

The simplified implementation uses two bases:

- **Rectilinear basis (+):** The computational basis, represented by $\lvert 0 \rangle$ and $\lvert 1 \rangle$
- **Diagonal basis (×):** The diagonal basis, represented by $\lvert + \rangle$ and $\lvert - \rangle$.

A qubit measured in the same basis in which it was prepared gives the original bit with certainty in this idealized model.

A measurement in the other basis produces a random result.

This property is central to BB84 and is what makes certain eavesdropping attempts detectable.

### The Intercept-Resend Attack

Eve can attempt to intercept a qubit, measure it using a randomly selected basis, and resend a newly prepared qubit to Bob.

If Eve chooses the wrong basis, her measurement disturbs the quantum information.

When Alice and Bob later compare their matching-basis measurements, this disturbance can appear as an increased error rate.

For an ideal intercept-resend attack, the expected QBER among sifted bits is:

$$
QBER = \frac{p_{\mathrm{Eve}}}{4}
$$

where $p_{\mathrm{Eve}}$ is the probability that Eve intercepts a transmitted qubit.

The reasoning is:

- Eve intercepts a qubit with probability $p_{\mathrm{Eve}}$.
- She chooses the wrong basis with probability $1/2$.
- Bob obtains an incorrect bit with probability $1/2$ in that case.

Therefore:

$$
QBER = p_{\mathrm{Eve}} \times \frac12 \times \frac12
$$

This theoretical prediction provides a useful reference for validating the simulation.

## ✨ Features

- **BB84 simulation:** Model the preparation, transmission, measurement, and sifting stages of quantum key distribution.
- **Three participants:** Represent Alice, Bob, and Eve as separate Java classes.
- **Configurable eavesdropping:** Control Eve's interception probability.
- **QBER estimation:** Estimate the error rate using a sample of sifted bits.
- **Key acceptance:** Decide whether to accept or reject a potential key based on an error threshold.
- **Unit testing:** Test the behaviour of the fundamental components.
- **Experimental data:** Collect simulation results for statistical analysis.
- **Scientific visualizations:** Compare experimental observations with theoretical predictions using Python.

## 🏗️ Project Architecture

The Java implementation follows an object-oriented structure, separating participants, quantum concepts, and cryptographic protocols.

```text
Quantum-Cryptography-Simulator/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Person/
│   │       │   ├── Participant.java
│   │       │   ├── Alice.java
│   │       │   ├── Bob.java
│   │       │   └── Eve.java
│   │       │
│   │       ├── Protocols/
│   │       │   ├── Protocol.java
│   │       │   └── BB84.java
│   │       │
│   │       └── Quantum/
│   │           ├── Qubit.java
│   │           ├── Basis.java
│   │           ├── Communication.java
│   │           └── Util.java
│   │
│   └── test/
│       └── java/
│           ├── Person/
│           └── Quantum/
│
├── notebooks/
│   └── BB84_Experimental_Analysis.ipynb
│
├── results/
│   └── bb84_results.csv
│
├── pom.xml
├── mvnw
└── README.md
```

The structure is designed to make it possible to introduce additional protocols without mixing their implementation with the underlying quantum components.

## 🛠️ Technologies

| Technology | Purpose |
|---|---|
| Java | Implementation of the quantum cryptography simulator |
| Maven | Project build and dependency management |
| JUnit 5 | Unit testing |
| Python | Experimental data analysis |
| NumPy | Numerical computations |
| pandas | CSV processing and data manipulation |
| Matplotlib | Scientific visualizations |
| Jupyter Notebook | Interactive experimental analysis |
| Quarto | Project documentation and scientific writing |

## 🚀 Getting Started

### Prerequisites

You will need:

- A Java Development Kit (JDK).
- Git.
- Python 3 (for the experimental analysis).
- Jupyter Notebook or JupyterLab (for the notebook).

Maven is not required if you use the included Maven Wrapper.

### Installation

Clone the repository:

```bash
git clone https://github.com/Haydara1/Quantum-Cryptography-Simulator.git

cd Quantum-Cryptography-Simulator
```

Compile the Java project:

```bash
./mvnw compile
```

### Running the Tests

Run the unit tests using:

```bash
./mvnw test
```

The tests cover fundamental behaviours such as:

- Qubit measurement.
- Quantum communication.
- Participant behaviour.
- Eve's interception behaviour.

### Running the Simulator

Run the relevant Java entry point from your IDE or through Maven.

For the experimental batch runner, once configured, the command is:

```bash
./mvnw compile exec:java -Dexec.mainClass="Experiments.Main"
```

The batch runner is intended to execute repeated BB84 simulations under different interception probabilities and export the results to:

```text
results/bb84_results.csv
```

## 📊 Experimental Analysis

A major part of this project is investigating whether the simulation reproduces the theoretical behaviour of BB84.

Instead of relying on a single execution, the simulator can run many experiments under different conditions.

### Data Collection

Each experiment records information such as:

| Column | Description |
|---|---|
| `experiment_id` | Unique identifier of the experiment |
| `eve_probability` | Probability of Eve intercepting a qubit |
| `qubits_sent` | Number of qubits sent by Alice |
| `sifted_bits` | Number of bits retained after basis reconciliation |
| `sample_bits` | Number of bits used for error estimation |
| `errors` | Number of errors found in the sample |
| `qber` | Experimentally measured quantum bit error rate |
| `theoretical_qber` | Theoretical QBER, calculated as \(p_{\mathrm{Eve}}/4\) |
| `key_bits` | Number of remaining potential key bits |
| `accepted` | Whether the protocol accepts the potential key |

### Python Analysis

The exported CSV file can be analysed using the Jupyter notebook.

Install the required Python packages:

```bash
python3 -m pip install numpy pandas matplotlib seaborn jupyter
```

Launch Jupyter:

```bash
jupyter notebook
```

Open:

```text
notebooks/BB84_Experimental_Analysis.ipynb
```

The notebook explores the experimental data through several visualizations.

**1. Experimental vs theoretical QBER**

Compare the measured error rate with the theoretical prediction:

$$
QBER = \frac{p_{\mathrm{Eve}}}{4}
$$

**2. Protocol acceptance rate**

Investigate how frequently the protocol accepts a potential key as Eve's interception probability increases.

$$
P(\mathrm{accept}) =
\frac{\text{Accepted experiments}}{\text{Total experiments}}
$$

**3. QBER distribution**

Study the statistical fluctuations between individual experiments using box plots.

**4. Potential key length**

Investigate how many bits remain after basis reconciliation and error sampling.

**5. Deviation from theory**

Measure the difference between the experimental and theoretical QBER to evaluate the accuracy of the simulation.

These experiments demonstrate how repeated simulations can be used to investigate the statistical behaviour of a quantum protocol.

### Theoretical Prediction

For the ideal intercept-resend attack:

$$
QBER = \frac{p_{\mathrm{Eve}}}{4}
$$

For example:

| Eve interception probability | Expected QBER |
|---:|---:|
| 0% | 0% |
| 20% | 5% |
| 40% | 10% |
| 60% | 15% |
| 80% | 20% |
| 100% | 25% |

The experimental results should fluctuate around these expected values due to the finite number of simulated qubits.

## 📚 Documentation and Resources

The project is accompanied by additional material explaining the implementation and the experimental methodology.

- **📖 Project blog:** [Read the complete project article](YOUR_QUARTO_BLOG_LINK)
- **📓 Jupyter notebook:** [Explore the experimental analysis](YOUR_JUPYTER_NOTEBOOK_LINK)
- **💻 Source code:** [Browse the repository](https://github.com/Haydara1/Quantum-Cryptography-Simulator)

The blog explains the motivation, theoretical foundations, implementation choices, and lessons learned.

The notebook focuses on the experimental data, statistical analysis, and visualizations.

## 🧠 What I Learned

Developing this project allowed me to explore several areas at the intersection of mathematics, physics, and computer science.

**Quantum information**

- The representation of qubits and measurement bases.
- The probabilistic nature of quantum measurements.
- How measurement disturbance can reveal certain eavesdropping attempts.
- The basic principles behind quantum key distribution.

**Software engineering**

- Designing an object-oriented simulation.
- Separating responsibilities between classes.
- Writing unit tests with JUnit.
- Structuring a Java project with Maven.

**Probability and statistics**

- Understanding the probabilistic behaviour of intercept-resend attacks.
- Deriving and testing the theoretical QBER.
- Using repeated experiments to estimate statistical quantities.
- Comparing theoretical predictions with empirical observations.

**Scientific computing**

- Exporting simulation results to CSV.
- Processing experimental data with pandas and NumPy.
- Creating visualizations with Matplotlib.
- Presenting computational experiments in Jupyter and Quarto.

## ⚠️ Limitations

This project is an educational simulation, not a production-ready quantum key distribution system.

The current model makes simplifying assumptions:

- Qubits are represented using classical bit and basis information rather than a full quantum state-vector simulator.
- Quantum channels are idealized.
- Noise and hardware imperfections are not comprehensively modelled.
- The eavesdropping model focuses on intercept-resend attacks.
- The simulation does not implement the complete security workflow of practical QKD, including authenticated classical communication, error correction, privacy amplification, or finite-key security proofs.

The theoretical results should therefore be interpreted within the assumptions of this simplified model.

## 🔭 Future Improvements

There are several directions in which this project could evolve:

- **Additional protocols:** Implement other quantum cryptography protocols, such as B92 and E91.
- **Improved quantum modelling:** Introduce state vectors, probability amplitudes, and quantum operators.
- **More attack models:** Explore other eavesdropping strategies.
- **Channel noise:** Simulate imperfect transmission and measurement errors.
- **Reproducibility:** Introduce configurable random number generators and seeds.
- **Advanced statistics:** Add confidence intervals and more extensive Monte Carlo experiments.
- **Security procedures:** Explore error correction and privacy amplification.
- **Interactive interface:** Build a graphical interface to experiment with protocol parameters.

---

## 🤝 Contributing

Suggestions, questions, and contributions are welcome.

If you are interested in quantum computing, quantum information, cryptography, or scientific programming, feel free to explore the project and open an issue.

---

**If you find this project interesting, consider giving the repository a ⭐!**

