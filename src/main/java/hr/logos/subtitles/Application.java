package hr.logos.subtitles;

import com.google.inject.Inject;

/**
 * @author pfh (Kristijan Šarić)
 */

public class Application {

    private final Finder<String> subtitleFinder;

    @Inject
    public Application( @SubsMaxFinder final Finder<String> subtitleFinder ) {
        this.subtitleFinder = subtitleFinder;
    }

    public void findSubtitle( final String subtitle ) {
        subtitleFinder.find( subtitle );
    }
}
