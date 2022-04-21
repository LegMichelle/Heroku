package at.spengergasse.legado.repository;

import at.spengergasse.legado.model.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CrudRepository <Song, Long>
{

}
