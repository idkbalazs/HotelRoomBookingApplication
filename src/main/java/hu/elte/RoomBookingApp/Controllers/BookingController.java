package hu.elte.RoomBookingApp.Controllers;

import hu.elte.RoomBookingApp.Entities.Booking;
import hu.elte.RoomBookingApp.Entities.User;
import hu.elte.RoomBookingApp.Repositories.BookingRepository;
import hu.elte.RoomBookingApp.Entities.Room;
import hu.elte.RoomBookingApp.Repositories.RoomRepository;
import hu.elte.RoomBookingApp.Repositories.UserRepository;
import hu.elte.RoomBookingApp.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Booking>> getAll() {
        return ResponseEntity.ok(bookingRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> get(@PathVariable Integer id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            return ResponseEntity.ok(booking.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<Booking> post(@RequestBody Booking booking) {
        Booking newBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(newBooking);
    }

    @PutMapping("/{id}/user")
    public ResponseEntity<User> put(@PathVariable Integer id) {
        Optional<Booking> oBooking = bookingRepository.findById(id);
        Optional<User> oUser = userRepository.findById(id);
        if (oBooking.isPresent()) {
            Booking booking = oBooking.get();
            if(oUser.isPresent()) {
                User user = oUser.get();
                booking.setUser(user);
                return ResponseEntity.ok(userRepository.save(user));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Booking> oBooking = bookingRepository.findById(id);
        if (oBooking.isPresent()) {
            bookingRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{iD}")
    public ResponseEntity<Booking> put(@PathVariable Integer iD, @RequestBody Booking booking) {
        Optional<Booking> oldBooking = bookingRepository.findById(iD);
        if (!oldBooking.isPresent()) {
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok(bookingRepository.save(booking));
    }

    @GetMapping("/{id}/rooms")
    public ResponseEntity<Iterable<Room>> rooms(@PathVariable Integer id) {
        Optional<Booking> oBooking = bookingRepository.findById(id);
        if (oBooking.isPresent()) {
            return ResponseEntity.ok(oBooking.get().getRooms());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/room/{room_id}")
    public ResponseEntity<Room> insertRoom(@PathVariable Integer id, @PathVariable Integer room_id) {
        Optional<Booking> oBooking = bookingRepository.findById(id);
        Optional<Room> oRoom = roomRepository.findById(room_id);
        if (oBooking.isPresent()) {
            Booking booking = oBooking.get();
            if(oRoom.isPresent()) {
                Room room = oRoom.get();
                room.setBooking(booking);
                return ResponseEntity.ok(roomRepository.save(room));
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}