# GenericLink: A High-Performance Custom List Implementation

![Java](https://img.shields.io/badge/Language-Java_17+-b07219.svg)
![Build](https://img.shields.io/badge/Build-Maven%2FGradle-blue)
![License](https://img.shields.io/badge/License-MIT-green)

## Project Overview
**GenericLink** is a lightweight, dependency-free implementation of the `java.util.List` interface, built from scratch to demonstrate low-level memory management and pointer arithmetic in Java.

Unlike standard `LinkedList` wrappers, this project implements a custom **Singly Linked List** core. It focuses on optimizing complex set operations (like `removeAll` and `retainAll`) by manipulating node pointers directly, rather than relying on heavy auxiliary collections.

## Key Features

* **Zero Dependencies**: Pure Java implementation. No external libraries used.
* **Type-Safe Generics**: Full support for Java Generics (`<T>`) with robust type erasure handling.
* **Custom Iterator**: Implements `Iterator<T>` pattern from scratch, supporting safe traversal and concurrent modification checks.
* **Branch Coverage Testing**: Comprehensive JUnit 5 test suite covering edge cases (nulls, boundary nodes, single-element lists).

## Algorithmic Highlights

This project solves specific algorithmic challenges in linked structure manipulation:

### 1. Optimized Consecutive Removal (`O(N)`)
Standard implementations often struggle with removing consecutive duplicate elements efficiently.
* **Challenge**: Removing a sequence like `[A, B, B, B, C]` (target: B) usually involves repeated pointer resets.
* **My Solution**: Implemented a **Look-Ahead Algorithm** in the `removeContinueSame` method. It detects a target node and "fast-forwards" the `next` pointer until a non-matching node is found, bridging the gap in a single operation.

### 2. In-Place Set Retention
The `retainAll` method is implemented using an in-place filtering strategy (`retainContinueElement`), minimizing memory overhead by avoiding the creation of temporary buffers during intersection operations.

## Installation & Usage

### Integration
Since this is a standalone library, you can simply include the `src` folder in your project.

### Example Code
```java
import genericlist.ConsListList;

public class Demo {
    public static void main(String[] args) {
        // 1. Initialize with Generics
        ConsListList<String> techStack = new ConsListList<>();

        // 2. Add Elements
        techStack.add("Java");
        techStack.add("Python");
        techStack.add("Java"); // Duplicate

        // 3. Advanced Removal (Custom Logic)
        // This triggers the optimized 'removeContinueSame' logic
        techStack.removeAllElements("Java"); 

        // 4. Iterate
        for (String tech : techStack) {
            System.out.println("Remaining: " + tech); // Output: Python
        }
    }
}