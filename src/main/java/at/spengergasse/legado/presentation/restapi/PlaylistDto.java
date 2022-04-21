package at.spengergasse.legado.presentation.restapi;

import at.spengergasse.legado.model.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embedded;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

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
public class PlaylistDto
{
    private Long Id;
    private String name;
    private String description;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;
   // private User user;

    private List<Song> songList;

    public static PlaylistDto valueOf(Playlist playlist)
    {
        return PlaylistDto.builder()
                .creationDate(playlist.getCreationDate())
                .Id(playlist.getId())
                //.user(playlist.getUser())
                .name(playlist.getName())
                .description(playlist.getDescription())
                .songList(Collections.emptyList())
                .build();
    }

    public Playlist toPlaylist()
    {
        return Playlist.builder()
                .Id(this.Id)
                .creationDate(this.creationDate)
             //   .user(this.user)
                .name(this.name)
                .description(this.description)
                .songList(this.songList)
                .build();
    }
}
