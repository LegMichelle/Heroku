package at.spengergasse.legado.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode
@ToString
@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name="songs")
public class Song implements Serializable
{
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Setter
    private String title;
    @Setter
    private Double duration;

    @Setter
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Artist artist;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Playlist playlist;


    public Playlist getPlaylist()
    {
        return playlist;
    }

    protected void setPlaylist(Playlist playlist)
    {
        this.playlist = playlist;
        if(!playlist.containsSong(this))
        {
            playlist.addSong(this);
        }
    }

    public Artist getArtist()
    {
        return artist;
    }

    protected void setArtist(Artist artist)
    {
        this.artist = artist;
        if(!artist.containsSong(this))
        {
            artist.addSong(this);
        }
    }


}
