package hr.logos.subtitles.file;

import com.google.common.base.CaseFormat;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.google.common.io.Files;
import hr.logos.subtitles.FileSystemAdapter;

import java.io.File;
import java.io.IOException;

/**
 * @author pfh (Kristijan Šarić)
 */

public class DefaultFileSystemAdapter implements FileSystemAdapter {

    @Override
    public ImmutableList<File> getMovieFiles( final String param ) {
        final File searchFolder = Preconditions.checkNotNull( new File( param ) );

        final FluentIterable<File> files = Files.fileTreeTraverser().preOrderTraversal( searchFolder );

        return files.filter( getMoviesFilter() ).toList();
    }

    @Override
    public ImmutableList<File> getNfoFiles( final String param ) {
        final File searchFolder = Preconditions.checkNotNull( new File( param ) );

        final FluentIterable<File> files = Files.fileTreeTraverser().preOrderTraversal( searchFolder );

        return files.filter( getNfoFilter() ).toList();
    }

    /**
     * Reads bytes from a file. No need for explicit conversions...
     * <p/>
     * todo: migrate to {@link Byte} ?
     *
     * @param firstNfoFile {@link java.io.File} to be read
     * @return byte[] byte array containing file content or empty array if no content extracted(none or exception)
     */

    @Override
    public byte[] readAllBytes( final File firstNfoFile ) {
        byte[] bytes = null;

        try {
            bytes = java.nio.file.Files.readAllBytes( firstNfoFile.toPath() );
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        // return empty
        return Optional.fromNullable( bytes ).or( new byte[]{} );
    }

    // http://www.fileinfo.com/filetypes/video
    /*

    Movie files:
    *.3g2, , *.3gp, *.3gp2, *.3gpp, *.60d, *.ajp, *.asf, *.asx, *.avchd, *.avi, *.bik, *.bix, *.box, *.cam, *.dat, *.divx, *.dmf, *.dv, *.dvr-ms, *.evo, *.flc, *.fli, *.flic, *.flv, *.flx, *.gvi, *.gvp, *.h264, *.m1v, *.m2p, *.m2ts, *.m2v, *.m4e, *.m4v, *.mjp, *.mjpeg, *.mjpg, *.mkv, *.moov, *.mov, *.movhd, *.movie, *.movx, *.mp4, *.mpe, *.mpeg, *.mpg, *.mpv, *.mpv2, *.mxf, *.nsv, *.nut, *.ogg, *.ogm, *.omf, *.ps, *.qt, *.ram, *.rm, *.rmvb, *.swf, *.ts, *.vfw, *.vid, *.video, *.viv, *.vivo, *.vob, *.vro, *.wm, *.wmv, *.wmx, *.wrap, *.wvx, *.wx, *.x264, *.xvid

    Subtitle files:
    *.srt, *.sub, *.smi, *.txt, *.ssa, *.ass, *.mpl

     */
    // SRP!
    private final String[] movieFormatList = new String[]{"avi", "mkv", "mpg", "mov", "wmv", "rm"};

    Predicate<? super File> getMoviesFilter() {
        return new Predicate<File>() {
            @Override
            public boolean apply( File fileName ) {
//                if ( !fileName.isFile() ) return false;
                // case matching
                final String fileExtension = CaseFormat.UPPER_UNDERSCORE.to( CaseFormat.LOWER_CAMEL, Files.getFileExtension( fileName.getName() ) );

                return Lists.newArrayList( movieFormatList ).contains( fileExtension );
            }
        };
    }

    private Predicate<? super File> getNfoFilter() {
        return new Predicate<File>() {
            @Override
            public boolean apply( File fileName ) {
//                if ( !fileName.isFile() ) return false;
                // case matching
                final String fileExtension = CaseFormat.UPPER_UNDERSCORE.to( CaseFormat.LOWER_CAMEL, Files.getFileExtension( fileName.getName() ) );

                return Lists.newArrayList( "nfo" ).contains( fileExtension );
            }
        };
    }
}
