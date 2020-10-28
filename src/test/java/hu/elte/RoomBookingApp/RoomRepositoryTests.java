package hu.elte.RoomBookingApp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.*; //Assert.assertEquals;
import static org.junit.*; //Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.junit.jupiter.api.Test;
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
		Room room = new Room(1, 2, 1, 10, false, null);
		roomRepository.save(room);
		Optional<Room> roomFromRepo = roomRepository.findById(1);
		Room room2 = roomFromRepo.get();
		assertNotNull(room2);
		assertEquals(room.getCapacity(), room2.getCapacity());
		assertEquals(room.getFloor(), room2.getFloor());
		assertEquals(room.getRoomNumber(), room2.getRoomNumber());
		assertEquals(room, room2);
	}
	
	@Test
    public void testDeleteRoom() {
		Room room = new Room(1, 2, 1, 10, false, null);
		roomRepository.save(room);
		roomRepository.delete(room);
	}
	
	@Test
    public void testFindAllRooms() {
		Room room = new Room(1, 2, 1, 10, false, null);
		roomRepository.save(room);
		assertNotNull(roomRepository.findAll());
	}
	
	@Test
    public void testFindAllRooms2() {
		
		Room room = new Room();
		room.setCapacity(2);
		room.setFloor(1);
		room.setRoomNumber(10);
		room.setReserved(false);
		
		Room room2 = new Room();
		room2.setCapacity(2);
		room2.setFloor(1);
		room2.setRoomNumber(11);
		room2.setReserved(false);
		
		List<Room> roomsToSave = new ArrayList<Room>();
		roomsToSave.add(room);
		roomsToSave.add(room2);
		roomRepository.saveAll(roomsToSave);
		assertNotNull(roomRepository.findAll());
		assertEquals(roomRepository.findAll(), roomsToSave);
	}
	

	@Test
    public void testDeleteRoomById() {
		Room room = new Room(1, 2, 1, 10, false, null);
		Room r = roomRepository.save(room);
		roomRepository.deleteById(r.getID());
	}
	
	

}
