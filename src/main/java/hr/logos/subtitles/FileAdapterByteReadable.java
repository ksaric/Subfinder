package hr.logos.subtitles;

import java.io.File;

/**
 * @author pfh (Kristijan Šarić)
 */
public interface FileAdapterByteReadable {

    byte[] readAllBytes( final File firstNfoFile );

}
