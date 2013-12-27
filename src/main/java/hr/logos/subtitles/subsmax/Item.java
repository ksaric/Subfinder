package hr.logos.subtitles.subsmax;

import com.google.common.base.Objects;
import org.simpleframework.xml.Element;

/**
 * @author pfh (Kristijan Šarić)
 */

public class Item {

    @Element( name = "title" )
    private String title;

    @Element( name = "link" )
    private String link;

    @Element( name = "filename" )
    private String filename;

    @Element( name = "files_in_archive" )
    private String filesInArchive;

    @Element( name = "files_counter" )
    private Integer filesCounter;

    @Element( name = "languages" )
    private String languages;

    @Element( name = "added_on" )
    private String addedOn;

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink( String link ) {
        this.link = link;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename( String filename ) {
        this.filename = filename;
    }

    public String getFilesInArchive() {
        return filesInArchive;
    }

    public void setFilesInArchive( String filesInArchive ) {
        this.filesInArchive = filesInArchive;
    }

    public Integer getFilesCounter() {
        return filesCounter;
    }

    public void setFilesCounter( Integer filesCounter ) {
        this.filesCounter = filesCounter;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages( String languages ) {
        this.languages = languages;
    }

    public String getAddedOn() {
        return addedOn;
    }

    public void setAddedOn( String addedOn ) {
        this.addedOn = addedOn;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper( this )
                .add( "title", title )
                .add( "link", link )
                .add( "filename", filename )
                .add( "filesInArchive", filesInArchive )
                .add( "filesCounter", filesCounter )
                .add( "languages", languages )
                .add( "addedOn", addedOn )
                .toString();
    }
}
