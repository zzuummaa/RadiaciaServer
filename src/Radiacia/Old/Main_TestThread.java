package Radiacia.Old;

/**
 * Created by Cntgfy on 23.07.2016.
 */
public class Main_TestThread {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new TestThread();
        System.out.println("before start isInterrupted: " + thread.isInterrupted());
        thread.start();
        System.out.println("during start isInterrupted: " + thread.isInterrupted());
        thread.join();
        System.out.println("after stop isInterrupted: " + thread.isInterrupted());
        System.out.println();

        thread = new TestThread();
        System.out.println("before start isAlive: " + thread.isAlive());
        thread.start();
        System.out.println("during start isAlive: " + thread.isAlive());
        thread.join();
        System.out.println("after stop isAlive: " + thread.isAlive());
        System.out.println();
    }

    private static class TestThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        }
    }
}
