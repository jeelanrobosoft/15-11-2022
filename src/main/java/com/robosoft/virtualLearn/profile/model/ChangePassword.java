package com.robosoft.virtualLearn.profile.model;


import lombok.Data;

@Data
public class ChangePassword {
    private String userName;
    private String currentPassword;
    private String newPassword;
}
