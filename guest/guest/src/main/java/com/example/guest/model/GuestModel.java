package com.example.guest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "guests_table")
public class GuestModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "visit_reason")
    private String visitReason;

    @Column(name = "visit_start_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime visitStartDate = LocalDateTime.parse("2024-08-17T23:24:00");;

    @Column(name = "visit_end_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime visitEndDate = LocalDateTime.parse("2024-08-17T23:24:00");

    @Column(name = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @Column(name = "is_allowed")
    private String  isAllowed = "Not Allowed";

    @Column(name = "national_number")
    private String nationalNumber;

    @Column(name = "check_in")
    private String inCheck = "GİRİŞ YAPMADI";

    @Column(name = "check_in_time")
    private LocalDateTime checkInTime;

    @OneToMany(mappedBy = "guestModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<LogGuestModel> logGuestModels;

    @OneToMany(mappedBy = "guestModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference

    private List<PhotoModel> photos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

    public LocalDateTime getVisitStartDate() {
        return visitStartDate;
    }

    public void setVisitStartDate(LocalDateTime visitStartDate) {
        this.visitStartDate = visitStartDate;
    }

    public LocalDateTime getVisitEndDate() {
        return visitEndDate;
    }

    public void setVisitEndDate(LocalDateTime visitEndDate) {
        this.visitEndDate = visitEndDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getIsAllowed() {
        return isAllowed;
    }

    public void setIsAllowed(String  allowed) {
        this.isAllowed  = allowed;
    }

    public List<LogGuestModel> getLogGuestModels() {
        return logGuestModels;
    }

    public void setLogGuestModels(List<LogGuestModel> logGuestModels) {
        this.logGuestModels = logGuestModels;
    }

    public List<PhotoModel> getPhotoModels() {
        return photos;
    }

    public void setPhotoModels(List<PhotoModel> photos) {
        this.photos = photos;
    }

    public void setCheckIn(String checkIn) {
        this.inCheck = checkIn;
        if ("GİRİŞ YAPTI".equals(checkIn)) {
            this.checkInTime = LocalDateTime.now();
        } else {
            this.checkInTime = null;
        }
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckIn() {
        return inCheck;
    }

    public String getNationalNumber() {
        return nationalNumber;
    }

    public void setNationalNumber(String nationalNumber) {
        this.nationalNumber = nationalNumber;
    }
}
