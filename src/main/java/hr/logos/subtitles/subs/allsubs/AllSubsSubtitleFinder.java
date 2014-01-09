package hr.logos.subtitles.subs.allsubs;

import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import hr.logos.subtitles.Finder;
import hr.logos.subtitles.HttpClientSearchGetAdapter;
import org.apache.commons.lang3.StringUtils;
import org.simpleframework.xml.Serializer;

/**
 * @author pfh (Kristijan Šarić) ksaric
 *         <p/>
 * @see /http://api.allsubs.org/index.php?search=heroes+season+4&language=en&limit=3
 */

// http://www.allsubs.org/api.php
public class AllSubsSubtitleFinder implements Finder<String, String> {

    enum AvailableLanguages {
        en, sq, ar, bg, zh, hr, cs, da, nl, et, fi, fr, ka, de, el, he, hi, hu, it, ja, ko, lv, lt, mk, no, pl, pt, ro, ru, sk, sl, es, sv, tr, vn
    }

    private final HttpClientSearchGetAdapter httpClientSearchGetAdapter;

    private final Serializer serializer;

    private String subtitleDownloadLink;

    @Inject
    public AllSubsSubtitleFinder(
            final HttpClientSearchGetAdapter httpClientSearchGetAdapter,
            final Serializer serializer
    ) {
        this.httpClientSearchGetAdapter = httpClientSearchGetAdapter;
        this.serializer = serializer;
    }

    @Override
    public Boolean find( final String param ) {

        Preconditions.checkState( !Strings.isNullOrEmpty( param ), "Find parameter cannot be NULL or EMPTY." );
        String movieName = convertToMovieName( param );

        // int?
        String limit = "3";
        String language = AvailableLanguages.en.name();
        String uri = "http://api.allsubs.org/index.php?search=" + movieName + "&language=" + language + "&limit=" + limit;

//        final String httpXmlContent = httpClientSearchGetAdapter.fetchHttpXml( uri );

        try {
            // deserialize the xml
            final String httpResponse = Preconditions.checkNotNull( httpClientSearchGetAdapter.fetchHttpXml( uri ) );

            // javax.xml.stream.XMLStreamException: ParseError
            // check if XML format
            final XmlAllSubsAPIRoot xmlAllSubs = serializer.read( XmlAllSubsAPIRoot.class, httpResponse );

            // must have some subtitles
            if ( xmlAllSubs.getItems() != null && !( xmlAllSubs.getItems().size() > 0 ) )
                return Boolean.FALSE;

            // set it on MAX value so we have an edge value for comparison
            Integer minDistance = Integer.MAX_VALUE;

            findLink( param, xmlAllSubs, minDistance );
        } catch ( Exception e ) {
            e.printStackTrace();
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
//        return !httpXmlContent.isEmpty();
    }

    private void findLink( String param, XmlAllSubsAPIRoot xmlAllSubsAPIRoot, Integer minDistance ) {
        for ( XmlAllSubsAPIItem item : xmlAllSubsAPIRoot.getItems() ) {
            final String filename = item.getFilename();

            final Integer levenshteinDistance = StringUtils.getLevenshteinDistance( param, filename );

            if ( levenshteinDistance < minDistance ) {
                minDistance = levenshteinDistance;
                subtitleDownloadLink = item.getLink();
            }
        }
    }

    // todo : Test this more, make flexibile...
    private String convertToMovieName( String param ) {
        final CharMatcher movieCharMatcher = ( CharMatcher.JAVA_LETTER ).or( CharMatcher.DIGIT ).or( CharMatcher.WHITESPACE );
        return movieCharMatcher.retainFrom( param ).replace( " ", "+" );
    }

    @Override
    public String getResult() {
        return subtitleDownloadLink;
    }
}
