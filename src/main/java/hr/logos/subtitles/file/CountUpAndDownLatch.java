package hr.logos.subtitles.file;

import java.util.concurrent.CountDownLatch;

/**
 * @author pfh (Kristijan Šarić) ksaric
 *         <p/>
 *         stack overflow
 */

public class CountUpAndDownLatch {
    private CountDownLatch latch;

    private final Object lock = new Object();

    public CountUpAndDownLatch( int count ) {
        this.latch = new CountDownLatch( count );
    }

    public void countDownOrWaitIfZero() throws InterruptedException {
        synchronized ( lock ) {
            while ( latch.getCount() == 0 ) {
                lock.wait();
            }

            latch.countDown();
            lock.notifyAll();
        }
    }

    public void waitUntilZero() throws InterruptedException {
        synchronized ( lock ) {
            while ( latch.getCount() != 0 ) {
                lock.wait();
            }
        }
    }

    // right?
    /*public void countDown() {
        synchronized ( lock ) {
            latch.countDown();
            lock.notifyAll();
        }
    }*/

    public void countUp() {
        synchronized ( lock ) {
            latch = new CountDownLatch( (int) latch.getCount() + 1 );
            lock.notifyAll();
        }
    }

    public int getCount() {
        synchronized ( lock ) {
            return (int) latch.getCount();
        }
    }
}
