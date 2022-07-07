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

    /**
     *
     * Barcha userlarni korish admin uchun
     *
     * @return
     */
    @GetMapping("/get-all-user")
    public HttpEntity<?> getUser() {
        ApiResponse all = service.getAll();
        return ResponseEntity.ok(all);
    }

    /**
     * user yoki admin login bolinishi
     *
     * @param singInDto
     * @return
     */
    @PostMapping("/login-user")
    public HttpEntity<?> login(@RequestBody SingInDto singInDto) {
        ApiResponse login = service.login(singInDto);
        return ResponseEntity.ok(login);
    }

    /**
     * profilni tahrirlash
     *
     * @param dto
     * @return
     */
    @PutMapping("/edit-profile")
    public HttpEntity<?> update(@RequestBody AuthUserUpdateDto dto) {
        ApiResponse update = service.update(dto);
        return ResponseEntity.status(update.isSuccess() ? 202 : 409).body(update);
    }

    /**
     * bir dona userni korish
     *
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public HttpEntity<?>get(@PathVariable String id ){
        ApiResponse apiResponse = service.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * Profilni ochirish
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete-user/{id}")
    public HttpEntity<?>delete(@PathVariable String  id){
        ApiResponse delete = service.delete(id);
        return ResponseEntity.status(delete.isSuccess()?204:409).body(delete);
    }
}
