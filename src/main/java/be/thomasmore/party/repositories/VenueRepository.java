package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    Iterable<Venue> findByOutdoor(boolean isOutdoor);
    Iterable<Venue> findByIndoor(boolean isIndoor);
    Iterable<Venue> findByCapacityBetween(int min, int max);
    Iterable<Venue> findByCapacityGreaterThan(int min);
    Iterable<Venue> findByCapacityGreaterThanEqual(int min);
    Iterable<Venue> findByCapacityLessThanEqual(int max);


    @Query("SELECT v FROM Venue v WHERE v.capacity BETWEEN ?1 AND ?2")
    List<Venue> findByCapacityBetweenQuery(int min, int max);
}
