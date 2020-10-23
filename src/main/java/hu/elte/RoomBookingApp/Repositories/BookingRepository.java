package hu.elte.RoomBookingApp.Repositories;

import hu.elte.RoomBookingApp.Entities.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer>{

}
