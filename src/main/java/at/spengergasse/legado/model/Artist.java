package at.spengergasse.legado.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode
@ToString
@Builder
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name="artists")
public class Artist implements Serializable
{
    @Getter
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    //Generate Shortcut: Alt+Einf
    @Setter
    private String stageName;
    @Setter
    private String birthName;
    @Setter
    private LocalDate birthDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Song> songList;

   public void addSong(Song song)
    {
        this.songList.add(song);
        if(!this.equals(song.getArtist()))
        {
            song.setArtist(this);
        }
    }

    public List<Song> getSong()

    {
        return Collections.unmodifiableList(songList);
    }

    public boolean containsSong(Song song)
    {
        return songList.contains(song);
    }

    public boolean removeSong(Song song) {
        if (containsSong(song)) {
            songList.remove(song);
            return true;
        }
        return false;
    }

}
