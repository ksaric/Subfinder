package hr.logos.subtitles;

import java.io.InputStream;

/**
 * @author pfh (Kristijan Šarić)
 */

public interface HttpClientDownloadAdapter {

    InputStream downloadFile( String uri );

}
