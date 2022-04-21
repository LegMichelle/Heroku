package at.spengergasse.legado.repository;

import at.spengergasse.legado.model.Artist;
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
public class ArtistRepositoryTest
{
    @Autowired
    private ArtistRepository artistRepositoryTest;

    @Test
    void assertThatInsertAndRead()
    {
        var save = artistRepositoryTest.save(artist("AJ"));
        assertThat(save).isNotNull();

        var result = artistRepositoryTest.findById(save.getId());

        assertThat(result).isNotEmpty();
    }

    @Test
    void assertThatDelete()
    {
        var artist1 = artistRepositoryTest.save(artist("AJ"));
        var artist2 = artistRepositoryTest.save(artist("J zee"));
        assertThat(artist1).isNotNull();
        assertThat(artist2).isNotNull();

        artistRepositoryTest.delete(artist1);

        assertThat(artistRepositoryTest.findById(artist1.getId())).isEmpty();

        var artists = artistRepositoryTest.findAll();

        assertThat(artists).hasSize(1).contains(artist2);
    }

    @Test
    void assertUpdate()
    {
        var artist1 = artistRepositoryTest.save(artist("AJ"));
        var artist2 = artistRepositoryTest.save(artist("Jzee"));
        assertThat(artist1).isNotNull();
        assertThat(artist2).isNotNull();

        var artist2Id = artistRepositoryTest.findById(artist2.getId()).get();

        assertThat(artist2Id.getBirthDate()).isEqualTo("1990-01-08");
        assertThat(artist2Id.getBirthName()).isEqualTo("Lol");
        assertThat(artist2Id.getStageName()).isEqualTo("Jzee");

        Artist updateArtist = Artist.builder()
                                    .birthDate(LocalDate.parse("2000-04-01"))
                                    .birthName("Lel")
                                    .build();

        artist2Id.setBirthName(updateArtist.getBirthName());
        artist2Id.setBirthDate(updateArtist.getBirthDate());

        artistRepositoryTest.save(artist2Id);

        var checkArtist2Id = artistRepositoryTest.findById(artist2.getId()).get();

        assertThat(checkArtist2Id.getBirthDate()).isEqualTo(updateArtist.getBirthDate());
        assertThat(checkArtist2Id.getBirthName()).isEqualTo(updateArtist.getBirthName());


    }

    Artist artist(String name)
    {
        return Artist.builder()
                .stageName(name)
                .birthName("Lol")
                .birthDate(LocalDate.parse("1990-01-08"))
                .build();
    }


}
