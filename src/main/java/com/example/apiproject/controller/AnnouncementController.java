package com.example.apiproject.controller;


import com.example.apiproject.entity.Announcement;
import com.example.apiproject.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/announcement")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @PostMapping("/create")
    public ResponseEntity<Announcement> createAnnouncement( @RequestBody Announcement announcement, @RequestHeader("Authorization") String authorizationHeader)  {
        return ResponseEntity.ok(announcementService.create(announcement,authorizationHeader));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Announcement> updateAnnouncement (@PathVariable Integer id, @RequestBody Announcement announcement,@RequestHeader("Authorization") String authorizationHeader){
        return ResponseEntity.ok(announcementService.update(announcement,id,authorizationHeader));
    }

    @GetMapping
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        List<Announcement> announcements = announcementService.getAllAnnouncements();
        return new ResponseEntity<>(announcements, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAnnouncement(@PathVariable Integer id,@RequestHeader("Authorization") String authorizationHeader){
        return  ResponseEntity.ok(announcementService.delete(id,authorizationHeader));
    }

}
