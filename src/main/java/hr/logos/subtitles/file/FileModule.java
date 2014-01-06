package hr.logos.subtitles.file;

import com.google.inject.AbstractModule;

/**
 * @author pfh (Kristijan Šarić)
 */
public class FileModule extends AbstractModule {
    private FileModule() {
    }

    public static FileModule create() {
        return new FileModule();
    }

    @Override
    protected void configure() {
        bind( FileSystemAdapter.class ).to( DefaultFileSystemAdapter.class ).asEagerSingleton();
    }
}
