package at.spengergasse.legado.presentation.restapi;

import at.spengergasse.legado.model.Artist;
import at.spengergasse.legado.services.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@RestController
@RequestMapping(path = "/artists")
public class ArtistController
{
    private final ArtistService artistService;


    @GetMapping
    public List<ArtistDto> allArtists()
    {
       return artistService.allArtists();
    }

/*
    @GetMapping(path = "/{identifier}")
    public Optional<Artist> oneArtist(Long id)
    {
        return artistService.oneArtist(id);
    }

 */

    @PostMapping
    public ArtistDto insert(@RequestBody ArtistDto artistDto)
    {
        return artistService.insert(artistDto);
    }

    /*
    @DeleteMapping(path = "/{identifier}")
    public ResponseEntity<> delete (@PathVariable String identifier)
    {

    }
*/
}
