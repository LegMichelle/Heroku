package at.spengergasse.legado.services;

import at.spengergasse.legado.model.Playlist;
import at.spengergasse.legado.model.Song;
import at.spengergasse.legado.presentation.restapi.PlaylistDto;
import at.spengergasse.legado.presentation.restapi.SongDto;
import at.spengergasse.legado.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Service
@Transactional
public class PlaylistService
{
    private final PlaylistRepository playlistRepository;

    public List<PlaylistDto> allPlaylists()
    {
        return StreamSupport.stream(
                playlistRepository
                        .findAll()
                        .spliterator(), false)
                        .map(entity -> PlaylistDto.valueOf(entity))
                        .collect(Collectors.toList());
    }

/*
    public Optional<Playlist> onePlaylist(String identifier)
    {
        return null;
    }
*/

    public PlaylistDto onePlaylist(long id)
    {
        Optional<Playlist> playlist = playlistRepository.findById(id);
        return PlaylistDto
                .builder()
                .Id(playlist.get().getId())
                .creationDate(playlist.get().getCreationDate())
                .name(playlist.get().getName())
                .description(playlist.get().getDescription())
            //    .user(playlist.get().getUser())
                .build();


    }
    public PlaylistDto insert(PlaylistDto playlistDto)
    {
        return Optional.ofNullable(playlistDto)
                .map(PlaylistDto::toPlaylist)  //.map(dto -> dto.toArtist())
                .map(playlistRepository::save) //.map(entity -> artistRepository.save(entity))
                .map(PlaylistDto::valueOf) // .map(entity -> ArtistDto.valueOf(entity))
                .get();
    }

    public void delete(Long id)
    {
        Optional<Playlist> playlistDto = playlistRepository.findById(id);
        playlistRepository.delete(playlistDto.get());
    }

    public PlaylistDto save(PlaylistDto playlistDto)
    {
        return Optional.ofNullable(playlistDto)
                .map(PlaylistDto::toPlaylist)
                .map(playlistRepository::save)
                .map(PlaylistDto::valueOf)
                .get();
    }

    public void saveSongs(List<Song> songList, PlaylistDto playlist)
    {
        for (Song songDto :songList)
        {
            playlist.toPlaylist().addSong(songDto);
        }
        playlistRepository.save(playlist.toPlaylist());
    }


}
