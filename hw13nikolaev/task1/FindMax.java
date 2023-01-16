package hw13nikolaev.task1;

import java.io.IOException;

public class FindMax implements Runnable {
    private int max;
    private int[] array;
    private Thread thread;

    public FindMax(int[] elements) {
        array = elements;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(20);
            int max = array[0];
            for (int j : array) {
                if (max < j) {
                    max = j;
                }
            }
            this.max = max;
        } catch (InterruptedException interruptedException) {
            System.out.println("Прерывание потока 1: " + interruptedException.getMessage());
        }
    }

        public int getMax () {
            return max;
        }

        public Thread getThread () {
            return thread;
        }
    }

