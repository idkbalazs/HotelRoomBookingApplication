package hu.elte.RoomBookingApp.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Length(min = 4, max = 16, message = "Username must be 4-16 character")
    @Column(name = "username", unique = true, nullable = false)
    @NotNull(message = "Username cannot be blank")
    private String username;

    @Column(name = "name",  nullable = false)
    @NotNull(message = "Name cannot be blank")
    private String name;

    @Length(min = 8, message = "Password must be at least 8 characters.")
    @NotNull(message = "Password field must not be empty.")
    @Column(name = "password",nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    @JsonIgnore
	@OneToMany(mappedBy = "user")
    private List<Booking> bookings;

}
