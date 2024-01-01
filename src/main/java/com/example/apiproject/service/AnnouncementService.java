package com.example.apiproject.service;


import com.example.apiproject.config.JwtService;
import com.example.apiproject.entity.Announcement;
import com.example.apiproject.entity.User;
import com.example.apiproject.exceptions.MissingFieldException;
import com.example.apiproject.exceptions.ResourceNotFoundException;
import com.example.apiproject.exceptions.UserNotFoundException;
import com.example.apiproject.repository.AnnouncementRepository;
import com.example.apiproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    private final UserService userService;

    public Announcement create(Announcement announcement, String token) {
        // Null checks
        if (announcement == null || token == null) {
            throw new MissingFieldException("Announcement or token are missing");
        }
        var user = userService.findUserByToken(token);
        announcement.setUser(user);

        announcementRepository.save(announcement);

        return announcement;
    }

    public Announcement update(Announcement announcement, Integer id, String authorizationHeader) {
        if(id == null || authorizationHeader == null){
            throw new MissingFieldException("id or token are missing");
        }
        var user = userService.findUserByToken(authorizationHeader);
        var updatedAnnouncement = announcementRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Announcement not exist with id: " + id));
        updatedAnnouncement.setUser(user);
        if (announcement != null) {
            if ( announcement.getDescription() != null && !announcement.getDescription().isBlank()) {
                updatedAnnouncement.setDescription(announcement.getDescription());
            }
            if ( announcement.getDescription() != null && !announcement.getTitle().isBlank()) {
                updatedAnnouncement.setTitle(announcement.getTitle());
            }
        }
        announcementRepository.save(updatedAnnouncement);
        return updatedAnnouncement;


    }

    public List<Announcement> getAllAnnouncements(){
        return  announcementRepository.findAll();
    }
}
