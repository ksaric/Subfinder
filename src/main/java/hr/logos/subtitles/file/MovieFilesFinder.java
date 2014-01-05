package hr.logos.subtitles.file;

import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.google.common.io.Files;
import hr.logos.subtitles.Finder;

import java.io.File;
import java.util.List;

/**
 * @author pfh (Kristijan Šarić)
 */

public class MovieFilesFinder implements Finder<String, List<File>> {

    private List<File> foundFiles = Lists.newArrayList();

    @Override
    public Boolean find( String param ) {
        Preconditions.checkNotNull( param, "Parameter cannot be NULL!" );
        final File searchFolder = Preconditions.checkNotNull( new File( param ) );

        final ImmutableList<File> files = Files.fileTreeTraverser().preOrderTraversal( searchFolder ).filter( getMoviesFilter() ).toList();

        for ( File foundFile : files ) {
            foundFiles.add( foundFile );
        }

        // if something is found...
        return ( foundFiles.size() > 0 );
    }

    @Override
    public List<File> getResult() {
        return foundFiles;
    }

    // SRP violation? KISS for now...
    private final String[] movieFormatList = new String[]{"avi", "mkv"};

    private Predicate<? super File> getMoviesFilter() {
        return new Predicate<File>() {
            @Override
            public boolean apply( File fileName ) {

                if ( !fileName.isFile() ) return false;
                // case matching
                final String fileExtension = CaseFormat.UPPER_UNDERSCORE.to( CaseFormat.LOWER_CAMEL, Files.getFileExtension( fileName.getName() ) );

                return Lists.newArrayList( movieFormatList ).contains( fileExtension );
            }
        };
    }
}
