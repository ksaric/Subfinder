package hr.logos.subtitles.file;

import com.google.common.base.Preconditions;
import com.google.common.collect.*;
import com.google.common.io.Files;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author pfh (Kristijan Šarić) ksaric
 */

public class JetlangFileSizeFunctionTest {

    public static final String TEST_PATHNAME = "C:\\MinGW";

    @Test
    public void testTest() throws Exception {
        //Before

        // prepare the thread pool
        ExecutorService service = Executors.newCachedThreadPool();

        // list of files is required to be able to run this in parallel since Coordinator should be immutable...
        final File searchFolder = Preconditions.checkNotNull( new File( TEST_PATHNAME ) );
        final FluentIterable<File> files = Files.fileTreeTraverser().preOrderTraversal( searchFolder );
        final ImmutableSet<File> foundFiles = files.toSet();

        for ( File foundFile : foundFiles ) {
            // create actor for determening if file contains the searched string...
        }
        /*return files.filter( new Predicate<File>() {
            @Override
            public boolean apply( File input ) {
                return input.
            }
        } ).toList();*/


        JetlangFileSizeFunction jetlangFileSizeFunction = new JetlangFileSizeFunction( service );

        //When
        jetlangFileSizeFunction.searchInFiles( new File( TEST_PATHNAME ), "string" );

        //Then

    }
}
