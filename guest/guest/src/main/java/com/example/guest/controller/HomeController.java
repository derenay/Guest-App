package com.example.guest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/auth/login"; // Redirect to login if not authenticated
        }
        return "visitors";
    }

    @GetMapping("/guestlist")
    public String guestList(HttpSession session) {
        if (session.getAttribute("user") == null) {
            
            return "redirect:/auth/login"; // Redirect to login if not authenticated
        }
        return "guestlist";
    }

    @GetMapping("/visitors")
    public String visitors(HttpSession session)
    {
        if (session.getAttribute("user") == null) {
            return "redirect:/auth/login"; // Redirect to login if not authenticated
        }else{
            return "visitors";
        }
    }

    @GetMapping("/register")
    public String registerGuest(HttpSession session){
        if (session.getAttribute("user") == null) {
            return "redirect:/auth/login"; // Redirect to login if not authenticated
        }else {
            return "registerGuest";
        }
    }

    @GetMapping("/registerphone")
    public String registerGuestphone(HttpSession session){
        return "phoneRegister";
    }


    @GetMapping("/request")
    public String requestpage(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "redirect:/auth/login"; // Redirect to login if not authenticated
        } else {
            return "visitorRequests";
        }
    }

}
