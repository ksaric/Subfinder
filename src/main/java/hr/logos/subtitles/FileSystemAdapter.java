package hr.logos.subtitles;

import com.google.common.collect.*;

import java.io.File;

/**
 * @author pfh (Kristijan Šarić)
 */
public interface FileSystemAdapter {

    ImmutableList<File> getMovieFiles( final String param );

}
