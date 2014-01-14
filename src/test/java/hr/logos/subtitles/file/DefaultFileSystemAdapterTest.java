package hr.logos.subtitles.file;

import com.google.common.collect.*;
import hr.logos.subtitles.FileSystemAdapter;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * @author pfh (Kristijan Šarić) ksaric
 */

public class DefaultFileSystemAdapterTest {
    @Test
    public void testGetMovieFiles() throws Exception {
        //Before
        FileSystemAdapter fileSystemAdapter = new DefaultFileSystemAdapter();

        //When
        final ImmutableList<File> movieFiles = fileSystemAdapter.getMovieFiles( "E:\\Filmovi" );

        //Then
        File file = new File( "E:\\Filmovi\\Thor[2011]DvDrip[Eng]-FXG\\Thor[2011]DvDrip[Eng]-FXG.avi" );
        Assert.assertThat( movieFiles, Matchers.contains( file ) );

    }
}
