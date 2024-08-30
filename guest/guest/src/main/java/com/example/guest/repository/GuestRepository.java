package com.example.guest.repository;

import com.example.guest.model.GuestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<GuestModel, Integer> {

    //List<GuestModel> findByisAllowed(Boolean is_allowed);

    List<GuestModel> findByfirstname(String firstname);

    List<GuestModel> findByIsAllowed(String isAllowed);

    List<GuestModel> findByinCheck(String checkIn);


}
