package hr.logos.subtitles.file;

import com.google.common.collect.*;
import hr.logos.subtitles.Finder;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author pfh (Kristijan Šarić)
 */

public class MovieFilesFinderTest {

    final List<File> foundFiles = Lists.newArrayList(
            new File( "/blabla/movie1.mkv" ),
            new File( "/blabla/movie2.avi" ),
            new File( "/blabla/image.bmp" ),
            new File( "/blabla/movie3.AVI" )
    );

    @Test
    public void testFind() throws Exception {
        //Before
        final FileSystemAdapter fileSystemAdapter = new FileSystemAdapter() {
            @Override
            public ImmutableList<File> getMovieFiles( String param ) {
                return ImmutableList.copyOf( foundFiles );
            }
        };

        Finder<String, List<File>> fileFinder = new MovieFilesFinder( fileSystemAdapter );

        //When
        final Boolean aBoolean = fileFinder.find( "/home/kristijan/Downloads" );
        final List<File> result = fileFinder.getResult();

        //Then
        Assert.assertThat( aBoolean, Matchers.is( Boolean.TRUE ) );
        Assert.assertThat( result, Matchers.equalTo( foundFiles ) );

    }

    @Test
    public void testGetResult() throws Exception {

        //Before

        //When

        //Then

    }
}
