package hr.logos.subtitles.file;

import com.google.common.base.Predicate;
import hr.logos.subtitles.FileSystemAdapter;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * User: ksaric
 * Date: 07.01.14.
 * Time: 09:59
 * <p/>
 * Neki komentar
 */
public class FileSystemAdapterTest {


    @Test( expected = NullPointerException.class )
    public void testGetMovieFilesNotNull() throws Exception {
        //Before
        FileSystemAdapter fileSystemAdapter = new DefaultFileSystemAdapter();

        //When
        fileSystemAdapter.getMovieFiles( null );

        //Then
        Assert.fail( "Must throw NullPointerException!" );
    }

    @Test
    public void testGetMovieFilesFilterAvi() throws Exception {
        //Before
        DefaultFileSystemAdapter fileSystemAdapter = new DefaultFileSystemAdapter();
        final Predicate<? super File> moviesFilter = fileSystemAdapter.getMoviesFilter();

        //When
        final boolean movie1 = moviesFilter.apply( new File( "SomeMovie.aVi" ) );
        final boolean movie2 = moviesFilter.apply( new File( "some movie.AVI" ) );
        final boolean movie3 = moviesFilter.apply( new File( "c:\\some movie.avi" ) );

        //Then
        Assert.assertTrue( movie1 );
        Assert.assertTrue( movie2 );
        Assert.assertTrue( movie3 );
    }

    @Test
    public void testGetMovieFilesFilterMkv() throws Exception {
        //Before
        DefaultFileSystemAdapter fileSystemAdapter = new DefaultFileSystemAdapter();
        final Predicate<? super File> moviesFilter = fileSystemAdapter.getMoviesFilter();

        //When
        final boolean movie1 = moviesFilter.apply( new File( "SomeMovie.mKv" ) );
        final boolean movie2 = moviesFilter.apply( new File( "some movie.MKV" ) );
        final boolean movie3 = moviesFilter.apply( new File( "c:\\some movie.mkv" ) );

        //Then
        Assert.assertTrue( movie1 );
        Assert.assertTrue( movie2 );
        Assert.assertTrue( movie3 );
    }

    @Test
    public void testGetMovieFilesFilterJpg() throws Exception {
        //Before
        DefaultFileSystemAdapter fileSystemAdapter = new DefaultFileSystemAdapter();
        final Predicate<? super File> moviesFilter = fileSystemAdapter.getMoviesFilter();

        //When
        final boolean movie1 = moviesFilter.apply( new File( "SomeMovie.jPg" ) );
        final boolean movie2 = moviesFilter.apply( new File( "some movie.JPG" ) );
        final boolean movie3 = moviesFilter.apply( new File( "c:\\some movie.jpg" ) );

        //Then
        Assert.assertFalse( movie1 );
        Assert.assertFalse( movie2 );
        Assert.assertFalse( movie3 );
    }
}
