package com.example.apiproject.controller;


import com.example.apiproject.entity.Animal;
import com.example.apiproject.entity.Donation;
import com.example.apiproject.service.DonationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/donation")
public class DonationController {

    private final DonationService donationService;


    @PostMapping("/donate")
    public ResponseEntity<Donation> makeDonation(@RequestBody Donation donation, @RequestHeader("Authorization") String token){
        return new ResponseEntity<>(donationService.create(donation,token), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Donation>> getAllDonations(){
        return new ResponseEntity<>(donationService.showAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> getDonationInfo(@PathVariable Integer id){
        return new ResponseEntity<>(donationService.showDetails(id),HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Donation>> getDonationByUser(@PathVariable Integer id){
        return new ResponseEntity<>(donationService.showAllUserDonations(id),HttpStatus.OK);
    }

    @GetMapping("/animal/{id}")
    public ResponseEntity<List<Donation>> getDonationByAnimal(@PathVariable Integer id){
        return new ResponseEntity<>(donationService.showDonationsPerAnimal(id),HttpStatus.OK);
    }


}
