# EasyDoctor - Data Structures-Based Diagnostic System

> Academic project developed for the **Algorithms and Data Structures (AED)** course in pairs as part of the Bachelor's degree in Computer Engineering at **UPB (Universidade Politécnica de Bragança)**.

---

## About the Project
**EasyDoctor** is a console application developed in **Java** whose main pedagogical goal is **implementing data structures and collections from scratch** (without relying on standard Java libraries such as `ArrayList` or `HashMap`).

The program simulates a medical decision-support system, allowing users to load symptoms and illnesses from a text dataset (`Data_EN.txt`), manage custom dynamic collections in memory, and process diagnostic matches based on user input.

---

## Architecture and Implemented Data Structures

The project is organized into two main packages:

### 1. `mycollections` (Core / Data Structures)
Manual implementation of fundamental memory management and storage structures:
- **`SinglyLinkedList`**: Singly linked list with internal node support (`Node`) and a custom iterator (`SLLIterator`).
- **`DoublyLinkedList`**: Doubly linked list that allows traversing the elements in both directions (`DLLIterator`).
- **`SortedDoublyLinkedList`**: Variant of the doubly linked list that automatically keeps elements sorted by insertion/comparison order.
- **`Map`, `AbstractMap` & `LinkedMap`**: Manually implemented key-value associative structure to enable medical data lookups and indexing.

### 2. `principal` (Application Logic)
- **`EasyDoctor.java`**: Main class managing user interaction logic, file parsing, symptom menu display, and information matching for diagnosis.
- **`Main.java`**: Application entry point.
- **`Data_EN.txt`**: Text file (dataset) containing illnesses and their respective symptoms in English.

---

## How to Run the Project

### Prerequisites
- **Java JDK** installed (version 17 or higher recommended).
- **Apache NetBeans IDE** (or another compatible Java editor).

### Steps to Run in NetBeans:
1. Open **Apache NetBeans**.
2. Go to **File** > **Open Project...** and select the project root folder (`trabAED`).
3. In the left panel (*Projects*), expand `src` > `principal` > `Main.java`.
4. Right-click `Main.java` and select **Run File** (or press `Shift + F6`).

---

## Project Structure
```text
trabAED/
├── src/
│   ├── mycollections/       # Data structures implemented from scratch
│   │   ├── AbstractMap.java
│   │   ├── DoublyLinkedList.java
│   │   ├── LinkedMap.java
│   │   ├── Map.java
│   │   ├── SinglyLinkedList.java
│   │   └── SortedDoublyLinkedList.java
│   └── principal/           # Program logic
│       ├── Data_EN.txt
│       ├── EasyDoctor.java
│       └── Main.java
└── README.md
```

## Conclusions
Developing **EasyDoctor** was a foundational step in consolidating software engineering and Java programming principles:

- **Mastery of Dynamic Structures:** Building linked lists and maps without using native Java collections provided a solid understanding of memory references, dynamic node allocation, and pointer management.
- **Iterator Design Pattern & Generics:** Using generic types (`<E>`, `<K, V>`) and implementing custom iterators ensured that collections were reusable for any data type and cleanly with `for-each` loops.
- **Complexity Analysis:** The project enabled practical evaluation of the real performance impact of each operation (e.g., $O(n)$ sequential traversal in linked lists vs. $O(1)$ controlled insertion at extremities).
- **Teamwork and Modularity:** The clear separation between the data structures package (`mycollections`) and the application logic (`principal`) facilitated pair collaboration, allowing each component to be developed and tested independently.
