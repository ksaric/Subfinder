package hr.logos.subtitles.file;

import com.google.common.collect.*;
import hr.logos.subtitles.Finder;

import java.io.File;
import java.util.List;

/**
 * @author pfh (Kristijan Šarić)
 */

public class FileStructureFinder implements Finder<List<File>> {


    @Override
    public Boolean find( List<File> searchList ) {
        return null;
    }

    @Override
    public List<File> getResult() {
        return null;
    }

    public static List<File> adaptPath( final File path ) {
        return Lists.newArrayList( path );
    }

    public static List<File> adaptPath( final String path ) {
        final File file = new File( path );
        return Lists.newArrayList( file );
    }
}
