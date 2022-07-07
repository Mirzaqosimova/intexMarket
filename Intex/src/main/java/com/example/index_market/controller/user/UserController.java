package com.example.index_market.controller.user;

import com.example.index_market.controller.AbstractController;
import com.example.index_market.service.user.UserServiceImpl;

public class UserController extends AbstractController<UserServiceImpl> {

    public UserController(UserServiceImpl service) {
        super(service);
    }
}
