package hr.logos.subtitles;

import com.google.inject.Inject;

import java.util.Set;

/**
 * @author pfh (Kristijan Šarić)
 */

public class Application {

    private final Set<Finder<String, String>> subtitleFinder;

    @Inject
    public Application( final Set<Finder<String, String>> subtitleFinders ) {
        this.subtitleFinder = subtitleFinders;
    }

    public void findSubtitle( final String subtitle ) {
        for ( Finder<String, String> finder : subtitleFinder ) {
            finder.find( subtitle );
        }

        // download them
    }
}
