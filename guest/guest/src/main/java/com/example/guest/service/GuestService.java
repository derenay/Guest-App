package com.example.guest.service;


import com.example.guest.model.GuestModel;
import com.example.guest.model.PhotoModel;
import com.example.guest.repository.GuestRepository;
import com.example.guest.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {


    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private PhotoRepository photoRepository;

    public List<GuestModel> getAllGuests() {
        return guestRepository.findAll();
    }

    public GuestModel saveGuest(GuestModel guestModel,MultipartFile file) throws IOException {
        GuestModel savedGuest = guestRepository.save(guestModel);

        //Eğer fotoğraf dosyası gönderildiyse
        if (file != null && !file.isEmpty()) {
            // Dosyayı belirlenen dizine kaydet
            String UPLOAD_DIR = "C:\\Users\\erena\\Desktop\\WeakCodes\\Java\\guest\\guest\\src\\main\\resources\\static\\Photo\\Images";
            String filePath = UPLOAD_DIR + "/" + file.getOriginalFilename();
            File dest = new File(filePath);
            file.transferTo(dest);

            // photoModel oluştur ve veritabanına kaydet
            PhotoModel photo = new PhotoModel();
            photo.setPhotoPath(filePath);
            photo.setGuestModel(savedGuest);

            // Fotoğrafı kaydet
            photoRepository.save(photo);
        }

        return savedGuest;
    }

    public void deleteGuest(Integer id) {
        guestRepository.deleteById(id);
    }

    // This three methods are gonna stay here


    //public List<GuestModel> getAllowedGuests(){
    //    return guestRepository.findByisAllowed(true);
    //}


    public GuestModel getGuestById(Integer id) {
        Optional<GuestModel> optionalGuest = guestRepository.findById(id);
        return optionalGuest.orElse(null);
    }

    public List<GuestModel> getApprovals() {
        return guestRepository.findByIsAllowed("Not Allowed");
    }

    public List<GuestModel> getByName(String name) {
        return guestRepository.findByfirstname(name);

    }

    public GuestModel updateGuest(Integer id, GuestModel guestDetails) {
        Optional<GuestModel> optionalGuest = guestRepository.findById(id);
        if (optionalGuest.isPresent()) {
            GuestModel guest = optionalGuest.get();
            guest.setFirstname(guestDetails.getFirstname());
            guest.setLastname(guestDetails.getLastname());
            guest.setEmail(guestDetails.getEmail());
            guest.setPhoneNumber(guestDetails.getPhoneNumber());
            guest.setVisitReason(guestDetails.getVisitReason());
            guest.setVisitStartDate(guestDetails.getVisitStartDate());
            guest.setVisitEndDate(guestDetails.getVisitEndDate());
            guest.setIsAllowed(guestDetails.getIsAllowed());

            return guestRepository.save(guest);
        } else {
            return null;
        }
    }


    public void updateGuestApproval(Integer id) throws Exception {
        GuestModel guest = guestRepository.findById(id)
                .orElseThrow(() -> new Exception("Guest not found"));

        guest.setIsAllowed("Allowed");
        guestRepository.save(guest);

    }

    public List<GuestModel> getVisitorRequest() {
        String checkIn = "GİRİŞ YAPTI";
        return guestRepository.findByinCheck(checkIn);
    }



}
