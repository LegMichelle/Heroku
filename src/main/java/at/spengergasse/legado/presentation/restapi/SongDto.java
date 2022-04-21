package at.spengergasse.legado.presentation.restapi;

import at.spengergasse.legado.model.Artist;
import at.spengergasse.legado.model.Genre;
import at.spengergasse.legado.model.Playlist;
import at.spengergasse.legado.model.Song;
import lombok.*;

import java.time.LocalDate;

/**
 * A Data Transfer Object for the model (entity) class Artist
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class SongDto
{
    private Long Id;
    private String title;
    private Double duration;
    private Artist artist;
    private Genre genre;
    private Playlist playlist;


    public static SongDto valueOf(Song song)
    {
        return SongDto.builder()
                .genre(song.getGenre())
                .Id(song.getId())
                .artist(song.getArtist())
                .duration(song.getDuration())
                .title(song.getTitle())
                .playlist(song.getPlaylist())
                .build();
    }

    public Song toSong()
    {
        return Song.builder()
                .genre(this.genre)
                .Id(this.Id)
                .artist(this.artist)
                .duration(this.duration)
                .title(this.title)
                .playlist(this.playlist)
                .build();
    }
}
