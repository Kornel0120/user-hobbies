package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.entity.HobbyEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserHobbyEntity;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.mapper.Mapper;
import com.example.demo.model.Hobby;
import com.example.demo.model.User;
import com.example.demo.model.UserHobby;
import com.example.demo.model.enums.Gender;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    @Mock
    Mapper mapper;

    @BeforeEach
    public void setup() {
        this.userController = new UserController(userService);
    }

    @Test
    void getUsersHappyPath() {
        when(userService.getUsers()).thenReturn(List.of(TestDataProvider.getUser()));
        ResponseEntity<List<User>> expected = ResponseEntity.ok(List.of(TestDataProvider.getUser()));
        ResponseEntity<List<User>> actual = userController.getUsers();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void registerUserHappyPath() throws AlreadyExistsException {
        User user = TestDataProvider.getUser();
        ResponseEntity<String> expected = ResponseEntity.ok("User " + user.getFirstName() + " " + user.getLastName() + " was registered successfully!");
        when(userService.postUser(any())).thenReturn(user);
        ResponseEntity<String> actual = userController.registerUser(user);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void registerUserThrowsResponseStatusException() throws AlreadyExistsException{
        User user = TestDataProvider.getUser();
        when(userService.postUser(any())).thenThrow(AlreadyExistsException.class);
        assertThatThrownBy(() -> {
            userController.registerUser(user);})
                .isInstanceOf(ResponseStatusException.class);
    }

    @Test
    void hobbiesHappyPath() {
        when(userService.getHobbies()).thenReturn(List.of(TestDataProvider.getHobby()));
        ResponseEntity<List<Hobby>> expected = ResponseEntity.ok(List.of(TestDataProvider.getHobby()));
        ResponseEntity<List<Hobby>> actual = userController.getHobbies();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getHobbiesForASpecificUserHappyPath() throws NotFoundException {
        when(userService.getHobbiesByUserId(any())).thenReturn(List.of(TestDataProvider.getUserHobby()));
        ResponseEntity<List<UserHobby>> expected = ResponseEntity.ok(List.of(TestDataProvider.getUserHobby()));
        ResponseEntity<List<UserHobby>> actual = userController.getHobbiesForASpecificUser(1L);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    void getHobbiesForASpecificUserThrowsResponseStatusException() throws NotFoundException {
        when(userService.getHobbiesByUserId(any())).thenThrow(NotFoundException.class);
        assertThatThrownBy(() -> {
            userController.getHobbiesForASpecificUser(TestDataProvider.userId);})
                .isInstanceOf(ResponseStatusException.class);
    }

    private static class TestDataProvider {
        private static Long hobbyId = 9999L;
        private static String name = "test hobby";
        private static Long userId = 9999L;
        private static String firstName = "test first name";
        private static String lastName = "test last name";
        private static int age = 99;
        private static Gender gender = Gender.MALE;
        private static Long id = 9999L;
        private static UserEntity userEntity = new UserEntity(hobbyId,firstName,lastName,age,gender);
        private static HobbyEntity hobbyEntity = new HobbyEntity(hobbyId,name);
        private static Duration duration = Duration.ofHours(1);
        private static Timestamp lastDone = Timestamp.valueOf("2020-01-01 00:00:00");

        private static UserHobby getUserHobby() {
            return new UserHobby(id,userEntity,hobbyEntity,duration,lastDone);
        }

        private static UserHobbyEntity getUserHobbyDto() {
            return UserHobbyEntity.builder()
                    .id(id)
                    .user(userEntity)
                    .hobby(hobbyEntity)
                    .duration(duration)
                    .lastDone(lastDone)
                    .build();
        }

        private static User getUser() {
            return new User(userId,firstName,lastName,age,gender);
        }

        private static Hobby getHobby() {
            return new Hobby(hobbyId,name);
        }
    }
}
