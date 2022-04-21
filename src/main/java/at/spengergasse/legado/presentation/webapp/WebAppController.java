package at.spengergasse.legado.presentation.webapp;

import at.spengergasse.legado.model.Artist;
import at.spengergasse.legado.model.Genre;
import at.spengergasse.legado.model.Song;
import at.spengergasse.legado.presentation.restapi.ArtistDto;
import at.spengergasse.legado.presentation.restapi.PlaylistDto;
import at.spengergasse.legado.presentation.restapi.SongDto;
import at.spengergasse.legado.services.ArtistService;
import at.spengergasse.legado.services.PlaylistService;
import at.spengergasse.legado.services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Controller
//@RequestMapping(path="/webapp")
@Transactional
public class WebAppController
{
    private final ArtistService artistService;
    private final SongService songService;
    private final PlaylistService playlistService;


    @GetMapping(produces = MediaType.TEXT_HTML_VALUE, path ="/webapp")
    public ModelAndView startPage()
    {
     /*  artistService.insert(ArtistDto.builder()
                .stageName("Lisa")
                .birthName("Lalisa")
                .birthDate(LocalDate.parse("1990-01-08"))
                .build());
*/
        ModelAndView modelAndView = new ModelAndView("startpage");
        return modelAndView;
    }

    /**
     * ARTISTS
     *
     */

    @GetMapping(path ="/webapp/show-artist")
    public ModelAndView showArtist()
    {
        ModelAndView modelAndView = new ModelAndView("show-artist");
        List<ArtistDto> artists = artistService.allArtists();
        modelAndView.addObject("artists", artists);
        return modelAndView;
    }

    @GetMapping(path ="/webapp/show-artist/add")
    public ModelAndView showAddForumArtist()
    {
        ArtistDto artist = ArtistDto.builder().build();
        ModelAndView modelAndView = new ModelAndView("add-artist");
        modelAndView.addObject("artist", artist);
        return modelAndView;
    }

