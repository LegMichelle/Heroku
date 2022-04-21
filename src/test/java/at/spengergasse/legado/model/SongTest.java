package at.spengergasse.legado.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SongTest
{
    @Test
    void experimentWithLombok()
    {
        Song song = Song.builder()
                .title("Lalisa")
                .build();

        assertThat(song.getTitle()).isEqualTo("Lalisa");

    }
}
