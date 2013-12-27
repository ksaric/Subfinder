package hr.logos.subtitles.subsmax;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author pfh (Kristijan Šarić)
 */

public class SubsMaxModule extends AbstractModule {

    private SubsMaxModule() {
    }

    public static SubsMaxModule create() {
        return new SubsMaxModule();
    }

    @Override
    protected void configure() {
        bind( HttpClient.class ).to( DefaultHttpClient.class );
    }

    @Provides
    public HttpPost provideHttpPost() {
        return new HttpPost();
    }
}
