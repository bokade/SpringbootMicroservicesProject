package com.example.UserService.external.service;

import com.example.UserService.model.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Map;
@Service
@FeignClient(name="RATING-SERVICE")
public interface RatingService
{
      @PostMapping("/ratings")
      public ResponseEntity<Rating> createRating(Rating values);

      @PutMapping("ratings/{ratingId}")
      public ResponseEntity<Rating> updatRating(@PathVariable("ratingId") String ratingId, Rating rating);

      @DeleteMapping("/ratings/{ratingId}")
      public void deleteRating(@PathVariable String ratingId);


}
