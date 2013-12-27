package hr.logos.subtitles;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * @author pfh (Kristijan Šarić)
 */

public class Main {

    public static void main( String[] args ) {
        // first args is the movie name...

        Injector injector = Guice.createInjector( SubtitleModule.create() );

        final Application application = injector.getInstance( Application.class );

        application.findSubtitle( "Batman " );
    }
}
