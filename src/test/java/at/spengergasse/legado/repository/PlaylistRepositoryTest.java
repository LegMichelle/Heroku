package at.spengergasse.legado.repository;

import at.spengergasse.legado.model.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Slf4j
@SpringBootTest
public class PlaylistRepositoryTest
{
    @Autowired
    private PlaylistRepository playlistRepository;

    @Test
    void assertSimpleInsertAndRead()
    {
        Playlist playlist = Playlist.builder()
                .name("First Testplaylist")
                .user(User.builder()
                        .username("Legooo")
                        .firstname("Michelle")
                        .lastname("Legado")
                        .birthDate(LocalDate.parse("2000-01-08"))
                        .build())
                .creationDate(LocalDate.now())
                .description("TestPlaylist for Pop Music")

                .songList(
                        List.of(
                                Song.builder()
                                        .duration(3.45)
                                        .genre(Genre.POP)
                                        .title("First Song")
                                        .artist(
                                                Artist.builder()
                                                        .stageName("Lel")
                                                        .birthName("Lol")
                                                        .birthDate(LocalDate.parse("1990-01-08"))
                                                        .build()
                                                 )
                                        .build()
                                )
                        )
                .build();

        log.info("Playlist {}", playlist);

        assertThat(playlist.getId()).isNull();
        var result = playlistRepository.save(playlist);
        assertThat(result.getId()).isNotNull();

        var reread = playlistRepository.findById(result.getId());
        assertThat(reread).isNotEmpty();

    }


    @Test
    void assertSimpleRemove()
    {
        Artist artist = artist();
        Song song = firstSong(artist());
        Playlist playlist = popPlaylist(List.of(song));

        assertThat(playlist.getId()).isNull();
        var result = playlistRepository.save(playlist);
        assertThat(result.getId()).isNotNull();

        playlistRepository.delete(playlist);
//        playlistRepository.findById(playlist.getId());
        assertThat(playlistRepository.findById(playlist.getId())).isEmpty();

        var playlists = playlistRepository.findAll();
        assertThat(playlists).hasSize(0);
    }

    @Test
    void assertFindAllPlaylist()
    {
        Artist artist = artist();
        Song song = firstSong(artist());
        Playlist playlist = popPlaylist(List.of(song));

        var result = playlistRepository.save(playlist);
        assertThat(result.getId()).isNotNull();

        var playlists = playlistRepository.findAll();

        assertThat(playlists).hasSize(1).contains(playlist);

    }

    Artist artist()
    {
        return Artist.builder()
                .stageName("Lel")
                .birthName("Lol")
                .birthDate(LocalDate.parse("1990-01-08"))
                .build();
    }


    Song firstSong(Artist artist)
    {
        return Song.builder()
                .duration(3.45)
                .genre(Genre.POP)
                .title("first Pop Song")
                .artist(artist)
                .build();
    }

    Playlist popPlaylist(List<Song> songList)
    {
        return Playlist.builder()
                .name("First Testplaylist")
                .user(User.builder()
                        .username("Legooo")
                        .firstname("Michelle")
                        .lastname("Legado")
                        .birthDate(LocalDate.parse("2000-01-08"))
                        .build())
                .creationDate(LocalDate.now())
                .description("TestPlaylist for Pop Music")
                .songList(songList)
                .build();
    }

}
