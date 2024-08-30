package com.example.guest.task;

import com.example.guest.model.GuestModel;
import com.example.guest.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduledTask {

    @Autowired
    private GuestRepository guestRepository;

    @Scheduled(cron = "0 0 0 * * *") // Her gün gece yarısı çalışır
    public void resetCheckInTimeAtEndOfDay() {
        List<GuestModel> guests = guestRepository.findAll();

        for (GuestModel guest : guests) {
            if ("GİRİŞ YAPTI".equals(guest.getCheckIn())) {
                guest.setCheckInTime(null);
                guest.setCheckIn("GİRİŞ YAPMADI");
                guestRepository.save(guest);
            }
        }
    }
}