    @PostMapping("/webapp/show-artist/addartist")
    public ModelAndView addArtist(@Valid ArtistDto artist, BindingResult result)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/webapp/show-artist");
        /*if (result.hasErrors())
        {
             modelAndView = new ModelAndView("add-artist");
             return modelAndView;
        }*/
      //  modelAndView.addObject("artist", artist);
        artistService.insert(artist);
        return modelAndView;
    }


    @GetMapping("/webapp/show-artist/delete/{id}")
    public ModelAndView deleteArtist(@PathVariable("id") long id)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/webapp/show-artist");
        artistService.delete(id);
        List<ArtistDto> artists = artistService.allArtists();
        modelAndView.addObject("artists", artists);
        return modelAndView;
    }

    @GetMapping(path ="/webapp/show-artist/edit/{id}")
    public ModelAndView showEditForumArtist(@PathVariable("id") long id)
    {
        ArtistDto artist = artistService.oneArtist(id);
        ModelAndView modelAndView = new ModelAndView("update-artist");
        modelAndView.addObject("artist", artist);
        return modelAndView;
    }

    @PostMapping("/webapp/show-artist/update/{id}") //not working .-.
    public ModelAndView updateArtist(@PathVariable("id") long id, @Valid ArtistDto artist, BindingResult result)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/webapp/show-artist");
        artistService.save(artist);

        return modelAndView;
    }


    /**
     * SONGS
     *
     */


    @GetMapping(path ="/webapp/show-songs")
    public ModelAndView showSong()
    {
        ModelAndView modelAndView = new ModelAndView("show-song");
        List<SongDto> songs = songService.allSongs();
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }

    @GetMapping(path ="/webapp/show-songs/add")
    public ModelAndView showAddForumSong() //not working .-., select don't work
    {
        SongDto song = SongDto.builder().build();
        ModelAndView modelAndView = new ModelAndView("add-song");
        List<ArtistDto> artists = artistService.allArtists();
        List<Genre> genres = songService.getAllGenre();
        modelAndView.addObject("genres", genres);
        modelAndView.addObject("artists", artists);
        modelAndView.addObject("song", song);
        return modelAndView;
    }

    @PostMapping("/webapp/show-songs/addsong") //not working .-.
    public ModelAndView addSong(@RequestParam String title, @RequestParam Double duration, @RequestParam Genre genre , @RequestParam Long artist
    ){
        ModelAndView modelAndView = new ModelAndView("redirect:/webapp/show-songs");

        //ArtistDto artistDto = artistService.oneArtist(artist);
        songService.insert(SongDto
                .builder()
                        .genre(genre)
                        .title(title)
                        .artist(artistService.oneArtist(artist).toArtist())
                        .duration(duration)
                .build());
        return modelAndView;
    }

    @GetMapping("/webapp/show-songs/delete/{id}")
    public ModelAndView deleteSong(@PathVariable("id") long id)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/webapp/show-songs");
        songService.delete(id);
        List<SongDto> songs = songService.allSongs();
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }

    @GetMapping(path ="/webapp/show-songs/edit/{id}")
    public ModelAndView showEditForumSong(@PathVariable("id") long id)
    {
        SongDto song = songService.oneSong(id);
        ModelAndView modelAndView = new ModelAndView("update-song");
        modelAndView.addObject("song", song);
        List<ArtistDto> artists = artistService.allArtists();
        modelAndView.addObject("artists", artists);
        List<Genre> genres = songService.getAllGenre();
        modelAndView.addObject("genres", genres);
        return modelAndView;
    }

    @PostMapping("/webapp/show-songs/update/{id}")//not working .-.
    public ModelAndView updateSong(@PathVariable("id") long id, @Valid SongDto song, BindingResult result)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/webapp/show-songs");
        songService.save(song);
        return modelAndView;
    }

    /**
     * PLAYLISTS
     *
     */

    @GetMapping(path ="/webapp/show-playlists")
    public ModelAndView showPlaylist()
    {
        ModelAndView modelAndView = new ModelAndView("show-playlist");
        List<PlaylistDto> playlists = playlistService.allPlaylists();
        modelAndView.addObject("playlists", playlists);
        return modelAndView;
    }

    @GetMapping(path ="/webapp/show-playlists/add")
    public ModelAndView showAddForumPlaylist() //not working .-., select don't work
    {
        PlaylistDto playlist = PlaylistDto.builder().build();
        ModelAndView modelAndView = new ModelAndView("add-playlist");
        List<SongDto> songs = songService.allSongs();
        modelAndView.addObject("songs", songs);
        modelAndView.addObject("playlist", playlist);
        return modelAndView;
    }

    @PostMapping("/webapp/show-playlists/addplaylist")
    public ModelAndView addPlaylist(@Valid PlaylistDto playlist, BindingResult result)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/webapp/show-playlists");
        modelAndView.addObject("playlist", playlist);
        playlistService.insert(playlist);
        return modelAndView;
    }


    @GetMapping("/webapp/show-playlists/delete/{id}")
    public ModelAndView deletePlaylist(@PathVariable("id") long id)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/webapp/show-playlists");
        playlistService.delete(id);
        List<PlaylistDto> playlists = playlistService.allPlaylists();
        modelAndView.addObject("playlists", playlists);
        return modelAndView;
    }

    @GetMapping(path ="/webapp/show-playlists/edit/{id}")
    public ModelAndView showEditForumPlaylist(@PathVariable("id") long id)
    {
        PlaylistDto playlist = playlistService.onePlaylist(id);
        ModelAndView modelAndView = new ModelAndView("update-playlist");
        modelAndView.addObject("playlist", playlist);
        return modelAndView;
    }

    @PostMapping("/webapp/show-playlists/update/{id}")
    public ModelAndView updatePlaylist(@PathVariable("id") long id, @Valid PlaylistDto playlist, BindingResult result)
    {
        ModelAndView modelAndView = new ModelAndView("redirect:/webapp/show-playlists");
        playlistService.save(playlist);
        return modelAndView;
    }

    @GetMapping("/webapp/show-playlists/addsong/{id}")
    public ModelAndView showAddSongToPlaylistForm(@PathVariable("id") long id)
    {
        PlaylistDto playlist = playlistService.onePlaylist(id);
        ModelAndView modelAndView = new ModelAndView("add-song-toplaylist");
        modelAndView.addObject("playlist", playlist);
        //List<SongDto> songs = songService.allSongs();
        List<SongDto> songs = songService.allSongs();

        for (SongDto song : songService.allSongs())
        {
            if(song.getPlaylist() != null)
            {
                songs.remove(song);
            }
        }

        modelAndView.addObject("songs", songs);
        return modelAndView;
    }

    @GetMapping("/webapp/show-playlists/addsongtoplaylist/{id}")
    public ModelAndView addSongToPlaylist(@RequestParam Long[] songId, @PathVariable("id") long id)
    {
        PlaylistDto playlist = playlistService.onePlaylist(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/webapp/show-playlists");
        List<Song> songs = Collections.emptyList();
        for (Long sId :songId)
        {
           songs.add(songService.oneSong(sId).toSong());
        }
        playlistService.saveSongs(songs, playlist);
        return modelAndView;
    }

}
