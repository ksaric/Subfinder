package hr.logos.subtitles;

import com.google.common.collect.*;

import java.io.File;

/**
 * @author pfh (Kristijan Šarić)
 */
public interface FileSystemAdapter extends FileAdapterByteReadable {

    ImmutableList<File> getMovieFiles( final String param );

    ImmutableList<File> getNfoFiles( String param );
}
