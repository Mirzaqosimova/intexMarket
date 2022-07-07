package com.example.index_market.controller.user;


import com.example.index_market.controller.AbstractController;
import com.example.index_market.dto.user.AuthUserUpdateDto;
import com.example.index_market.dto.user.SingInDto;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.user.UserServiceImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController extends AbstractController<UserServiceImpl> {
    public UserController(UserServiceImpl service) {
        super(service);
    }

    @GetMapping("/get-all-user")
    public HttpEntity<?> getUser() {
        ApiResponse all = service.getAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping("/login-user")
    public HttpEntity<?> login(@RequestBody SingInDto singInDto) {
        ApiResponse login = service.login(singInDto);
        return ResponseEntity.ok(login);
    }

    @PutMapping("/edit-profile")
    public HttpEntity<?> update(@RequestBody AuthUserUpdateDto dto) {
        ApiResponse update = service.update(dto);
        return ResponseEntity.status(update.isSuccess() ? 202 : 409).body(update);
    }


    @GetMapping("/get/{id}")
    public HttpEntity<?>get(@PathVariable String id ){
        ApiResponse apiResponse = service.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
