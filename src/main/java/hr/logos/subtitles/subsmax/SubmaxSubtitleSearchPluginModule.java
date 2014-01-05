package hr.logos.subtitles.subsmax;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import hr.logos.subtitles.Finder;

/**
 * @author pfh (Kristijan Šarić)
 */
public class SubmaxSubtitleSearchPluginModule extends AbstractModule {

    private SubmaxSubtitleSearchPluginModule() {
    }

    public static SubmaxSubtitleSearchPluginModule create() {
        return new SubmaxSubtitleSearchPluginModule();
    }

    @Override
    protected void configure() {
        Multibinder<Finder<String, String>> uriBinder = Multibinder.newSetBinder( binder(), new TypeLiteral<Finder<String, String>>() {} );
        uriBinder.addBinding().to( SubsMaxMovieSubtitleFinder.class );
    }
}
