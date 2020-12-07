package hu.elte.RoomBookingApp.Entities;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer iD;

    @Column
    @NotNull
    private Integer floor;

    @Column
    @NotNull
    private Integer roomNumber;

}
