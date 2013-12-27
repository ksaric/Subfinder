package hr.logos.subtitles;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import hr.logos.subtitles.subsmax.HttpClientAdapter;
import hr.logos.subtitles.subsmax.Item;
import hr.logos.subtitles.subsmax.SubsMaxAPI;
import org.simpleframework.xml.Serializer;

/**
 * @author pfh (Kristijan Šarić)
 */

public class MovieSubtitleFinder implements Finder<String> {

    public static final String SUBSMAX_URL = "http://subsmax.com/api/50/";
    public static final String LANGUAGE = "-en";

    private final HttpClientAdapter httpClientAdapter;
    private final Serializer serializer;

    // injector requires more than private

    @Inject
    public MovieSubtitleFinder(
            final HttpClientAdapter httpClientAdapter,
            final Serializer serializer
    ) {
        this.httpClientAdapter = httpClientAdapter;
        this.serializer = serializer;
    }

    @Override
    public Boolean find( String param ) {

        final String uri = SUBSMAX_URL + param.replaceAll( " ", "-" ) + LANGUAGE;

        try {

            // deserialize the xml
            final String httpResponse = Preconditions.checkNotNull( httpClientAdapter.fetchHttpXml( uri ) );
            final SubsMaxAPI subsMaxApi = serializer.read( SubsMaxAPI.class, httpResponse );

//            Preconditions.checkState(  );

            if ( subsMaxApi.getItems() != null && !( subsMaxApi.getItems().size() > 0 ) )
                return Boolean.FALSE;

            // choose link
            for ( Item item : subsMaxApi.getItems() ) {
                final String filename = item.getFilename();

                //                if (filename) //special compare method
                final String languages = item.getLanguages();
                final String itemLink = item.getLink();
            }

            // pick an item
            Item pickedItem = subsMaxApi.getItems().get( 0 );

            String link = pickedItem.getLink();

            System.out.println( link );

        } catch ( Exception e ) {
            e.printStackTrace();
        }

        return Boolean.TRUE;
    }
}