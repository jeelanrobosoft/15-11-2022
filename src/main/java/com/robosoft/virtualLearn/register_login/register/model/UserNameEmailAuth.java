package com.robosoft.virtualLearn.register_login.register.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNameEmailAuth {

    private String userName;
    private String email;

}
