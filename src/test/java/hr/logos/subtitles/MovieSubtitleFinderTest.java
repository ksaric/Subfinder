package hr.logos.subtitles;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.util.Modules;
import hr.logos.subtitles.subsmax.HttpClientAdapter;
import org.apache.http.HttpResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.IOException;

/**
 * @author pfh
 */

public class MovieSubtitleFinderTest {

    public static final String AMERICAN_BEAUTY = "American Beauty";
    public static final String GARDEN_STATE = "Garden State";

    //    private HttpClient httpClient = Mockito.mock( HttpClient.class );
    private HttpClientAdapter httpClientAdapter = Mockito.mock( HttpClientAdapter.class );
    private Serializer serializer = Mockito.mock( Serializer.class );

    private Injector injector;

    @Before
    public void setUp() throws Exception {
        final SubtitleModule subtitleModule = SubtitleModule.create();
        injector = Guice.createInjector( Modules.override( subtitleModule ).with( new CustomTestModule() ) );
    }

    @Test
    public void testFindDarkKnight() throws Exception {
        //Before
        Finder<String> stringFinder = injector.getInstance( MovieSubtitleFinder.class );

        final HttpResponse response = Mockito.mock( HttpResponse.class );

        final String toString = getResourceToString( "the-dark-knight-2008.xml" );
        Mockito.when( httpClientAdapter.fetchHttpXml( Mockito.anyString() ) ).thenReturn( toString );

        //When
        final Boolean isMovieFound = stringFinder.find( "The dark knight 2008" );

        //Then
        Assert.assertThat( isMovieFound, Matchers.is( Boolean.TRUE ) );
    }

    private String getResourceToString( String fileName ) throws IOException {
        final CharSource charSource = Resources.asCharSource(
                Resources.getResource( fileName ), Charsets.UTF_8
        );

        StringBuilder stringBuilder = new StringBuilder();

        for ( String currentLine : charSource.readLines() ) {
            stringBuilder.append( currentLine );
        }


        return stringBuilder.toString();
    }

    public class CustomTestModule extends AbstractModule {
        public CustomTestModule() {
        }

        @Override
        protected void configure() {
        }

        // todo: not mocked!
        @Provides
        public Serializer provideSerializer() {
            return new Persister();
        }

        @Provides
        public HttpClientAdapter provideHttpClientAdapter() {
            return httpClientAdapter;
        }
    }
}
