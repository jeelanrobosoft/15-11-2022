package com.robosoft.virtualLearn.register_login.register.sms.controller;


import com.google.api.Http;
import com.robosoft.virtualLearn.register_login.register.sms.model.MobileAuth;
import com.robosoft.virtualLearn.register_login.register.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/User")
public class TwoFactorServiceController {


    @Autowired
    SmsService service;


    @PutMapping("/Continue")
    public ResponseEntity<String> sendCodeInSMS(@RequestBody MobileAuth mobileAuth) {
        int status = service.checkMobileNumber(mobileAuth);
        if (status == 1)
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_FOUND);
        String twoFaCode = String.valueOf(new Random().nextInt(8999) + 1000);
        return ResponseEntity.of(Optional.of("OTP Valid Until = " + service.sendOtp(mobileAuth, twoFaCode) + " Minutes"));
    }


    @PutMapping("/Resend")
    public ResponseEntity<String> ResendCodeInSMS(@RequestBody MobileAuth mobileAuth) {
        service.deletePreviousOtp(mobileAuth.getMobileNumber());
        String twoFaCode = String.valueOf(new Random().nextInt(8999) + 1000);
        return ResponseEntity.of(Optional.of("OTP Valid Until = " + service.sendOtp(mobileAuth, twoFaCode) + " Minutes"));
    }

    @PostMapping("/Verify")
    public ResponseEntity<String> verifyOtp(@RequestBody MobileAuth otp) {
        return new ResponseEntity<>(service.verifyOtp(otp),HttpStatus.OK);
    }

    @PostMapping("/Send")
    public ResponseEntity<?> sendOtp(@RequestBody MobileAuth auth) {
        int status = service.checkMobileNumber(auth);
        if (status == 0)
            return new ResponseEntity<String>("User not registered", HttpStatus.NOT_FOUND);
        String twoFaCode = String.valueOf(new Random().nextInt(8999) + 1000);
        return ResponseEntity.of(Optional.of("OTP Valid Until = " + service.sendOtp(auth, twoFaCode) + " Minutes"));
    }

    @PostMapping("/ResetPassword")
    public ResponseEntity<?> resetPassword(@RequestBody MobileAuth auth) {
        int status = service.checkMobileNumber(auth);
        if (status == 0)
            return new ResponseEntity<>("Input field is incorrect",HttpStatus.NOT_FOUND);
        service.resetPassword(auth);
        return ResponseEntity.ok().build();
    }

}
