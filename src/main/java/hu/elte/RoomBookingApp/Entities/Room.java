package hu.elte.RoomBookingApp.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

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
    private Integer capacity;

    @Column
    @NotNull
    private Integer floor;

    @Column
    @NotNull
    private Integer roomNumber;

    @Column
    @NotNull
    private boolean reserved;
}