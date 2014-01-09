package hr.logos.subtitles;

import com.google.inject.AbstractModule;
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
        bind( Serializer.class ).to( Persister.class );
        bind( HttpClientSearchGetAdapter.class ).to( DefaultHttpClientSearchGetAdapter.class );
    }
}
