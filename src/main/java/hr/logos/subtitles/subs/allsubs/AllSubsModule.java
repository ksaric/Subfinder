package hr.logos.subtitles.subs.allsubs;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import hr.logos.subtitles.Finder;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author pfh (Kristijan Šarić)
 */

public class AllSubsModule extends AbstractModule {

    private AllSubsModule() {
    }

    public static AllSubsModule create() {
        return new AllSubsModule();
    }

    @Override
    protected void configure() {
        bind( HttpClient.class ).to( DefaultHttpClient.class );
        Multibinder<Finder<String, String>> uriBinder = Multibinder.newSetBinder( binder(), new TypeLiteral<Finder<String, String>>() {} );
        uriBinder.addBinding().to( AllSubsSubtitleFinder.class );
    }

    @Provides
    public HttpPost provideHttpPost() {
        return new HttpPost();
    }
}
