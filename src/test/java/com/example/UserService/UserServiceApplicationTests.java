package com.example.UserService;

import com.example.UserService.external.service.RatingService;
import com.example.UserService.model.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private RatingService ratingService;


//	@Test
//	void createRating(){
//		Rating rating = Rating.builder().rating(10).userId("").hotelId("").feedback("This is created using feign client").build();
//		ResponseEntity<Rating> ratingResponseEntity = ratingService.createRating(rating);
//
//		System.out.println("new rating created");
//	}

}
