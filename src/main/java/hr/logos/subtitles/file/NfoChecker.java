package hr.logos.subtitles.file;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.*;
import com.google.inject.Inject;
import hr.logos.subtitles.FileSystemAdapter;
import hr.logos.subtitles.Finder;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author pfh (Kristijan Šarić) ksaric
 */

public class NfoChecker implements Finder<String, String> {

    //    private final Pattern pattern = Pattern.compile( "/imdb\\.[^\\/]+\\/title\\/tt(\\d+)/i", Pattern.CASE_INSENSITIVE );
    private final Pattern pattern = Pattern.compile( "http://(?:www\\.)?imdb.com/title/tt[^/]+/", Pattern.CASE_INSENSITIVE );

    private FileSystemAdapter fileSystemAdapter;

    private String foundNfoInfo = "";

    @Inject
    public NfoChecker( final FileSystemAdapter fileSystemAdapter ) {
        this.fileSystemAdapter = fileSystemAdapter;
    }

    /**
     * Send movie absolute path - c:\movies\Breaking Bad S03E2.avi
     * <p/>
     * Searches for 'Breaking Bad S03E2.nfo' in 'c:\movies\'
     *
     * @param path an absolute file path
     * @return true if file is found and the nfo content can be read
     */

    @Override
    public Boolean find( final String path ) {
        final File file = Preconditions.checkNotNull( new File( path ) );

        final String absolutePathWithoutFile = getPathWithoutFilename( file );

        // get nfo files from path
        ImmutableList<File> nfoFiles = fileSystemAdapter.getNfoFiles( absolutePathWithoutFile );

        // get first!
        final File firstNfoFile = nfoFiles.get( 0 );

        // read from file
        final byte[] readAllBytes = fileSystemAdapter.readAllBytes( firstNfoFile );
        if ( readAllBytes.length == 0 ) return Boolean.FALSE;  // not found, right?

        final String text = new String( readAllBytes, Charsets.UTF_8 );

        final Matcher matcher = pattern.matcher( text );

        if ( matcher.matches() ) {
            foundNfoInfo = matcher.group();
            return Boolean.TRUE;
        } else
            return Boolean.FALSE;
    }

    private String getPathWithoutFilename( File file ) {
        return file.getAbsolutePath().substring(
                0,
                ( file.getAbsolutePath().lastIndexOf( "\\" ) + 1 )
        );
    }

    @Override
    public String getResult() {
        return foundNfoInfo;
    }
}
