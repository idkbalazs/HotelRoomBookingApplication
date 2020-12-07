package hu.elte.RoomBookingApp.Repositories;

import hu.elte.RoomBookingApp.Entities.Booking;
import hu.elte.RoomBookingApp.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer>{
    Optional<List<Booking>> findByUser(User user);

}
