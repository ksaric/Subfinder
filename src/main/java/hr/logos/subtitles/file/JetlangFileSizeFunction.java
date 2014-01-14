package hr.logos.subtitles.file;

import com.google.common.collect.*;
import org.jetlang.channels.Channel;
import org.jetlang.channels.MemoryChannel;
import org.jetlang.core.Callback;
import org.jetlang.fibers.Fiber;
import org.jetlang.fibers.PoolFiberFactory;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author pfh (Kristijan Šarić) ksaric
 */

public class JetlangFileSizeFunction {

    private final AtomicLong folderSize = new AtomicLong( 0 );

    public void test() {
        ExecutorService service = Executors.newCachedThreadPool();
        PoolFiberFactory fact = new PoolFiberFactory( service );

        // create as many fibers as there are cores * 10

        Collection<Fiber> fileSizeFibers = Lists.newArrayList();

        final int fiberCount = Runtime.getRuntime().availableProcessors() * 10;

        System.out.println( fiberCount );

        for ( int counter = 0; counter < fiberCount; counter++ ) {
            final Fiber fiber = fact.create();
            fiber.start();
            fileSizeFibers.add( fiber );
        }



        /*Channel<String> channel = new MemoryChannel<String>();

        final CountDownLatch reset = new CountDownLatch(1);
        Callback<String> runnable = new Callback<String>() {
            public void onMessage(String msg) {
                reset.countDown();
            }
        };
        channel.subscribe(fiber, runnable);
        channel.publish("hello");

        Fiber fiber = new ThreadFiber();
        fiber.start();*/

        Channel<Long> channel = new MemoryChannel<>();

        final CountDownLatch countDownLatch = new CountDownLatch( 4 );

        Callback<Long> onMsg = new Callback<Long>() {
            public void onMessage( Long size ) {
                folderSize.addAndGet( size );
                countDownLatch.countDown();
            }
        };

        for ( Fiber fileSizeFiber : fileSizeFibers ) {
            channel.subscribe( fileSizeFiber, onMsg );
        }

        channel.publish( 1l );
        channel.publish( 2l );
        channel.publish( 3l );
        channel.publish( 4l );


//        ChannelSubscription<Long> sub = new ChannelSubscription<>( fiber, onMsg );
//        channel.subscribe( sub );


        try {
            countDownLatch.await( 5, TimeUnit.SECONDS );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        System.out.println( folderSize.get() );
    }

}
