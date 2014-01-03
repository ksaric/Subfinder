package hr.logos.subtitles.subsmax;

import java.io.InputStream;

/**
 * @author pfh (Kristijan Šarić)
 */

public interface HttpClientDownloadAdapter {

    InputStream downloadFile( String uri );

}
