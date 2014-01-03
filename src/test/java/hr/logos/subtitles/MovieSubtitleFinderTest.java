package hr.logos.subtitles;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.util.Modules;
import hr.logos.subtitles.subsmax.HttpClientSearchGetAdapter;
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
 * @author pfh (Kristijan Šarić)
 */

public class MovieSubtitleFinderTest {

    public static final String AMERICAN_BEAUTY = "American Beauty";
    public static final String GARDEN_STATE = "Garden State";
    public static final String DARK_KNIGHT = "The dark knight 2008";

    //    private HttpClient httpClient = Mockito.mock( HttpClient.class );
    private HttpClientSearchGetAdapter httpClientSearchGetAdapter = Mockito.mock( HttpClientSearchGetAdapter.class );
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
        Mockito.when( httpClientSearchGetAdapter.fetchHttpXml( MovieSubtitleFinder.SUBSMAX_URL + "The-dark-knight-2008-en" ) ).thenReturn( toString );

        //When
        final Boolean isMovieFound = stringFinder.find( DARK_KNIGHT );
        final String result = stringFinder.getResult();

        //Then
        Assert.assertThat( isMovieFound, Matchers.is( Boolean.TRUE ) );
        Assert.assertThat( result, Matchers.not( Matchers.isEmptyOrNullString() ) );
    }

    @Test( expected = IllegalStateException.class )
    public void testFindNull() throws Exception {
        //Before
        Finder<String> stringFinder = injector.getInstance( MovieSubtitleFinder.class );

        //When
        final Boolean isMovieFound = stringFinder.find( null );
    }

    @Test/*( expected = IllegalStateException.class )*/
    public void testFindEmpty() throws Exception {
        //Before
        Finder<String> stringFinder = injector.getInstance( MovieSubtitleFinder.class );

        //When
        try {
            final Boolean isMovieFound = stringFinder.find( "" );
            Assert.fail();
        } catch ( Exception e ) {
            final String message = e.getMessage();
            Assert.assertThat( "Find parameter cannot be NULL or EMPTY.", Matchers.equalTo( message ) );
        }
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

        // Real implementation.
        @Provides
        public Serializer provideSerializer() {
            return new Persister();
        }

        @Provides
        public HttpClientSearchGetAdapter provideHttpClientAdapter() {
            return httpClientSearchGetAdapter;
        }
    }
}
