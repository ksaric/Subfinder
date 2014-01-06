package hr.logos.subtitles.file;

import com.google.common.base.CaseFormat;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.google.common.io.Files;

import java.io.File;

/**
 * @author pfh (Kristijan Šarić)
 */
public class DefaultFileSystemAdapter implements FileSystemAdapter {

    @Override
    public ImmutableList<File> getMovieFiles( final String param ) {
        Preconditions.checkNotNull( param, "Parameter cannot be NULL!" );
        final File searchFolder = Preconditions.checkNotNull( new File( param ) );

        return Files.fileTreeTraverser().preOrderTraversal( searchFolder ).filter( getMoviesFilter() ).toList();
    }

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
