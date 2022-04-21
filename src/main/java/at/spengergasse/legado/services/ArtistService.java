package at.spengergasse.legado.services;

import at.spengergasse.legado.model.Artist;
import at.spengergasse.legado.presentation.restapi.ArtistDto;
import at.spengergasse.legado.repository.ArtistRepository;
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
public class ArtistService
{
    private final ArtistRepository artistRepository;

    public List<ArtistDto> allArtists()
    {
        return StreamSupport.stream(
                artistRepository
                        .findAll()
                        .spliterator(), false)
                        .map(entity -> ArtistDto.valueOf(entity))
                        .collect(Collectors.toList());
    }


   /* public Optional<Artist> oneArtist(String identifier)
    {
        return null;
    }*/


    public ArtistDto oneArtist(long id)
    {
        Optional<Artist> artistDto = artistRepository.findById(id);
        return ArtistDto
                .builder()
                .Id(artistDto.get().getId())
                .birthDate(artistDto.get().getBirthDate())
                .stageName(artistDto.get().getStageName())
                .birthName(artistDto.get().getBirthName())
                .build();
    }

    public ArtistDto insert(ArtistDto artistDto)
    {
        return Optional.ofNullable(artistDto)
                .map(ArtistDto::toArtist)  //.map(dto -> dto.toArtist())
                .map(artistRepository::save) //.map(entity -> artistRepository.save(entity))
                .map(ArtistDto::valueOf) // .map(entity -> ArtistDto.valueOf(entity))
                .get();
    }

    public void delete(Long id)
    {
        Optional<Artist> artistDto = artistRepository.findById(id);
        artistRepository.delete(artistDto.get());
    }

    public ArtistDto save(ArtistDto artistDto)
    {
        return Optional.ofNullable(artistDto)
                .map(ArtistDto::toArtist)
                .map(artistRepository::save)
                .map(ArtistDto::valueOf)
                .get();
    }
}
