package com.example.UserService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "micro_users")
public class User
{
    @Id
    private String userId;

    private String name;
    private String email;
    private String about;

    @Transient
    private List<Rating> ratings = new ArrayList<>();


}
