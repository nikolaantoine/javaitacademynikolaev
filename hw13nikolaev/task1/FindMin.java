package hw13nikolaev.task1;

public class FindMin implements Runnable {
    private int min;
    private int[] array;
    private Thread thread;


    public FindMin(int [] elements) {
        array = elements;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(20);
            int min = array[0];
            for (int j : array) {
                if (min > j) {
                    min = j;
                }
            }
            this.min = min;
        } catch (InterruptedException interruptedException) {
            System.out.println("Прерывание потока 2: " + interruptedException.getMessage());
        }
    }

    public int getMin() {
        return min;
    }
    public Thread getThread() {
        return thread;
    }

    public int[] getArray() {
        return array;
    }
}