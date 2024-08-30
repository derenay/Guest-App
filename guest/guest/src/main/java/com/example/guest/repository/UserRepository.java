package com.example.guest.repository;


import com.example.guest.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    //@Query("select UserModel.password_account FROM UserModel where UserModel.username = :username")
    UserModel findByUsername(String username);

}
