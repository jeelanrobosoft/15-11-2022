package com.robosoft.virtualLearn.Menu.service;


import com.robosoft.virtualLearn.Menu.dao.MenuDataAccess;
import com.robosoft.virtualLearn.Menu.dto.MyProfileResponse;
import com.robosoft.virtualLearn.Menu.dto.NotificationResponse;
import com.robosoft.virtualLearn.Menu.dto.SideBarRequest;
import com.robosoft.virtualLearn.Menu.dto.SideBarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<NotificationResponse> getNotification(String userName) {
        return dataAccess.getNotification(userName);
    }
}
