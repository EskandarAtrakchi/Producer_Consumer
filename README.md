## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Producer-Consumer Problem in Java

This Java program demonstrates the **Producer-Consumer problem** using **synchronized** blocks and `wait()`/`notifyAll()` to manage the interaction between producer and consumer threads.

## Problem Overview

In the **Producer-Consumer problem**, a producer generates data and places it into a shared resource (like a queue), while a consumer retrieves and processes the data from the same shared resource. The challenge is to ensure that the producer does not produce when the resource is full, and the consumer does not consume when the resource is empty. Proper synchronization mechanisms are necessary to avoid race conditions.

### Key Concepts:
1. **Shared Resource (Queue)**: A bounded queue where the producer adds elements and the consumer removes them.
2. **Producer**: Adds items to the queue if there is space.
3. **Consumer**: Removes items from the queue if it is not empty.
4. **Synchronization**: Ensures that both threads do not access the shared resource simultaneously in a conflicting manner.

---

## Code Explanation

### Queue: The queue is defined as a LinkedList of integers, and it has a capacity of 5. It acts as the shared resource between the producer and consumer.

### Producer Class:
The producer is responsible for adding items to the queue.
The producer runs in an infinite loop, checking if the queue is full. If the queue is full, it waits (queue.wait()), releasing the lock on the queue until the consumer consumes an item.
Once there is space in the queue, the producer adds an item to the queue and notifies the consumer (queue.notifyAll()).

### Consumer Class:
The consumer is responsible for removing items from the queue.
The consumer runs in an infinite loop, checking if the queue is empty. If the queue is empty, it waits (queue.wait()), releasing the lock on the queue until the producer adds an item.
Once there is an item in the queue, the consumer removes it and notifies the producer (queue.notifyAll()).

### Synchronization:
The synchronized block ensures that only one thread (either the producer or consumer) can modify the queue at a time. This prevents race conditions.
The wait() method causes the producer or consumer to wait until the condition they need (space in the queue or items to consume) is met.
The notifyAll() method wakes up all waiting threads so they can check if their condition has changed (e.g., an item has been consumed, so the producer can produce more).

### Thread.sleep():
Both the producer and consumer have a delay (Thread.sleep()) to simulate some processing time. The producer has a delay of 500 ms, and the consumer has a delay of 1000 ms.