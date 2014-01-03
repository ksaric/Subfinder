package hr.logos.subtitles;

import com.google.inject.AbstractModule;
import hr.logos.subtitles.subsmax.SubsMaxHttpClientSearchGetAdapter;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * @author pfh (Kristijan Šarić)
 */

public class SubtitleModule extends AbstractModule {
    private SubtitleModule() {
    }

    public static SubtitleModule create() {
        return new SubtitleModule();
    }

    @Override
    protected void configure() {
//        install( SubsMaxModule.create() );

        // forcing architecture!
//        requireBinding( HttpClient.class );
//        requireBinding( HttpPost.class );

        bind( Serializer.class ).to( Persister.class );             // todo : read-only
        bind( HttpClientSearchGetAdapter.class ).to( SubsMaxHttpClientSearchGetAdapter.class );

//        bind( new TypeLiteral<Finder<String>>() {
//        } ).annotatedWith( SubsMaxFinder.class ).to( SubsMaxMovieSubtitleFinder.class );
    }
}
