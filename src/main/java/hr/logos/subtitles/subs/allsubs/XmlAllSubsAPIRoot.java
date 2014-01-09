package hr.logos.subtitles.subs.allsubs;

import com.google.common.base.Objects;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * @author pfh (Kristijan Šarić) ksaric
 */

@Root
public class XmlAllSubsAPIRoot {
    @Element( name = "title" )
    private String title;

    @Element( name = "link" )
    private String link;

    @Element( name = "description" )
    private String description;

    @Element( name = "language" )
    private String language;

    @Element( name = "results" )
    private String results;

    @Element( name = "found_results" )
    private String foundResults;

    @ElementList( name = "items" )
    private List<XmlAllSubsAPIItem> items;// = Lists.newArrayList();

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

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage( String language ) {
        this.language = language;
    }

    public String getResults() {
        return results;
    }

    public void setResults( String results ) {
        this.results = results;
    }

    public String getFoundResults() {
        return foundResults;
    }

    public void setFoundResults( String foundResults ) {
        this.foundResults = foundResults;
    }

    public List<XmlAllSubsAPIItem> getItems() {
        return items;
    }

    public void setItems( List<XmlAllSubsAPIItem> items ) {
        this.items = items;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper( this )
                .add( "title", title )
                .add( "link", link )
                .add( "description", description )
                .add( "language", language )
                .add( "results", results )
                .add( "foundResults", foundResults )
                .add( "items", items )
                .toString();
    }
}
