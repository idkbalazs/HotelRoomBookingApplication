package hu.elte.RoomBookingApp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.Optional;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hu.elte.RoomBookingApp.Entities.Room;
import hu.elte.RoomBookingApp.Repositories.RoomRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTests {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Test
	  public void injectedComponentsAreNotNull(){
	    assertThat(roomRepository).isNotNull();
	}
	
	
	@Test
    public void testSaveRoom() {
		Room room = new Room(1, 2, 1);
		roomRepository.save(room);
		Optional<Room> roomFromRepo = roomRepository.findById(1);
		Room room2 = roomFromRepo.get();
		assertNotNull(room2);
		assertEquals(room.getFloor(), room2.getFloor());
		assertEquals(room.getRoomNumber(), room2.getRoomNumber());
		assertEquals(room, room2);
	}
	
	@Test
    public void testDeleteRoom() {
		Room room = new Room(1, 2, 1);
		roomRepository.save(room);
		roomRepository.delete(room);
	}
	
	@Test
    public void testFindAllRooms() {
		Room room = new Room(1, 2, 1);
		roomRepository.save(room);
		assertNotNull(roomRepository.findAll());
	}
	

	@Test
    public void testDeleteRoomById() {
		Room room = new Room(1, 2, 1);
		Room r = roomRepository.save(room);
		roomRepository.deleteById(r.getID());
	}

}
