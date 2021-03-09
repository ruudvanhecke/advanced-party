package be.thomasmore.party.repositories;

import be.thomasmore.party.model.Venue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VenueRepository extends CrudRepository<Venue, Integer> {
    Iterable<Venue> findByOutdoor(boolean isOutdoor);
    Iterable<Venue> findByIndoor(boolean isIndoor);
    Iterable<Venue> findByCapacityBetween(int min, int max);
    Iterable<Venue> findByCapacityLessThanEqual(int max);


    @Query("SELECT v FROM Venue v WHERE (:min IS NULL OR v.capacity >= :min) AND (:max IS NULL OR v.capacity <= :max) ")
    List<Venue> findByCapacity(@Param("min") Integer min ,@Param("max") Integer max);


    @Query("SELECT v FROM Venue v WHERE (:min IS NULL OR v.capacity >= :min) AND (:max IS NULL OR v.capacity <= :max) AND " +
            "(:food IS NULL OR v.foodProvided = :food) AND (:indoor is NULL or v.indoor = :indoor) AND (:outdoor is null or v.outdoor = :outdoor)" +
            " AND (:maxKm is null or v.distanceFromPublicTransportInKm <= :maxKm)")
            List<Venue> findByALl(@Param("min") Integer min ,@Param("max") Integer max, @Param("food") Boolean food, @Param("indoor") Boolean indoor, @Param("outdoor") Boolean outdoor, @Param("maxKm") Double maxKm);




  /*  @Query("SELECT v FROM Venue v WHERE v.capacity <= ?1")
    List<Venue> findBySmallCapacity(int max);

    @Query("SELECT v FROM Venue v WHERE v.capacity >= ?1")
    List<Venue> findByBigCapacity(int min);*/

}
