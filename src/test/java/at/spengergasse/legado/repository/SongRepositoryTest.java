package at.spengergasse.legado.repository;

import at.spengergasse.legado.model.Artist;
import at.spengergasse.legado.model.Genre;
import at.spengergasse.legado.model.Song;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Slf4j
@SpringBootTest
public class SongRepositoryTest
{
    @Autowired
    private SongRepository songRepository;

    @Test
    void assertThatInsertAndRead()
    {
        var artist = artist("AJ");
        var save = songRepository.save(song(artist,"First Song"));
        assertThat(save).isNotNull();

        var result = songRepository.findById(save.getId());

        assertThat(result).isNotEmpty();
    }
/*

 */
    @Test
    void assertThatDelete() {
        var artist = artist("AJ");
        var artist2 = artist("J zee");
        var song1 = songRepository.save(song(artist, "First Song"));
        var song2 = songRepository.save(song(artist2, "Second Song"));
        assertThat(song1).isNotNull();
        assertThat(song2).isNotNull();

        songRepository.delete(song1);

        assertThat(songRepository.findById(song1.getId())).isEmpty();

      /*  var songs = songRepository.findAll();
        assertThat(songs).hasSize(1).contains(song2);

       */
    }

    @Test
    void assertUpdate()
    {
        Artist artist = artist("AJ");
        var song1 = songRepository.save(song(artist, "First Song"));
        var song2 = songRepository.save(song(artist, "Second Song"));
        assertThat(song1).isNotNull();
        assertThat(song2).isNotNull();

        var song2Id = songRepository.findById(song2.getId()).get();

        assertThat(song2Id.getArtist()).isEqualTo(artist);
        assertThat(song2Id.getTitle()).isEqualTo("Second Song");
        assertThat(song2Id.getDuration()).isEqualTo(3.45);
        assertThat(song2Id.getGenre()).isEqualTo(Genre.POP);

        Song updateSong2 = Song.builder()
                .title("Latin Song Lol")
                .genre(Genre.LATIN)
                .duration(4.30)
                .build();


        song2Id.setGenre(updateSong2.getGenre());
        song2Id.setTitle(updateSong2.getTitle());
        song2Id.setDuration(updateSong2.getDuration());

        songRepository.save(song2Id);

        var checkSong2Id = songRepository.findById(song2Id.getId()).get();

        assertThat(checkSong2Id.getTitle()).isEqualTo(updateSong2.getTitle());
        assertThat(checkSong2Id.getDuration()).isEqualTo(updateSong2.getDuration());
        assertThat(checkSong2Id.getGenre()).isEqualTo(updateSong2.getGenre());

      /*  var songs = songRepository.findAll();
        assertThat(songs).hasSize(2).contains(song1, checkSong2Id);

       */
    }

    Artist artist(String name)
    {
        return Artist.builder()
                .stageName(name)
                .birthName("Lol")
                .birthDate(LocalDate.parse("1990-01-08"))
                .build();
    }


    Song song(Artist artist,String title)
    {
        return Song.builder()
                .duration(3.45)
                .genre(Genre.POP)
                .title(title)
                .artist(artist)
                .build();
    }

}
