package com.example.guest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "guest_photos")
public class PhotoModel {
//C:\Users\erena\Desktop\WeakCodes\Java\guest\guest\src\main\resources\static\Photo\Images

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "guest_id")
    @JsonBackReference
    private GuestModel guestModel;

    @Column(name = "photo_path")
    private String photoPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GuestModel getGuestModel() {
        return guestModel;
    }

    public void setGuestModel(GuestModel guest) {
        this.guestModel = guest;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
