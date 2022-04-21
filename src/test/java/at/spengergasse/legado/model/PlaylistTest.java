package at.spengergasse.legado.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlaylistTest
{
    @Test
    void experimentWithLombok()
    {
        Playlist playlist = Playlist.builder()
                .name("First Playlist")
                .build();

        assertThat(playlist.getName()).isEqualTo("First Playlist");
    }
}
