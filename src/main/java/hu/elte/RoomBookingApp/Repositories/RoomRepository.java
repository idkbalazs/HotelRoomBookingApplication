package hu.elte.RoomBookingApp.Repositories;

import hu.elte.RoomBookingApp.Entities.Booking;
import hu.elte.RoomBookingApp.Entities.Room;
import hu.elte.RoomBookingApp.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {
    Optional<List<Room>> findByFloor(Integer floorNumber);
}
