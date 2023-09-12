package com.inn.restaurant.RestImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inn.restaurant.Service.UserService;
import com.inn.restaurant.Wrapper.UserWrapper;
import com.inn.restaurant.jwt.CustomerUserDetailsService;
import com.inn.restaurant.jwt.JwtFilter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserRestImpl.class)
@AutoConfigureMockMvc(addFilters = false)
class UserRestImplTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @MockBean
    private JwtFilter jwtFilter;
    @MockBean
    private CustomerUserDetailsService customerUserDetailsService;

    @Test
    void testSignup() throws Exception {
        Map<String,String> user = new HashMap<>();
        user.put("name","raj");
        user.put("contactNumber","123");
        user.put("email","123@gmail.com");
        user.put("password","123");


        when(mockUserService.login(user)).
                thenReturn(new ResponseEntity<>(("Successfully registerd"),HttpStatus.OK));
        // Setup
//       Map<String,String> user = new HashMap<>();
//        user.put("name","raj");
//        user.put("contactNumber","123");
//        user.put("email","123@gmail.com");
//        user.put("password","123");
        System.out.println(asJsonstring(user));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/user/signup")
                        .content(asJsonstring(user)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        System.out.println(response.getStatus());

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("Successfully registerd.");
    }
    private String asJsonstring( Map<String,String> requestmap) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(requestmap);
    }

    @Test
    void testLogin() throws Exception {
        Map<String,String> user = new HashMap<>();
        user.put("name","raj");
        user.put("contactNumber","123");
        user.put("email","123@gmail.com");
        user.put("password","123");
        when(mockUserService.login(Map.ofEntries(Map.entry("value", "value"))))
                .thenReturn(new ResponseEntity<>("body", HttpStatus.OK));


        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/user/login")
                        .content(asJsonstring(user)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllUsers() throws Exception {
        // Setup
        // Configure UserService.getAllUsers(...).
        final ResponseEntity<List<UserWrapper>> listResponseEntity = new ResponseEntity<>(
                List.of(new UserWrapper(0, "name", "email", "contactNumber", "status")), HttpStatus.OK);
        when(mockUserService.getAllUsers()).thenReturn(listResponseEntity);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/user/get")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testGetAllUsers_UserServiceReturnsNoItems() throws Exception {
        // Setup
        // Configure UserService.getAllUsers(...).
        final ResponseEntity<List<UserWrapper>> listResponseEntity = ResponseEntity.ok(Collections.emptyList());
        when(mockUserService.getAllUsers()).thenReturn(listResponseEntity);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/user/get")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testUpdate() throws Exception {
        Map<String,String> user = new HashMap<>();
        user.put("name","raj");
        user.put("contactNumber","123");
        user.put("email","123@gmail.com");
        user.put("password","123");
        // Setup
        when(mockUserService.update(Map.ofEntries(Map.entry("value", "value"))))
                .thenReturn(new ResponseEntity<>("body", HttpStatus.OK));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/user/update")
                        .content(asJsonstring(user)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testCheckToken() throws Exception {
        // Setup
        when(mockUserService.checkToken()).thenReturn(new ResponseEntity<>("body", HttpStatus.OK));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/user/checktoken")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testChangePassword() throws Exception {
        Map<String,String> user = new HashMap<>();
        user.put("name","raj");
        user.put("contactNumber","123");
        user.put("email","123@gmail.com");
        user.put("password","123");
        // Setup
        when(mockUserService.changePassword(Map.ofEntries(Map.entry("value", "value"))))
                .thenReturn(new ResponseEntity<>("body", HttpStatus.OK));

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/user/changePassword")
                        .content(asJsonstring(user)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
