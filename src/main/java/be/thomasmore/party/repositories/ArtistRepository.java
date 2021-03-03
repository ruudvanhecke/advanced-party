package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    Iterable<Artist> findByArtistNameLike(String keyWord);
    Iterable<Artist> findByArtistNameContainingIgnoreCase(String KeyWord);
}