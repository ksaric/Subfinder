package hr.logos.subtitles.subs.allsubs;

import hr.logos.subtitles.Finder;
import hr.logos.subtitles.HttpClientSearchGetAdapter;
import hr.logos.subtitles.ResourceUtils;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.simpleframework.xml.core.Persister;

/**
 * @author pfh (Kristijan Šarić) ksaric
 */

public class AllSubsSubtitleFinderTest {

    private HttpClientSearchGetAdapter httpClientSearchGetAdapter;

    private String bigBangTheorySubs;

    private Finder<String, String> allSubsSubtitleFinder;

    @Before
    public void setUp() throws Exception {
        httpClientSearchGetAdapter = Mockito.mock( HttpClientSearchGetAdapter.class );
        allSubsSubtitleFinder = new AllSubsSubtitleFinder( httpClientSearchGetAdapter, new Persister() );
        bigBangTheorySubs = ResourceUtils.getResourceToString( "the_big_bang_theory_7_all_subs.xml" );
    }

    @Test
    public void testFindSimple() throws Exception {
        //Before
        Mockito.when( httpClientSearchGetAdapter.
                fetchHttpXml( "http://api.allsubs.org/index.php?search=The+Big+Bang+Theory+7&language=en&limit=3" ) )
                .thenReturn( bigBangTheorySubs );

        //When
        final Boolean isMovieFound = allSubsSubtitleFinder.find( "The Big Bang Theory 7" );

        //Then
        Assert.assertThat( isMovieFound, Matchers.is( true ) );
    }

    @Test
    public void testFindSearchNoise() throws Exception {
        //Before
        Mockito.when( httpClientSearchGetAdapter.
                fetchHttpXml( "http://api.allsubs.org/index.php?search=The+Big+Bang+Theory+7&language=en&limit=3" ) )
                .thenReturn( bigBangTheorySubs );

        //When
        final Boolean isMovieFound = allSubsSubtitleFinder.find( "The+ Big.. Bang-.,.-,-., Theory+++ 7" );

        //Then
        Assert.assertThat( isMovieFound, Matchers.is( true ) );
    }

    @Test
    public void testFindGetResult() throws Exception {
        //Before
        Mockito.when( httpClientSearchGetAdapter.
                fetchHttpXml( "http://api.allsubs.org/index.php?search=The+Big+Bang+Theory+7&language=en&limit=3" ) )
                .thenReturn( bigBangTheorySubs );

        //When
        final Boolean isMovieFound = allSubsSubtitleFinder.find( "The+ Big.. Bang-.,.-,-., Theory+++ 7" );
        final String result = allSubsSubtitleFinder.getResult();

        //Then
        Assert.assertThat( isMovieFound, Matchers.is( true ) );
        Assert.assertThat( result, Matchers.notNullValue() );
    }
}
