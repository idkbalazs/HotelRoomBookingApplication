package hu.elte.RoomBookingApp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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
    private Integer iD;

    @Column
    @NotNull
    private String floor;

    @Column
    @NotNull
    private String room;

    @Column
    @NotNull
    private LocalDate arriveDate;

    @Column
    @NotNull
    private LocalDate leaveDate;

    @ManyToOne
    @JoinColumn
    @JsonIgnoreProperties({"bookings"})
    private User user;
}
