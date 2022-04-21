package at.spengergasse.legado.services;

import at.spengergasse.legado.model.Artist;
import at.spengergasse.legado.model.Genre;
import at.spengergasse.legado.model.Song;
import at.spengergasse.legado.presentation.restapi.ArtistDto;
import at.spengergasse.legado.presentation.restapi.SongDto;
import at.spengergasse.legado.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Transactional
public class SongService
{
    private final SongRepository songRepository;

    public List<SongDto> allSongs()
    {
        return StreamSupport.stream(
                songRepository
                        .findAll()
                        .spliterator(), false)
                        .map(entity -> SongDto.valueOf(entity))
                        .collect(Collectors.toList());
    }

    public List<Genre> getAllGenre()
    {
        return Arrays.stream(Genre.values()).toList();
    }

    /*
    public Optional<Song> oneSong(String identifier)
    {
        return null;
    }
*/
    public SongDto oneSong(long id)
    {
        Optional<Song> songDto = songRepository.findById(id);
        return SongDto
                .builder()
                .Id(songDto.get().getId())
                .title(songDto.get().getTitle())
                .artist(songDto.get().getArtist())
                .duration(songDto.get().getDuration())
                .genre(songDto.get().getGenre())
                .build();
    }

    public SongDto insert(SongDto songDto)
    {
        /*
        return Optional.ofNullable(songDto)
                .map(SongDto::toSong)
                .map(songRepository::save)
                .map(SongDto::valueOf)
                .get();
                */

    //   songDto.setArtist(artistDto.toArtist());

        return Optional.ofNullable(songDto)
                .map(SongDto::toSong)
                .map(songRepository::save)
                .map(SongDto::valueOf)
                .get();
    }

    public void delete(Long id)
    {
        Optional<Song> songDto = songRepository.findById(id);
        songRepository.delete(songDto.get());
    }

    public SongDto save(SongDto songDto)
    {
        return Optional.ofNullable(songDto)
                .map(SongDto::toSong)
                .map(songRepository::save)
                .map(SongDto::valueOf)
                .get();
    }
}
