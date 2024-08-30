package com.example.guest.controller;

import com.example.guest.model.GuestModel;
import com.example.guest.model.UserModel;
import com.example.guest.service.GuestService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    private GuestService guestService;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(GuestController.class);

    //@GetMapping("/allowed")
    //public List<GuestModel> getAllowedGuests(){
    //    return guestService.getAllowedGuests();
    //}

    @GetMapping("/{id}")
    public GuestModel getGuestById(@PathVariable Integer id, HttpSession session)
    {
        if (session.getAttribute("user") == null) {
            return null;
        }else return guestService.getGuestById(id);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGuest(
            @PathVariable Integer id,
            @RequestBody GuestModel guestDetails,
            HttpSession session) {

        if (session.getAttribute("user") == null) {
            return null;
        }else {
            logger.info("Received update request for guest with ID: {}", id);

            // Gelen verileri kontrol etmek için log
            logger.debug("Guest details received: {}", guestDetails);

            try {
                GuestModel existingGuest = guestService.getGuestById(id);

                if (existingGuest == null) {
                    logger.warn("Guest with ID {} not found", id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Guest not found");
                }

                // Güncellemeyi gerçekleştir
                GuestModel updatedGuest = guestService.updateGuest(id, guestDetails);

                // Başarılı güncellemeyi logla
                logger.info("Successfully updated guest with ID: {}", id);
                return ResponseEntity.ok(updatedGuest);

            } catch (Exception e) {
                // Hata durumunda logla
                logger.error("Error updating guest with ID: {} - {}", id, e.getMessage(), e);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input: " + e.getMessage());
            }
        }
    }

    @GetMapping
    public List<GuestModel> getAllGuests(HttpSession session){
        if (session.getAttribute("user") == null) {
            return null;
        }else {
            return guestService.getAllGuests();
        }
    }

    @GetMapping("/approval")
    public List<GuestModel> getApprovals(HttpSession session){
        if(session.getAttribute("user") == null){
            return null;
        }else {
            return guestService.getApprovals();
        }

    }

    @PutMapping("/approval")
    public ResponseEntity<Map<String, String>> approveGuest(@RequestBody GuestModel guestModel, HttpSession session) {
        try {
            guestService.updateGuestApproval(guestModel.getId());
            Map<String, String> response = new HashMap<>();
            response.put("message", "Guest updated successfully!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log the exception (for debugging purposes)
            e.printStackTrace();

            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Failed to update guest.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


    @PostMapping
    public ResponseEntity<GuestModel> saveGuest(
            @RequestPart("guest") GuestModel guestModel,
            @RequestPart("file") MultipartFile file,
            HttpSession session) {

        if (session.getAttribute("user") == null) {
            return null;
        }else {
            try {
                GuestModel newGuest = guestService.saveGuest(guestModel, file);
                return ResponseEntity.ok(newGuest);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteGuest(@PathVariable Integer id, HttpSession session) {

        UserModel user = (UserModel) session.getAttribute("user");

        if (user == null) {
            System.out.println();
        } else if (user.getRole().equals("ADMIN")) {
            guestService.deleteGuest(id);
        }
    }

    @GetMapping("/request")
    public List<GuestModel> getVisitorRequests(HttpSession session){
        if (session.getAttribute("user") == null) {
            return null;
        }else{
            return guestService.getVisitorRequest();
        }

    }

}
