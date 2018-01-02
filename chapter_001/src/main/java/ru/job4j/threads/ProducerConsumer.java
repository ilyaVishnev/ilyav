package ru.job4j.threads;

public class ProducerConsumer {


    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        producer.start();
        consumer.start();
    }
}

class Store {
    int product = 0;

    public synchronized void get() {
        while (product < 1) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        product--;
        System.out.println("Consumer took product");
        System.out.println("remain " + product + " product");
        notify();
    }

    public synchronized void put() {
        while (product > 6) {
            try {
                wait();
            } catch (InterruptedException ex) {
            }
        }
        product++;
        System.out.println("Producer put product");
        System.out.println("remain " + product + " product");
        notify();
    }
}

class Consumer extends Thread {
    Store store;

    Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++)
            store.get();
    }
}

class Producer extends Thread {
    Store store;

    Producer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        for (int i = 0; i < 4; i++)
            store.put();
    }
}
