package hr.logos.subtitles.file;

import com.google.common.collect.*;
import com.google.inject.Inject;
import hr.logos.subtitles.Finder;

import java.io.File;
import java.util.List;

/**
 * @author pfh (Kristijan Šarić)
 */

public class MovieFilesFinder implements Finder<String, List<File>> {

    private List<File> foundFiles = Lists.newArrayList();
    private final FileSystemAdapter fileSystemAdapter;

    @Inject
    public MovieFilesFinder( final FileSystemAdapter fileSystemAdapter ) {
        this.fileSystemAdapter = fileSystemAdapter;
    }

    @Override
    public Boolean find( final String param ) {

        for ( File foundFile : fileSystemAdapter.getMovieFiles( param ) ) {
            foundFiles.add( foundFile );
        }

        // if something is found...
        return ( foundFiles.size() > 0 );
    }

    @Override
    public List<File> getResult() {
        return foundFiles;
    }
}
