package hu.elte.RoomBookingApp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotNull
    private String floor;

    @Column
    @NotNull
    private String room;

    @Column
    @NotNull
    private String arriveDate;

    @Column
    @NotNull
    private String leaveDate;
	
	@OneToMany(mappedBy = "booking")
    private List<Room> rooms;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private User user;
}
