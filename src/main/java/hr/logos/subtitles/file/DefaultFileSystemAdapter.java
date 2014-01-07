package hr.logos.subtitles.file;

import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.google.common.io.Files;
import hr.logos.subtitles.FileSystemAdapter;

import java.io.File;

/**
 * @author pfh (Kristijan Šarić)
 */

public class DefaultFileSystemAdapter implements FileSystemAdapter {

    @Override
    public ImmutableList<File> getMovieFiles( final String param ) {
        final File searchFolder = Preconditions.checkNotNull( new File( param ) );

        final FluentIterable<File> files = Files.fileTreeTraverser().preOrderTraversal( searchFolder );

        return files.filter( getMoviesFilter() ).toList();
    }

    // SRP!
    private final String[] movieFormatList = new String[]{"avi", "mkv"};

    Predicate<? super File> getMoviesFilter() {
        return new Predicate<File>() {
            @Override
            public boolean apply( File fileName ) {
//                if ( !fileName.isFile() ) return false;
                // case matching
                final String fileExtension = CaseFormat.UPPER_UNDERSCORE.to( CaseFormat.LOWER_CAMEL, Files.getFileExtension( fileName.getName() ) );

                return Lists.newArrayList( movieFormatList ).contains( fileExtension );
            }
        };
    }
}
