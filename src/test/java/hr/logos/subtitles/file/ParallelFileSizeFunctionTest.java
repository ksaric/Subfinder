package hr.logos.subtitles.file;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pfh (Kristijan Šarić) ksaric
 */

// todo: fix test, bounded
public class ParallelFileSizeFunctionTest {

    public static final String TEST_PATHNAME = "C:\\MinGW";

    public static final String TEST_LARGE_PATHNAME = "C:\\";

    @Test
    public void testCall() throws Exception {
        //Before
//        ExecutorService executorService = Executors.newFixedThreadPool( 5 );
        ExecutorService executorService = Executors.newCachedThreadPool();
        final ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator( executorService );

        final File searchRoot = new File( TEST_PATHNAME );
        ParallelFileSizeFunction parallelFileSizeFunction =
                new ParallelFileSizeFunction( listeningExecutorService, searchRoot );

        //When
        long startTime = System.nanoTime();
        final Long call = parallelFileSizeFunction.call();
        long endTime = System.nanoTime();

        //Then
        System.out.println( "++++" + call + " -- in -- " + ( endTime - startTime ) );
        Assert.assertThat( call, Matchers.equalTo( 288994925l ) );

    }
}
