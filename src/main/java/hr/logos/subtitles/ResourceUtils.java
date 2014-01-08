package hr.logos.subtitles;

import com.google.common.base.Charsets;
import com.google.common.io.CharSource;
import com.google.common.io.Resources;

import java.io.IOException;

/**
 * @author pfh (Kristijan Šarić) ksaric
 */

public class ResourceUtils {

    public static String getResourceToString( final String fileName ) throws IOException {
        final CharSource charSource = Resources.asCharSource(
                Resources.getResource( fileName ), Charsets.UTF_8
        );

        final StringBuilder stringBuilder = new StringBuilder();
        charSource.copyTo( stringBuilder );

        return stringBuilder.toString();
    }
}
