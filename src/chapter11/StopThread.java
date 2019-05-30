package chapter11;

import java.util.concurrent.TimeUnit;

public class StopThread {

    private static boolean stopRequest = false;

    /**
     * 写方法同步
     */
    private static synchronized void requestStop() {
        stopRequest = true;
    }

    private static synchronized boolean stopRequested() {
        return stopRequest;
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            int i = 0;

            @Override
            public void run() {
                while (!stopRequested()) {
                    i++;
                    System.out.println("i=" + i);
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep((long)0.001);
        requestStop();
    }
}
