package com.robosoft.editprofile.Menu.controller;

import com.robosoft.editprofile.Menu.dto.SideBarRequest;
import com.robosoft.editprofile.Menu.dto.SideBarResponse;
import com.robosoft.editprofile.Menu.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MenuController {

    @Autowired
    MenuService menuService;


    @GetMapping("/Menu")
    public ResponseEntity<?> getSideBar(@RequestBody SideBarRequest request){
        SideBarResponse response = menuService.getUserDetails(request);
        if (response == null)
            return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
        return ResponseEntity.of(Optional.of(response));
    }


}
