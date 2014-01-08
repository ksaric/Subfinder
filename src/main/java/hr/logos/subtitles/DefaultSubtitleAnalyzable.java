package hr.logos.subtitles;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.*;

import java.util.Collection;

/**
 * @author pfh (Kristijan Šarić) ksaric
 */

public class DefaultSubtitleAnalyzable implements SubtitleAnalyzable {

    private SearchState currentSearchState = SearchState.SRT_STATE_SUBNUMBER;

    private Integer subtitleNumber = 0;

    private String subtitleTime = "";

    private String subtitleTextContent = "";

    @Override
    public Collection<SubtitleInfo> analyzeSubtitle( final String subtitleText ) {

        final Iterable<String> subtitleTokens = Splitter.on( lineSeparator )
                .omitEmptyStrings()
                .trimResults()
                .split( subtitleText );

        Collection<SubtitleInfo> subtitleInfos = Lists.newArrayList();
        SubtitleInfo currentSubtitleInfo = new SubtitleInfo();

        for ( String currentSubtitleLine : subtitleTokens ) {

            final Integer tempNumber = subtitleNumber + 1;
            if ( currentSubtitleLine.trim().equals( tempNumber.toString() ) ) {
                currentSubtitleInfo.setSubtitleNumber( subtitleNumber );
                currentSubtitleInfo.setSubtitleContent( subtitleTextContent );

                currentSearchState = SearchState.SRT_STATE_SUBNUMBER;
                subtitleTextContent = "";

                subtitleInfos.add( currentSubtitleInfo );
            }

            switch ( currentSearchState ) {
                case SRT_STATE_SUBNUMBER:
                    currentSubtitleInfo = new SubtitleInfo();
                    subtitleNumber = Integer.valueOf( ( CharMatcher.DIGIT.retainFrom( currentSubtitleLine ) ).trim() );
                    currentSearchState = SearchState.SRT_STATE_TIME;
                    break;

                case SRT_STATE_TIME:
                    subtitleTime = ( currentSubtitleLine ).trim();
                    currentSubtitleInfo.setSubtitleTime( subtitleTime );
                    currentSearchState = SearchState.SRT_STATE_TEXT;
                    break;

                case SRT_STATE_TEXT:
                    subtitleTextContent += currentSubtitleLine;
                    break;
            }
        }

        // the last item!
        currentSubtitleInfo.setSubtitleNumber( subtitleNumber );
        currentSubtitleInfo.setSubtitleContent( subtitleTextContent );
        subtitleInfos.add( currentSubtitleInfo );

        return subtitleInfos;
    }
}
