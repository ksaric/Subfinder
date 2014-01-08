package hr.logos.subtitles;

import com.google.common.collect.*;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * @author pfh (Kristijan Šarić)
 */

public class SubtitleAnalysisTest {

    @Test
    public void testSimpleAnalysis() throws Exception {
        //Before
        final String subtitle = ResourceUtils.getResourceToString( "walking_dead.srt" );
        SubtitleAnalyzable subtitleAnalyzable = new DefaultSubtitleAnalyzable();

        //When
        final Collection<SubtitleInfo> subtitleInfos = subtitleAnalyzable.analyzeSubtitle( subtitle );

        //Then
        Assert.assertThat( 385, Matchers.is( subtitleInfos.size() ) );
    }

    @Test
    public void testSubtitleContain() throws Exception {
//Before
        final String subtitle = ResourceUtils.getResourceToString( "walking_dead.srt" );
        SubtitleAnalyzable subtitleAnalyzable = new DefaultSubtitleAnalyzable();

        //When
        final Collection<String> subtitleContents = Lists.newArrayList();

        for ( SubtitleInfo subtitleInfo : subtitleAnalyzable.analyzeSubtitle( subtitle ) ) {
            subtitleContents.add( subtitleInfo.getSubtitleContent() );
        }

        //Then
        Assert.assertThat( subtitleContents, Matchers.hasItems(
                "(gunshot)",
                "They're gonna join us."
        ) );
    }
}


