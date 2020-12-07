package hu.elte.RoomBookingApp;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import hu.elte.RoomBookingApp.Repositories.BookingRepository;
import hu.elte.RoomBookingApp.Repositories.RoomRepository;
import hu.elte.RoomBookingApp.Repositories.UserRepository;
import hu.elte.RoomBookingApp.security.AuthenticatedUser;
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    AuthenticatedUser authenticatedUser;

    @MockBean
    BookingRepository bookingRepository;

    @MockBean
    RoomRepository roomRepository;

    @MockBean
    UserRepository userRepository;

<<<<<<< HEAD
    @MockBean
    private BCryptPasswordEncoder passwordEncoder;
    @WithMockUser(value = "MVCtestprofile")
    @Test
    void usersTest() throws Exception {
        mockMvc.perform(get("/allusers").contentType("application/json")).andExpect(status().is(404));
    }
    @Test
    void whenUserNotAuthenticated_thenReturns401() throws Exception {
        mockMvc.perform(get("/bookings").contentType("application/json")).andExpect(status().is(401));
    }
=======
	@MockBean
	AuthenticatedUser authenticatedUser;
	
	@MockBean
	BookingRepository bookingRepository;
	
	@MockBean
	RoomRepository roomRepository;
	
	@MockBean
	UserRepository userRepository;
	
	@MockBean
	private BCryptPasswordEncoder passwordEncoder;

	@WithMockUser(value = "MVCtestprofile")
	@Test
	void usersTest() throws Exception {
	    mockMvc.perform(get("/allusers").contentType("application/json")).andExpect(status().is(404));
	}
>>>>>>> 99941917fe381178a96d373d4e3bfdf16136228a

}
