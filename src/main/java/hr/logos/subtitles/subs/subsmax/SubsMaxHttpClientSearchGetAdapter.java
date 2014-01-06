package hr.logos.subtitles.subs.subsmax;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;
import com.google.inject.Inject;
import hr.logos.subtitles.HttpClientSearchGetAdapter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import javax.inject.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * @author pfh (Kristijan Šarić)
 */

public class SubsMaxHttpClientSearchGetAdapter implements HttpClientSearchGetAdapter {

    private final HttpClient httpClient;
    private final Provider<HttpPost> httpPostProvider;

    @Inject
    public SubsMaxHttpClientSearchGetAdapter(
            final HttpClient httpClient,
            final Provider<HttpPost> httpPostProvider
    ) {
        this.httpClient = httpClient;
        this.httpPostProvider = httpPostProvider;
    }

    @Override
    public String fetchHttpXml( String uri ) {

        HttpResponse response = null;

        // provide it
        final HttpPost httPostRequest = httpPostProvider.get();
        httPostRequest.setURI( URI.create( uri ) );
        String source = "";


        try {
            // execute
            response = httpClient.execute( httPostRequest );

            try ( final InputStream inputStream = response.getEntity().getContent() ) {
                source = streamToString( inputStream );
            }

            // clear the entity
            EntityUtils.consume( httPostRequest.getEntity() );

        } catch ( IOException e ) {
            e.printStackTrace();
        }

        return source;
    }

    private String streamToString( final InputStream finalSource ) throws IOException {
        InputSupplier<? extends InputStream> supplier = new InputSupplier<InputStream>() {
            @Override
            public InputStream getInput() throws IOException {
                return finalSource;
            }
        };

        InputSupplier<InputStreamReader> readerSupplier = CharStreams.newReaderSupplier( supplier, Charsets.UTF_8 );

        final String resultContent = CharStreams.toString( readerSupplier );

        return resultContent;
    }
}
