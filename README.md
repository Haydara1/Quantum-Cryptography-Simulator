# 🔐 Quantum Cryptography Simulator

A Java-based simulator exploring **quantum cryptography and secure communication**, starting with the BB84 quantum key distribution protocol.

## 🌌 Overview

Quantum cryptography uses the principles of quantum mechanics to establish secure communication. This project simulates the interaction between Alice, Bob, and Eve to explore how quantum key distribution works and how eavesdropping can affect the security of a shared key.

The simulator currently implements **BB84**, one of the foundational protocols in quantum cryptography. It also provides a starting point for exploring additional quantum cryptography protocols in the future.

## ✨ Features

- 🔑 **BB84 Protocol:** Simulate quantum key distribution between Alice and Bob.
- 👩‍💻 **Alice and Bob:** Model the parties attempting to establish a shared secret key.
- 🕵️ **Eve (Eavesdropper):** Simulate an eavesdropper with a configurable interception probability.
- 🧪 **Batch Testing:** Run multiple protocol executions to evaluate the success rate.
- 📊 **Success Rate Analysis:** Measure the percentage of successful quantum key distribution attempts.
- 🚀 **Extensible Architecture:** Designed to grow with the addition of further quantum cryptography protocols.

## 🛠️ Technologies

- ☕ **Java**
- 📦 **Object-oriented programming**
- 🧮 **Monte Carlo-style simulation through repeated protocol executions**

## 🚀 Getting Started

### 📋 Prerequisites

- A Java Development Kit (JDK).
- A Java-compatible IDE, such as IntelliJ IDEA or Eclipse.

### 📥 Installation

Clone the repository:

```bash
git clone https://github.com/Haydara1/Quantum-Cryptography-Simulator.git
```

Open the project in your preferred Java IDE and build it.

### ▶️ Running the Simulator

Run the `main` method in `Protocols.Tests`.

The program will prompt you to enter three parameters:

| Parameter | Description |
|---|---|
| Number of test cases | The number of times to execute the protocol |
| Number of qubits | The number of qubits used in each execution |
| Interception rate | The probability that Eve intercepts a qubit |

For example:

```text
Enter the number of the wanted testcases: 1000
Enter the number of wanted qubits: 100
Enter the intercept rate : 0.5

The quantum key distribution has succeeded with a 73% rate.
```

*The output above is illustrative; actual results depend on the implementation and simulation parameters.*

The simulator then runs the requested number of protocol executions and reports the percentage that succeed.

## 🧠 The BB84 Protocol

BB84, introduced by Charles Bennett and Gilles Brassard in 1984, is a quantum key distribution protocol that uses quantum states to establish a shared secret key.

The simulation models three participants:

- 👩 **Alice:** Sends quantum information to establish a key.
- 👨 **Bob:** Receives and measures the quantum information.
- 🕵️ **Eve:** Attempts to intercept quantum information with a configurable probability.

By repeating the protocol under different conditions, the simulator can be used to investigate how eavesdropping influences the success of quantum key distribution.

## 🔬 Experimenting with the Simulator

Try varying the input parameters to explore different scenarios:

- 🔢 **Increase the number of test cases** to obtain more representative aggregate results.
- ⚛️ **Change the number of qubits** to investigate the effect of the number of transmitted qubits.
- 🕵️ **Adjust Eve's interception rate** to study how eavesdropping affects protocol execution.

These experiments provide a practical introduction to quantum key distribution and the role of quantum measurement in communication security.

## 🗺️ Future Development

This project is a work in progress, and **additional quantum cryptography protocols will be implemented in the future**.

Planned directions include:

- 🔐 **More quantum cryptography protocols:** Extend the simulator beyond BB84.
- 🧪 **Expanded testing:** Compare protocol behavior under different experimental conditions.
- 📈 **Improved analysis:** Add more detailed statistics and visualizations of simulation results.
- 📚 **Better documentation:** Explain the theory and implementation behind each supported protocol.

The long-term goal is to build a collection of quantum cryptography protocol simulations that makes it easier to experiment with and understand quantum communication.

## 🤝 Contributing

Ideas, suggestions, and contributions are welcome! If you're interested in quantum cryptography, quantum computing, or information security, feel free to explore the project and suggest improvements.

## 📜 License

Check the repository for licensing information before reusing or distributing the code.

---

⭐ If you find this project interesting, consider giving the repository a star!
