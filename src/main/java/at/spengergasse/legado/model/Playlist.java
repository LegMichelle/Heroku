package at.spengergasse.legado.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
@ToString
@Builder
@Getter

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "playlists")
public class Playlist implements Serializable
{

    @Getter
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Setter
    private String name;
    @Setter
    private String description;
    @Setter
    private LocalDate creationDate;

    @Embedded
    @Setter
    private User user;

    @Setter
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Song> songList;

    public void addSong(Song song)
    {
        this.songList.add(song);

        if (!this.equals(song.getPlaylist()))
        {
            song.setPlaylist(this);
        }
    }

    public List<Song> getSong()
    {
        return Collections.unmodifiableList(songList);
    }

    public boolean containsSong(Song song)
    {
        if(songList == null)
        {
            return false;
        }
        return songList.contains(song);
    }

    public boolean removeSong(Song song) {
        if (containsSong(song)) {
            songList.remove(song);
            return true;
        }
        return false;
    }

    public User getUser()
    {
        return user;
    }

}
