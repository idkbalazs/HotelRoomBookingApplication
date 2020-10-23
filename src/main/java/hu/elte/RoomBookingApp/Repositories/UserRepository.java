package hu.elte.RoomBookingApp.Repositories;

import hu.elte.RoomBookingApp.Entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, Integer>{
    //TODO
}
