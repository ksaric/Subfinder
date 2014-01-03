package hr.logos.subtitles;

import com.google.inject.Inject;

import java.io.File;

/**
 * @author pfh (Kristijan Šarić)
 */

public class MovieSubtitleDownloader implements Downloader {

    private final HttpClientDownloadAdapter httpClientDownloadAdapter;

    @Inject
    public MovieSubtitleDownloader(
            final HttpClientDownloadAdapter httpClientDownloadAdapter
    ) {
        this.httpClientDownloadAdapter = httpClientDownloadAdapter;
    }

    @Override
    public Boolean download( String link ) {
        httpClientDownloadAdapter.downloadFile( link );
        return null;
    }

    @Override
    public Boolean download( String link, File pathToDownloadTo ) {
        httpClientDownloadAdapter.downloadFile( link );
        return null;
    }
}