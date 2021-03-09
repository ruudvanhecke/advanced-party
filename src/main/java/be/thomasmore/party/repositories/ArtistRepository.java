package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    Iterable<Artist> findByArtistNameLike(String keyWord);

    @Query("SELECT a FROM Artist a " +
            "WHERE ?1 is NULL " +
            "OR LOWER(a.artistName) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            "OR LOWER(a.bio) LIKE LOWER(CONCAT('%', ?1, '%'))" +
            "OR LOWER(a.genre) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Artist> findByKeyword(@Param("word") String word);
}