package hu.elte.RoomBookingApp.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    @NotNull
    private Integer price;

    @Column
    @NotNull
    private String arriveDate;

    @Column
    @NotNull
    private String leaveDate;

    @JsonIgnore
    @ManyToMany
    private List<Room> rooms;

}
