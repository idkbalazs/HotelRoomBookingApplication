package hu.elte.RoomBookingApp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;//Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;

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

}
