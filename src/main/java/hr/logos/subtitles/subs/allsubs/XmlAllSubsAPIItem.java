package hr.logos.subtitles.subs.allsubs;

import com.google.common.base.Objects;
import org.simpleframework.xml.Element;

/**
 * @author pfh (Kristijan Šarić) ksaric
 */

@Element( name = "item" )
public class XmlAllSubsAPIItem {
    @Element( name = "title" )
    private String title;

    @Element( name = "link" )
    private String link;

    @Element( name = "filename" )
    private String filename;

    // todo : Separated list
    @Element( name = "files_in_archive" )
    private String filesInArchive;

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
                .add( "languages", languages )
                .add( "addedOn", addedOn )
                .toString();
    }
}
