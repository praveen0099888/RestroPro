package com.inn.restaurant.ServiceImpl;

import com.inn.restaurant.POJO.User;
import com.inn.restaurant.Service.UserService;
import com.inn.restaurant.Utils.EmailUtils;
import com.inn.restaurant.Utils.RestaurantUtils;
import com.inn.restaurant.Wrapper.UserWrapper;
import com.inn.restaurant.constants.RestaurantConstants;
import com.inn.restaurant.dao.UserDao;
import com.inn.restaurant.jwt.CustomerUserDetailsService;
import com.inn.restaurant.jwt.JwtFilter;
import com.inn.restaurant.jwt.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    CustomerUserDetailsService customerUserDetailsService;
    @Autowired
    EmailUtils emailUtils;


    @Autowired
    UserDao userDao;
    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestmap) {
        log.info("inside signup {}",requestmap);
        try {
            log.info("inside try of login");
            if (validateSignUpMap(requestmap)) {
                User user = userDao.findByEmailId(requestmap.get("email"));
                if (Objects.isNull(user)) {
                    userDao.save(getUserFromMap(requestmap));
                    return RestaurantUtils.getResponseEntity("Successfully registerd.", HttpStatus.OK);

                } else {
                    return RestaurantUtils.getResponseEntity("Email already exist.", HttpStatus.BAD_REQUEST);
                }
            } else {
                return RestaurantUtils.getResponseEntity(RestaurantConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private boolean validateSignUpMap(Map<String,String> requestmap){
       if( requestmap.containsKey("name")&& requestmap.containsKey("contactNumber")
                &&requestmap.containsKey("email")
                &&requestmap.containsKey("password")) {
           return true;
       }

       return false;
    }
     private User getUserFromMap(Map<String,String> requestmap){
        User user = new User();
        user.setName(requestmap.get("name"));
        user.setContactNumber(requestmap.get("contactNumber"));
        user.setEmail(requestmap.get("email"));
        user.setPassword(requestmap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;
     }



    @Override
    public ResponseEntity<String> login(Map<String, String> requestmap) {
       log.info("inside login");
       try{
           Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestmap.get("email"),requestmap.get("password"))
           );
           if(auth.isAuthenticated()){
               if(customerUserDetailsService.getUserdetail().getStatus().equalsIgnoreCase("true")){
                   return new ResponseEntity<String>("{\"token\":\""+ jwtUtil.generateToken(customerUserDetailsService.getUserdetail().getEmail(),
                            customerUserDetailsService.getUserdetail().getRole())+"\"}", HttpStatus.OK) ;
               }
               else {
                   return new ResponseEntity<String> ("{\"message\":\""+"wait for admin approval."+"\"}",HttpStatus.BAD_REQUEST);
               }
           }
       }
       catch (Exception ex){
           log.info("{}",ex);
       }
        return new ResponseEntity<String> ("{\"message\":\""+"bad credentials."+"\"}",HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getAllUsers() {
        try{
            if(jwtFilter.isAdmin()){
               return  new ResponseEntity<>(userDao.getAllUsers(),HttpStatus.OK);
            }else {
                return  new ResponseEntity<>(new ArrayList<>(),HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
       return new  ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestmap) {
        try {
            if(jwtFilter.isAdmin()){
               Optional<User> optional= userDao.findById(Integer.parseInt(requestmap.get("id")));
               if(!optional.isEmpty()){
                   userDao.updateStatus(requestmap.get("status"),Integer.parseInt(requestmap.get("id")));
                   sendMailToAllAdmin(requestmap.get("status"),optional.get().getEmail(),userDao.getAllAdmin());
                   return  RestaurantUtils.getResponseEntity("user status updated successfully",HttpStatus.OK);


               }else{
                   return RestaurantUtils.getResponseEntity("User id dosen't exist",HttpStatus.OK);
               }

            }else{
                return RestaurantUtils.getResponseEntity(RestaurantConstants.UNAUTHORIZED_ACCESS,HttpStatus.UNAUTHORIZED);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> checkToken() {
        return RestaurantUtils.getResponseEntity("true", HttpStatus.OK);
    }



    private void sendMailToAllAdmin(String status, String user, List<String> allAdmin) {

        allAdmin.remove(jwtFilter.getCurrentUser());
        if(status!=null&&status.equalsIgnoreCase("true")){
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Approved","USER:-"+user+"\n is approved by \n ADMIN:-"+jwtFilter.getCurrentUser(),allAdmin);
        }else {
            emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(),"Account Disabled","USER:-"+user+"\n is Disabled by \n ADMIN:-"+jwtFilter.getCurrentUser(),allAdmin);
        }


    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestmap) {
        try{
            User userobj = userDao.findByEmail(jwtFilter.getCurrentUser());
            if(!userobj.equals(null)){
                if(userobj.getPassword().equals(requestmap.get("oldPassword"))){
                   userobj.setPassword(requestmap.get("newPassword"));
                   userDao.save(userobj);
                    return RestaurantUtils.getResponseEntity("password updated successfully",HttpStatus.OK);
                }
                return RestaurantUtils.getResponseEntity("incorrect old password",HttpStatus.BAD_REQUEST);
            }
            return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return RestaurantUtils.getResponseEntity(RestaurantConstants.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

