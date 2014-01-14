package hr.logos.subtitles.file;

import com.google.common.base.Preconditions;
import com.google.common.collect.*;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author pfh (Kristijan Šarić) ksaric
 */

public class ParallelFileSizeFunction implements Callable<Long> {

    private final ListeningExecutorService listeningExecutorService;

    private final File root;

    public ParallelFileSizeFunction(
            final ListeningExecutorService listeningExecutorService,
            final File root
    ) {
        this.listeningExecutorService = listeningExecutorService;
        this.root = root;
    }

    @Override
    public Long call() throws Exception {
        Long fileTreeSize = 0l;

        Collection<Callable<Long>> futureTasks = Lists.newArrayList();

        // should contain some performance loss, since threads are not immediately scheduled...
        for ( File currentFile : Preconditions.checkNotNull( root.listFiles() ) ) {
            if ( currentFile.isFile() ) {
                fileTreeSize += ( currentFile.length() );
            } else if ( currentFile.isDirectory() ) {
                final ParallelFileSizeFunction parallelFileSizeFunction = new ParallelFileSizeFunction(
                        listeningExecutorService,
                        currentFile
                );

                futureTasks.add( parallelFileSizeFunction );
            } else {
                throw new RuntimeException( "What else could it be ?" );
            }
        }

        final Collection<ListenableFuture<Long>> listenableFutures = Lists.newArrayList();

        // now run the collection, some time lost on coordination...
        for ( Callable<Long> futureTask : futureTasks ) {
            ListenableFuture<Long> listenableFuture = listeningExecutorService.submit( futureTask );
            listenableFutures.add( listenableFuture );
        }

        // wait for result, 20 sec should be enough...
        for ( ListenableFuture<Long> listenableFuture : listenableFutures ) {
            final Long fileSize = listenableFuture.get( 20, TimeUnit.SECONDS );
            fileTreeSize += ( fileSize );
        }

        return fileTreeSize;
    }
}
