package com.example.demo;

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
import com.example.demo.repository.HobbyRepository;
import com.example.demo.repository.UserHobbyRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;
    @Mock
    HobbyRepository hobbyRepository;
    @Mock
    UserHobbyRepository userHobbyRepository;
    @Mock
    Mapper mapper;

    @Test
    void getUsersHappyPath() {
        List<UserEntity> userEntities = List.of(
                TestDataProvider.userEntity
        );
        List<User> expectedUsers = List.of(
                TestDataProvider.getUser()
        );
        when(userRepository.findAll()).thenReturn(userEntities);
        when(mapper.userEntityToUser(TestDataProvider.userEntity)).thenReturn(TestDataProvider.getUser());
        List<User> actual = userService.getUsers();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedUsers);
    }

    @Test
    void postUserHappyPath() throws AlreadyExistsException {
        User user = TestDataProvider.getUser();
        UserEntity userEntity = TestDataProvider.userEntity;
        when(mapper.userToUserEntity(user)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(mapper.userEntityToUser(userEntity)).thenReturn(user);
        User actual = userService.postUser(user);
        assertThat(actual).usingRecursiveComparison().isEqualTo(user);
    }

    @Test
    void postUserThrowsAlreadyExistsException() throws AlreadyExistsException {
        User user = TestDataProvider.getUser();
        when(userRepository.existsById(user.getUserId())).thenReturn(true);
        assertThatThrownBy(() -> userService.postUser(user)).isInstanceOf(AlreadyExistsException.class);
    }

    @Test
    void getHobbiesHappyPath() {
        List<HobbyEntity> hobbyEntities = List.of(
                TestDataProvider.hobbyEntity
        );
        List<Hobby> expectedHobbies = List.of(
                TestDataProvider.getHobby()
        );
        when(hobbyRepository.findAll()).thenReturn(hobbyEntities);
        when(mapper.hobbyEntityToHobby(TestDataProvider.hobbyEntity)).thenReturn(TestDataProvider.getHobby());
        List<Hobby> actual = userService.getHobbies();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedHobbies);
    }

    @Test
    void getHobbiesByUserIdHappyPath() throws NotFoundException {
        List<UserHobbyEntity> userHobbyEntities = List.of(
                TestDataProvider.getUserHobbyEntity()
        );
        List<UserHobby> expectedUserHobbies = List.of(
                TestDataProvider.getUserHobby()
        );
        when(userRepository.existsById(TestDataProvider.userId)).thenReturn(true);
        when(userHobbyRepository.getHobbiesByUserUserId(TestDataProvider.userId)).thenReturn(userHobbyEntities);
        doReturn(TestDataProvider.getUserHobby()).when(mapper).userHobbyEntityToUserHobby(any(UserHobbyEntity.class));
        List<UserHobby> actual = userService.getHobbiesByUserId(TestDataProvider.userId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedUserHobbies);
    }

    @Test
    void getHobbiesByUserIdThrowsNotFoundException() throws NotFoundException {
        when(!userRepository.existsById(TestDataProvider.userId)).thenReturn(false);
        assertThatThrownBy(() -> userService.getHobbiesByUserId(TestDataProvider.userId)).isInstanceOf(NotFoundException.class);
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

        private static UserHobbyEntity getUserHobbyEntity() {
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
