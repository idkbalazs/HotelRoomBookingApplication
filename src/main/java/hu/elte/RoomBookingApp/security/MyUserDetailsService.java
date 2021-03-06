package hu.elte.RoomBookingApp.security;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import hu.elte.RoomBookingApp.Entities.User;
import hu.elte.RoomBookingApp.Exceptions.ResourceNotFoundException;
import hu.elte.RoomBookingApp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticatedUser authenticatedUser;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> oUser = userRepository.findByUsername(username);
		if (!oUser.isPresent()) {
			throw new UsernameNotFoundException(username);
		}
		User user = oUser.get();
		authenticatedUser.setUser(user);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
	public User findById(Integer iD) {
		return userRepository.findById(iD).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", iD));
	}
	public void changePassword(Integer iD, User userDetails) {
		User user = findById(iD);
		user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
		userRepository.save(user);
	}
}