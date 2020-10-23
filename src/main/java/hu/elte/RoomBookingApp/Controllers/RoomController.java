package hu.elte.RoomBookingApp.Controllers;

import hu.elte.RoomBookingApp.Entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hu.elte.RoomBookingApp.Repositories.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("Rooms")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Room>> getAll() {
        return ResponseEntity.ok(roomRepository.findAll());
    }

    @GetMapping(value = "/{iD}")
    public ResponseEntity<Room> get(@PathVariable Integer iD) {
        Optional<Room> room = roomRepository.findById(iD);
        if (!room.isPresent()) {
            ResponseEntity.notFound();
        }

        return ResponseEntity.ok(room.get());
    }

    @PostMapping("")
    public ResponseEntity<Room> post(@RequestBody Room room) {
        Room newRoom = roomRepository.save(room);
        return ResponseEntity.ok(newRoom);
    }

    @DeleteMapping("/{iD}")
    public ResponseEntity delete(@PathVariable Integer iD) {
        Optional<Room> room = roomRepository.findById(iD);
        if (!room.isPresent()) {
            ResponseEntity.notFound();
        }

        roomRepository.delete(room.get());

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{iD}")
    public ResponseEntity<Room> put(@PathVariable Integer iD, @RequestBody Room room) {
        Optional<Room> oldRoom = roomRepository.findById(iD);
        if (!oldRoom.isPresent()) {
            ResponseEntity.notFound();
        }

        room.setID(iD);
        return ResponseEntity.ok(roomRepository.save(room));
    }

}