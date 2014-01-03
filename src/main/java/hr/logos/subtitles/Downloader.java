package hr.logos.subtitles;

import java.io.File;

/**
 * @author pfh (Kristijan Šarić)
 */

public interface Downloader {

    Boolean download( final String link );

    Boolean download( final String link, final File pathToDownloadTo );

}
