package hr.logos.subtitles;

import com.google.inject.Guice;
import com.google.inject.Injector;
import hr.logos.subtitles.subsmax.SubmaxSubtitleSearchPluginModule;
import hr.logos.subtitles.subsmax.SubsMaxModule;

/**
 * @author pfh (Kristijan Šarić)
 */

public class Main {

    public static void main( String[] args ) {
        // first args is the movie name...

        Injector injector = Guice.createInjector( SubtitleModule.create(), SubsMaxModule.create(), SubmaxSubtitleSearchPluginModule.create() );

        final Application application = injector.getInstance( Application.class );

        application.findSubtitle( "Batman " );
    }
}
