package hr.logos.subtitles;

import hr.logos.subtitles.subsmax.HttpClientDownloadAdapter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author pfh (Kristijan Šarić)
 */

public class MovieSubtitleDownloaderTest {

    private HttpClientDownloadAdapter httpClientDownloadAdapter;

    @Before
    public void setUp() throws Exception {
        httpClientDownloadAdapter = Mockito.mock( HttpClientDownloadAdapter.class );
    }

    @Test
    public void testDownload() throws Exception {
        //Before
        Downloader downloader = new MovieSubtitleDownloader( httpClientDownloadAdapter );

        //When
        downloader.download( "http://google.com" );

        //Then
        Mockito.verify( httpClientDownloadAdapter ).downloadFile( "http://google.com" );
    }


}
