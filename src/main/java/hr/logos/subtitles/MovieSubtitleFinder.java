package hr.logos.subtitles;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.inject.Inject;
import hr.logos.subtitles.subsmax.HttpClientSearchGetAdapter;
import hr.logos.subtitles.subsmax.Item;
import hr.logos.subtitles.subsmax.SubsMaxAPI;
import org.apache.commons.lang3.StringUtils;
import org.simpleframework.xml.Serializer;

/**
 * @author pfh (Kristijan Šarić)
 */

public class MovieSubtitleFinder implements Finder<String> {

    public static final String SUBSMAX_URL = "http://subsmax.com/api/50/";
    public static final String LANGUAGE = "-en";

    private final HttpClientSearchGetAdapter httpClientSearchGetAdapter;
    private final Serializer serializer;

    private String subtitleDownloadLink = "";

    @Inject
    public MovieSubtitleFinder(
            final HttpClientSearchGetAdapter httpClientSearchGetAdapter,
            final Serializer serializer
    ) {
        this.httpClientSearchGetAdapter = httpClientSearchGetAdapter;
        this.serializer = serializer;
    }

    @Override
    public Boolean find( String param ) {

        Preconditions.checkState( !Strings.isNullOrEmpty( param ), "Find parameter cannot be NULL or EMPTY." );
        final String uri = SUBSMAX_URL + param.replaceAll( " ", "-" ) + LANGUAGE;

        try {

            // deserialize the xml
            final String httpResponse = Preconditions.checkNotNull( httpClientSearchGetAdapter.fetchHttpXml( uri ) );

            // javax.xml.stream.XMLStreamException: ParseError
            // check if XML format
            final SubsMaxAPI subsMaxApi = serializer.read( SubsMaxAPI.class, httpResponse );

            // must have some subtitles
            Preconditions.checkState( subsMaxApi.getItems().size() > 0, "No found subtitles." );

            if ( subsMaxApi.getItems() != null && !( subsMaxApi.getItems().size() > 0 ) )
                return Boolean.FALSE;

            // set it on MAX value so we have an edge value for comparison
            Integer minDistance = Integer.MAX_VALUE;

            findLink( param, subsMaxApi, minDistance );
        } catch ( Exception e ) {
            e.printStackTrace();
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

    private void findLink( String param, SubsMaxAPI subsMaxApi, Integer minDistance ) {
        for ( Item item : subsMaxApi.getItems() ) {
            final String filename = item.getFilename();

            final Integer levenshteinDistance = StringUtils.getLevenshteinDistance( param, filename );

            if ( levenshteinDistance < minDistance ) {
                minDistance = levenshteinDistance;
                subtitleDownloadLink = item.getLink();
            }
        }
    }

    @Override
    public String getResult() {
        Preconditions.checkState( !Strings.isNullOrEmpty( subtitleDownloadLink ), "Subtitle download link cannot be NULL!" );
        return subtitleDownloadLink;
    }
}