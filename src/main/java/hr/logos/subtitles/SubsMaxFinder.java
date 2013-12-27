package hr.logos.subtitles;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author pfh (Kristijan Šarić)
 */

@BindingAnnotation
@Target( {ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD} )
@Retention( RetentionPolicy.RUNTIME )
public @interface SubsMaxFinder {
}
