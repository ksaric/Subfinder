package hr.logos.subtitles.file;

import com.google.common.collect.*;
import hr.logos.subtitles.FileSystemAdapter;
import hr.logos.subtitles.Finder;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * @author pfh (Kristijan Šarić) ksaric
 */

public class NfoCheckerTest {

    private Finder<String, String> nfoChecker;

    @Test
    public void testFind() throws Exception {
        //Before
        final List<File> files = Lists.newArrayList();

        files.add( new File( "c:\\movies\\Superman.nfo" ) );
        files.add( new File( "c:\\movies\\Superman2.nfo" ) );

        final FileSystemAdapter fileSystemAdapter = new FileSystemAdapter() {
            @Override
            public ImmutableList<File> getMovieFiles( String param ) {
                throw new RuntimeException( "This is not the focus. READ the code." );
            }

            @Override
            public ImmutableList<File> getNfoFiles( String param ) {
                return ImmutableList.copyOf( files );
            }

            @Override
            public byte[] readAllBytes( File firstNfoFile ) {
                final String name = firstNfoFile.getName();
                switch ( name ) {
                    case "Superman2.nfo":
                        return "This is some content\n".getBytes();
                    case "Superman.nfo":
                        return "http://www.imdb.com/title/tt0078346/".getBytes();
                    default:
                        throw new RuntimeException( "No match!" );
                }
            }
        };

        nfoChecker = new NfoChecker( fileSystemAdapter );

        //When
        nfoChecker.find( "c:\\movies\\Superman.avi" );
        final String result = nfoChecker.getResult();

        //Then
        Assert.assertThat( result, Matchers.equalTo( "http://www.imdb.com/title/tt0078346/" ) );

    }
}
