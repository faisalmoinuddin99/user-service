package com.kakashi.user.service.UserService.service;

import com.kakashi.user.service.UserService.dto.Hotel;
import com.kakashi.user.service.UserService.dto.Rating;
import com.kakashi.user.service.UserService.dto.UserRequest;
import com.kakashi.user.service.UserService.dto.UserResponse;
import com.kakashi.user.service.UserService.exception.ResourceNotFoundException;
import com.kakashi.user.service.UserService.model.User;
import com.kakashi.user.service.UserService.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    private Logger logger = LoggerFactory.getLogger(UserService.class);


    @Override
    public User saveUser(UserRequest userRequest) {
        // mapping User model with UserRequest DTO
        String randomUserId = UUID.randomUUID().toString();
        userRequest.setUserId(randomUserId);
        User user = User.builder()
                .userId(userRequest.getUserId())
                .name(userRequest.getName())
                .email(userRequest.getEmail())
                .about(userRequest.getAbout())
                .build();
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
//        List<User> users = userRepository.findAll();
//        return users.stream().map(user -> mapToUserResponse(user)).toList();
        List<User> users = userRepository.findAll();

        return users;
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given" +
                "id is not found on server !! " + userId));
        /*
        fetch the rating of user by using RATING SERVICE MICROSERVICE
        http://localhost:8083/api/ratings/users/02e4865a-c048-4d2f-bd29-1308211cb321
         */

        Rating[] userRatings = restTemplate.getForObject("http://RATING-SERVICE/api/ratings/users/" + user.getUserId(), Rating[].class);
        logger.info("{} ", userRatings);

        List<Rating> ratings = Arrays.stream(userRatings).toList();

        List<Rating> hotelWithRatingList = ratings.stream().map(rating -> {
            // api call to hotel service to get the hotel
            // http://localhost:8082/api/hotels/01df1884-23a2-49ec-b540-df16fa3c457d
            ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/api/hotels/" + rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelResponseEntity.getBody();
            logger.info("response status code: {} ", hotelResponseEntity.getStatusCode());

            // set the hotel to rating
            rating.setHotel(hotel);

            return rating;
        }).collect(Collectors.toList());


        user.setRatings(hotelWithRatingList);
        return user;
    }

    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .about(user.getEmail())
                .build();
    }
}

