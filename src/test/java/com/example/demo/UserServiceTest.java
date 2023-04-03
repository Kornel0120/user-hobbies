package com.example.demo;

import com.example.demo.entity.HobbyEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.UserHobbyEntity;
import com.example.demo.exceptions.AlreadyExistsException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.model.Hobby;
import com.example.demo.model.User;
import com.example.demo.model.UserHobby;
import com.example.demo.model.enums.Gender;
import com.example.demo.model.mapper.UserMapper;
import com.example.demo.repository.HobbyRepository;
import com.example.demo.repository.UserHobbyRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    @Mock
    HobbyRepository hobbyRepository;
    @Mock
    UserHobbyRepository userHobbyRepository;

    @Test
    void getUsersHappyPath() {
        final UserEntity userEntity = getUserEntity(999L, "test", "user", 21, Gender.MALE);
        final User user = getUser(999L, "test", "user", 21, Gender.MALE);
        final List<UserEntity> userEntities = List.of(
                userEntity
        );
        final List<User> expectedUsers = List.of(
                user
        );
        when(userRepository.findAll()).thenReturn(userEntities);
        final List<User> actual = userService.getUsers();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedUsers);
    }

    @Test
    void postUserHappyPath() throws AlreadyExistsException {
        final UserEntity userEntity = getUserEntity(999L, "test", "user", 21, Gender.MALE);
        final User user = getUser(999L, "test", "user", 21, Gender.MALE);
        when(userRepository.save(any())).thenReturn(userEntity);
        final User actual = userService.postUser(user);
        assertThat(actual).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    void postUserThrowsAlreadyExistsException() throws AlreadyExistsException {
        final User user = getUser(999L, "test", "user", 21, Gender.MALE);
        when(userRepository.existsById(user.getUserId())).thenReturn(true);
        assertThatThrownBy(() -> userService.postUser(user)).isInstanceOf(AlreadyExistsException.class);
    }

    @Test
    void getHobbiesHappyPath() {
        final Hobby hobby = getHobby(999L, "test-hobby");
        final HobbyEntity hobbyEntity = getHobbyEntity(999L, "test-hobby");
        final List<HobbyEntity> hobbyEntities = List.of(
                hobbyEntity
        );
        final List<Hobby> expectedHobbies = List.of(
                hobby
        );
        when(hobbyRepository.findAll()).thenReturn(hobbyEntities);
        final List<Hobby> actual = userService.getHobbies();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHobbies);
    }

    @Test
    void getHobbiesByUserIdHappyPath() throws NotFoundException {
        final UserEntity userEntity = getUserEntity(999L, "test", "user", 21, Gender.MALE);
        final HobbyEntity hobbyEntity = getHobbyEntity(999L, "test-hobby");
        final UserHobbyEntity userHobbyEntity = getUserHobbyEntity(999L, userEntity, hobbyEntity, Duration.ofHours(1), Timestamp.valueOf("2021-01-01 00:00:00"));
        final UserHobby userHobby = getUserHobby(999L, userEntity, hobbyEntity, Duration.ofHours(1), Timestamp.valueOf("2021-01-01 00:00:00"));
        final List<UserHobbyEntity> userHobbyEntities = List.of(
                userHobbyEntity
        );
        final List<UserHobby> expectedUserHobbies = List.of(
                userHobby
        );
        when(userRepository.existsById(userEntity.getUserId())).thenReturn(true);
        when(userHobbyRepository.getHobbiesByUserUserId(userEntity.getUserId())).thenReturn(userHobbyEntities);
        final List<UserHobby> actual = userService.getHobbiesByUserId(userEntity.getUserId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedUserHobbies);
    }

    @Test
    void getHobbiesByUserIdThrowsNotFoundException() throws NotFoundException {
        when(!userRepository.existsById(999L)).thenReturn(false);
        assertThatThrownBy(() -> userService.getHobbiesByUserId(999L)).isInstanceOf(NotFoundException.class);
    }

    private UserEntity getUserEntity(long id, String firstName, String lastName, int age, Gender gender) {
        return new UserEntity(id, firstName, lastName, age, gender);
    }

    private User getUser(long id, String firstName, String lastName, int age, Gender gender) {
        return new User(id, firstName, lastName, age, gender);
    }

    private HobbyEntity getHobbyEntity(long id, String name) {
        return new HobbyEntity(id, name);
    }

    private Hobby getHobby(long id, String name) {
        return new Hobby(id, name);
    }

    private UserHobbyEntity getUserHobbyEntity(long id, UserEntity userEntity, HobbyEntity hobbyEntity, Duration duration, Timestamp lastDone) {
        return new UserHobbyEntity(id, userEntity, hobbyEntity, duration, lastDone);
    }

    private UserHobby getUserHobby(long id, UserEntity userEntity, HobbyEntity hobbyEntity, Duration duration, Timestamp lastDone) {
        return new UserHobby(id, userEntity, hobbyEntity, duration, lastDone);
    }
}
