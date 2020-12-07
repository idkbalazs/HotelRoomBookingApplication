package hu.elte.RoomBookingApp.Controllers;

import hu.elte.RoomBookingApp.Entities.Booking;
import hu.elte.RoomBookingApp.Entities.User;
import hu.elte.RoomBookingApp.Repositories.BookingRepository;
import hu.elte.RoomBookingApp.Entities.Room;
import hu.elte.RoomBookingApp.Repositories.RoomRepository;
import hu.elte.RoomBookingApp.Repositories.UserRepository;
import hu.elte.RoomBookingApp.security.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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

import javax.validation.Valid;

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

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/all")
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
    @Secured("ROLE_USER")
    @PostMapping("/{id}")
    public ResponseEntity<Booking> post(@RequestBody @Valid Booking booking, @PathVariable Integer id) {
        Optional<User> oUser = userRepository.findById(id);
        if (!oUser.isPresent()) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        booking.setUser(oUser.get());
        return ResponseEntity.ok(bookingRepository.save(booking));
    }


    @Secured({"ROLE_USER"})
    @GetMapping("/user/{iD}")
    public ResponseEntity<List<Booking>> getByUser(@PathVariable Integer iD) {
        Optional<User> oUser = userRepository.findById(iD);
        Optional<List<Booking>> booking = bookingRepository.findByUser(oUser.get());
        if (!oUser.isPresent()) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        List<Booking> app = booking.isPresent() ? booking.get() : new ArrayList<>();
        return ResponseEntity.ok(app);
    }

    @PutMapping("/{id}/user")
    public ResponseEntity<User> put(@PathVariable Integer id) {
        Optional<Booking> oBooking = bookingRepository.findById(id);
        Optional<User> oUser = userRepository.findById(id);
        if (oBooking.isPresent()) {
            Booking booking = oBooking.get();
            if (oUser.isPresent()) {
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
        if (!oBooking.isPresent()) {
            ResponseEntity.notFound();
        }
        try {
            bookingRepository.delete(oBooking.get());
        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{iD}")
    public ResponseEntity<Booking> put(@PathVariable Integer iD, @RequestBody Booking booking) {
        Optional<Booking> oldBooking = bookingRepository.findById(iD);
        if (!oldBooking.isPresent()) {
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok(bookingRepository.save(booking));
    }

}