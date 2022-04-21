package at.spengergasse.legado.presentation.restapi;

import at.spengergasse.legado.model.Artist;
import at.spengergasse.legado.model.Song;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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
public class ArtistDto
{
    private Long Id;
    private String stageName;
    private String birthName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    //private List<Song> songList;

    public static ArtistDto valueOf(Artist artist)
    {
        return ArtistDto.builder()
                .Id(artist.getId())
                .stageName(artist.getStageName())
                .birthName(artist.getBirthName())
                .birthDate(artist.getBirthDate())
                .build();
    }

    public Artist toArtist()
    {
        return Artist.builder()
                .Id(this.Id)
                .stageName(this.stageName)
                .birthName(this.birthName)
                .birthDate(this.birthDate)
                .build();
    }
}
