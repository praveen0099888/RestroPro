package com.inn.restaurant.ServiceImpl;

import com.inn.restaurant.POJO.User;
import com.inn.restaurant.Utils.EmailUtils;
import com.inn.restaurant.Wrapper.UserWrapper;
import com.inn.restaurant.dao.UserDao;
import com.inn.restaurant.jwt.CustomerUserDetailsService;
import com.inn.restaurant.jwt.JwtFilter;
import com.inn.restaurant.jwt.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserServiceImpl userServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        userServiceImplUnderTest = new UserServiceImpl();
        userServiceImplUnderTest.jwtUtil = mock(JwtUtil.class);
        userServiceImplUnderTest.customerUserDetailsService = mock(CustomerUserDetailsService.class);
        userServiceImplUnderTest.emailUtils = mock(EmailUtils.class);
        userServiceImplUnderTest.userDao = mock(UserDao.class);
        userServiceImplUnderTest.jwtFilter = mock(JwtFilter.class);
        userServiceImplUnderTest.authenticationManager = mock(AuthenticationManager.class);
    }

    @Test
    void testSignUp() {
        // Setup
        Map<String,String> requestmap = new HashMap<>();
        requestmap.put("name","raj");
        requestmap.put("contactNumber","raj");
        requestmap.put("email","123@gmail.com");
        requestmap.put("password","123");
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("{\"message\":\"Successfully registerd.\"}",
                HttpStatus.OK);


        // Run the test
        final ResponseEntity<String> result = userServiceImplUnderTest.signUp(requestmap);
        System.out.println(result);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

    }

    @Test
    void testLogin() {
        // Setup
        Map<String,String> requestmap = new HashMap<>();
        requestmap.put("email","123@gmail.com");
        requestmap.put("password","123");

        final ResponseEntity<String> expectedResult = new ResponseEntity<>("{\"message\":\"bad credentials.\"}",
                HttpStatus.BAD_REQUEST);
//
        // Run the test
        final ResponseEntity<String> result = userServiceImplUnderTest.login(requestmap);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testLogin_AuthenticationManagerThrowsAuthenticationException() {
        // Setup
        Map<String,String> requestmap = new HashMap<>();
        requestmap.put("email","123@gmail.com");
        requestmap.put("password","123");

        final ResponseEntity<String> expectedResult = new ResponseEntity<>("{\"message\":\"bad credentials.\"}",
                HttpStatus.BAD_REQUEST);


        // Run the test
        final ResponseEntity<String> result = userServiceImplUnderTest.login(requestmap);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllUsers() {
        // Setup
        final ResponseEntity<List<UserWrapper>> expectedResult = new ResponseEntity<>(
                new ArrayList<>(), HttpStatus.UNAUTHORIZED);

        // Run the test
        final ResponseEntity<List<UserWrapper>> result = userServiceImplUnderTest.getAllUsers();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetAllUsers_UserDaoReturnsNoItems() {
        // Setup
        when(userServiceImplUnderTest.jwtFilter.isAdmin()).thenReturn(false);
        when(userServiceImplUnderTest.userDao.getAllUsers()).thenReturn(Collections.emptyList());
        final ResponseEntity<List<UserWrapper>> expectedresult = new ResponseEntity<>(
                new ArrayList<>(), HttpStatus.UNAUTHORIZED);

        // Run the test
        final ResponseEntity<List<UserWrapper>> result = userServiceImplUnderTest.getAllUsers();


        // Verify the results
        assertThat(result).isEqualTo(expectedresult);
    }

    @Test
    void testUpdate() {
        // Setup
        final Map<String, String> requestmap = Map.ofEntries(Map.entry("value", "value"));
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("{\"message\":\"UNAUTHORIZED ACCESS.\"}",
                HttpStatus.UNAUTHORIZED);
        when(userServiceImplUnderTest.jwtFilter.isAdmin()).thenReturn(false);

        // Configure UserDao.findById(...).
        final User user = new User();
        user.setId(0);
        user.setName("name");
        user.setContactNumber("contactNumber");
        user.setEmail("email");
        user.setPassword("password");
        user.setStatus("false");
        user.setRole("user");
        final Optional<User> optional = Optional.of(user);
        when(userServiceImplUnderTest.userDao.findById(0)).thenReturn(optional);

        when(userServiceImplUnderTest.userDao.updateStatus("status", 0)).thenReturn(0);
        when(userServiceImplUnderTest.userDao.getAllAdmin()).thenReturn(List.of("value"));
        when(userServiceImplUnderTest.jwtFilter.getCurrentUser()).thenReturn("result");

        // Run the test
        final ResponseEntity<String> result = userServiceImplUnderTest.update(requestmap);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

    }

    @Test
    void testUpdate_UserDaoFindByIdReturnsAbsent() {
        // Setup
        final Map<String, String> requestmap = Map.ofEntries(Map.entry("value", "value"));
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("{\"message\":\"bad credentials.\"}",
                HttpStatus.OK);
        when(userServiceImplUnderTest.jwtFilter.isAdmin()).thenReturn(false);
        when(userServiceImplUnderTest.userDao.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final ResponseEntity<String> result = userServiceImplUnderTest.update(requestmap);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate_UserDaoGetAllAdminReturnsNoItems() {
        // Setup
        final Map<String, String> requestmap = Map.ofEntries(Map.entry("value", "value"));
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("{\"message\":\"bad credentials.\"}",
                HttpStatus.OK);
        when(userServiceImplUnderTest.jwtFilter.isAdmin()).thenReturn(false);

        // Configure UserDao.findById(...).
        final User user = new User();
        user.setId(0);
        user.setName("name");
        user.setContactNumber("contactNumber");
        user.setEmail("email");
        user.setPassword("password");
        user.setStatus("false");
        user.setRole("user");
        final Optional<User> optional = Optional.of(user);
        when(userServiceImplUnderTest.userDao.findById(0)).thenReturn(optional);

        when(userServiceImplUnderTest.userDao.updateStatus("status", 0)).thenReturn(0);
        when(userServiceImplUnderTest.userDao.getAllAdmin()).thenReturn(Collections.emptyList());

        // Run the test
        final ResponseEntity<String> result = userServiceImplUnderTest.update(requestmap);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(userServiceImplUnderTest.userDao).updateStatus("status", 0);
    }

    @Test
    void testCheckToken() {
        // Setup
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("{\"message\":\"bad credentials.\"}",
                HttpStatus.OK);

        // Run the test
        final ResponseEntity<String> result = userServiceImplUnderTest.checkToken();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testChangePassword() {
        // Setup
        final Map<String, String> requestmap = Map.ofEntries(Map.entry("value", "value"));
        final ResponseEntity<String> expectedResult = new ResponseEntity<>("{\"message\":\"bad credentials.\"}",
                HttpStatus.OK);
        when(userServiceImplUnderTest.jwtFilter.getCurrentUser()).thenReturn("email");

        // Configure UserDao.findByEmail(...).
        final User user = new User();
        user.setId(0);
        user.setName("name");
        user.setContactNumber("contactNumber");
        user.setEmail("email");
        user.setPassword("password");
        user.setStatus("false");
        user.setRole("user");
        when(userServiceImplUnderTest.userDao.findByEmail("email")).thenReturn(user);

        // Configure UserDao.save(...).
        final User user1 = new User();
        user1.setId(0);
        user1.setName("name");
        user1.setContactNumber("contactNumber");
        user1.setEmail("email");
        user1.setPassword("password");
        user1.setStatus("false");
        user1.setRole("user");
        when(userServiceImplUnderTest.userDao.save(new User())).thenReturn(user1);

        // Run the test
        final ResponseEntity<String> result = userServiceImplUnderTest.changePassword(requestmap);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
        verify(userServiceImplUnderTest.userDao).save(new User());
    }
}
