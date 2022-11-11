package com.robosoft.editprofile.profile.dao;

import com.robosoft.editprofile.profile.model.ChangePassword;
import com.robosoft.editprofile.profile.model.SaveProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public void saveProfile(SaveProfile saveProfile, String profilePhotoLink, String finalDateOfBirth) {
        String query = "update user set dateOfBirth=?,profilePhoto=?,occupation=?,gender=?,twitterLink=?,faceBookLink=? where userName='" + saveProfile.getUserName() + "'";
        jdbcTemplate.update(query,finalDateOfBirth,profilePhotoLink,saveProfile.getOccupation(),saveProfile.getGender(),saveProfile.getTwitterLink(),saveProfile.getFaceBookLink());
    }

    public String changePassword(ChangePassword password) {
        String query = "select password from authenticate where userName='" + password.getUserName() + "'";
        String currentPassword = jdbcTemplate.queryForObject(query, String.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(password.getCurrentPassword(),currentPassword)){
            query = "update authenticate set password=? where userName='" + password.getUserName() + "'";
            jdbcTemplate.update(query,new BCryptPasswordEncoder().encode(password.getNewPassword()));
            return "Password Changed Successfully";
        }
        else {
            return "Reset Password Failed";
        }

    }

}
