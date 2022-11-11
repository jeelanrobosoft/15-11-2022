package com.robosoft.editprofile.Menu.service;


import com.robosoft.editprofile.Menu.dao.MenuDataAccess;
import com.robosoft.editprofile.Menu.dto.MyProfileResponse;
import com.robosoft.editprofile.Menu.dto.SideBarRequest;
import com.robosoft.editprofile.Menu.dto.SideBarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {


    @Autowired
    MenuDataAccess dataAccess;
    public SideBarResponse getUserDetails(SideBarRequest sideBar) {
        return dataAccess.getDetails(sideBar);
    }

    public MyProfileResponse getMyProfile(SideBarResponse response, SideBarRequest request) {
        return dataAccess.getMyProfile(response,request);
    }
}
