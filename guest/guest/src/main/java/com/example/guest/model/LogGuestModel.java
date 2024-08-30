package com.example.guest.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "guest_logs")
public class LogGuestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private GuestModel guestModel;

    @Column(name = "check_in_time")
    private LocalDateTime check_in_time;

    @Column(name = "check_out_time")
    private LocalDateTime  check_out_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GuestModel getGuestModelLog() {
        return guestModel;
    }

    public void setGuestModelLog(GuestModel guestModel) {
        this.guestModel = guestModel;
    }

    public LocalDateTime getCheck_in_time() {
        return check_in_time;
    }

    public void setCheck_in_time(LocalDateTime check_in_time) {
        this.check_in_time = check_in_time;
    }

    public LocalDateTime getCheck_out_time() {
        return check_out_time;
    }

    public void setCheck_out_time(LocalDateTime check_out_time) {
        this.check_out_time = check_out_time;
    }
}
