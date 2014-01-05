package hr.logos.subtitles.file;

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

    @Test
    public void testFind() throws Exception {
        //Before
        Finder<String, List<File>> fileFinder = new MovieFilesFinder();

        //When
        final Boolean aBoolean = fileFinder.find( "/home/kristijan/Downloads" );

        //Then
        Assert.assertThat( aBoolean, Matchers.is( Boolean.TRUE ) );

        for ( File file : fileFinder.getResult() ) {
            System.out.println( file.getAbsolutePath() );
        }
    }

    @Test
    public void testGetResult() throws Exception {

        //Before

        //When

        //Then

    }
}
