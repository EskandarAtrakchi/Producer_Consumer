package com.mycompany.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;

class SynchronizedProducerConsumer {
    private final int CAPACITY = 5;
    private final Queue<Integer> queue = new LinkedList<>();

    // Producer class
    class Producer implements Runnable {
        @Override
        public void run() {
            int value = 0;
            while (true) {
                synchronized (queue) {
                    while (queue.size() == CAPACITY) {
                        try {
                            System.out.println("Queue is full, Producer waiting...");
                            queue.wait(); // Wait until the queue has space
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Produced: " + value);
                    queue.add(value++);
                    queue.notifyAll(); // Notify the consumer that an item is produced
                }

                try {
                    Thread.sleep(500); // Simulate some delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Consumer class
    class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            System.out.println("Queue is empty, Consumer waiting...");
                            queue.wait(); // Wait until the queue has an item
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int value = queue.poll();
                    System.out.println("Consumed: " + value);
                    queue.notifyAll(); // Notify the producer that an item is consumed
                }

                try {
                    Thread.sleep(1000); // Simulate some delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Main method to start the producer and consumer threads
    public static void main(String[] args) {
        SynchronizedProducerConsumer pc = new SynchronizedProducerConsumer();

        Thread producerThread = new Thread(pc.new Producer());
        Thread consumerThread = new Thread(pc.new Consumer());

        // Start both threads
        producerThread.start();
        consumerThread.start();
    }
}
