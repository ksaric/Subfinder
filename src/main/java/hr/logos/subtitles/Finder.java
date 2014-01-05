package hr.logos.subtitles;

/**
 * @author pfh (Kristijan Šarić)
 */

public interface Finder<T, L> extends FinderResult<L> {

    Boolean find( T param );

}
