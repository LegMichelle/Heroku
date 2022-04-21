package at.spengergasse.legado.repository;

import at.spengergasse.legado.model.Playlist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends CrudRepository <Playlist, Long>
{

}
