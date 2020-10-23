package hu.elte.RoomBookingApp.Controllers;

import hu.elte.RoomBookingApp.Entities.Booking;
import hu.elte.RoomBookingApp.Repositories.BookingRepository;
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

    @GetMapping("")
    public ResponseEntity<Iterable<Booking>> getAll() {
        return ResponseEntity.ok(bookingRepository.findAll());
    }

    @PostMapping("")
    public ResponseEntity<Booking> post(@RequestBody Booking booking) {
        Booking newBooking = bookingRepository.save(booking);
        return ResponseEntity.ok(newBooking);
    }

    @GetMapping(value = "/myBookings/{iD}")
    public ResponseEntity<Object> getGenre(@PathVariable Integer iD ) {
        Optional<Booking> oBooking = bookingRepository.findById(iD);
        if (!oBooking.isPresent()) {
            ResponseEntity.notFound();
        }

        return new ResponseEntity<Object>(oBooking, HttpStatus.OK);
    }

    @DeleteMapping("/myBookings/{iD}")
    public ResponseEntity delete(@PathVariable Integer iD) {
        Optional<Booking> oBooking = bookingRepository.findById(iD);
        if (!oBooking.isPresent()) {
            ResponseEntity.notFound();
        }

        bookingRepository.delete(oBooking.get());

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