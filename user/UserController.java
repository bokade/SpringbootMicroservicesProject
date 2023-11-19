package com.example.UserService.controller;

import com.example.UserService.model.User;
import com.example.UserService.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<User> CreateUser(@RequestBody User user)
    {

        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
//        return new ResponseEntity<>(user1,HttpStatus.CREATED);
    }
    int retryCount=1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker",fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelService",fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name="userRateLimiter",fallbackMethod="ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId)
    {
        logger.info("Get Single User Handler: UserController");
        logger.info("Retry count: {}",retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }



    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex)
    {
//          ex.printStackTrace();
//       logger.info("Fallback is executed because service is down: ", ex.getMessage());
       User user = User.builder()
               .email("sb@gmail.com")
               .name("swapnil")
               .about("This user is created dummy because some service is down")
               .userId("141234")
               .build();
       return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getSingleUser()
    {
        List<User> alluser = userService.getAllUser();
        return ResponseEntity.ok(alluser);
    }




}
