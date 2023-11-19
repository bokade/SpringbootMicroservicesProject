package com.example.UserService.service;

import com.example.UserService.exception.ResourceNotFoundException;
import com.example.UserService.external.service.HotelService;
import com.example.UserService.model.Hotel;
import com.example.UserService.model.Rating;
import com.example.UserService.model.User;
import com.example.UserService.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    public HotelService hotelService;

    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        //generate unique userid
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId)
    {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found on server"));
        //fetch rating of above user using rating service
        //http://localhost:8082/ratings/users/d7bb3462-ca6d-4929-a648-ec56a2696cf7
        //ArrayList<Rating> ratingOfUser = restTemplate.getForObject("http://localhost:8082/ratings/users/"+user.getUserId(), ArrayList.class);
        Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        LOGGER.info("{}",ratingOfUser);
        List<Rating> ratings = Arrays.stream(ratingOfUser).toList();
        List<Rating> ratingList = ratings.stream().map(rating -> {
            //api call to hotel service to get the hotel
            //set the hotel rating
            //return the rating
            //return rating
            //http://localhost:8081/hotels/72a53ffd-a1d1-4af8-9422-6019325820ef
               //ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);

               Hotel hotel = hotelService.getHotel(rating.getHotelId());
//               LOGGER.info("for entity {} ",forEntity);
//               LOGGER.info("response status code {} ",forEntity.getStatusCode());
               rating.setHotel(hotel);
               return rating;

        }).collect(Collectors.toList());
        user.setRatings(ratingList);

        return user;
    }

    
}
