package com.example.repository;

import com.example.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface RatingReposirory extends MongoRepository<Rating,String>
{
  List<Rating> findByUserId(String userId);
  List<Rating> findByHotelId(String hotelId);


}
