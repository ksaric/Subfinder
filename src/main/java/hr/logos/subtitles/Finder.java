package hr.logos.subtitles;

/**
 * @author pfh (Kristijan Šarić)
 */

public interface Finder<T> extends FinderResult<T> {

    Boolean find( T param );

}
