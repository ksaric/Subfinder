package hr.logos.subtitles;

import java.util.Collection;

/**
 * @author pfh (Kristijan Šarić) ksaric
 */

public interface SubtitleAnalyzable {

    enum SearchState {
        SRT_STATE_SUBNUMBER,
        SRT_STATE_TIME,
        SRT_STATE_TEXT
    }

    final String lineSeparator = System.getProperty( "line.separator" );

    Collection<SubtitleInfo> analyzeSubtitle( final String subtitleText );

}

class SubtitleInfo {
    private Integer subtitleNumber;

    private String subtitleTime;

    private String subtitleContent;

    public Integer getSubtitleNumber() {
        return subtitleNumber;
    }

    public void setSubtitleNumber( Integer subtitleNumber ) {
        this.subtitleNumber = subtitleNumber;
    }

    public String getSubtitleTime() {
        return subtitleTime;
    }

    public void setSubtitleTime( String subtitleTime ) {
        this.subtitleTime = subtitleTime;
    }

    public String getSubtitleContent() {
        return subtitleContent;
    }

    public void setSubtitleContent( String subtitleContent ) {
        this.subtitleContent = subtitleContent;
    }
}
