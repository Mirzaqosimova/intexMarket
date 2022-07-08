package com.example.index_market.controller.user;


import com.example.index_market.controller.AbstractController;
import com.example.index_market.dto.user.AuthUserCreateDto;
import com.example.index_market.dto.user.AuthUserUpdateDto;
import com.example.index_market.dto.user.SingInDto;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.user.UserServiceImpl;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
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
     * Barcha userlarni korish admin uchun
     *
     * @return
     */
    @Operation(summary = "Userlar ro'yxati",description = "admin uchun userlar ro'yxati")
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
    @Operation(summary = "Login method",description = "user login qilish")
    @PostMapping("/login-user")
    public HttpEntity<?> login(@RequestBody SingInDto singInDto) {
        ApiResponse login = service.login(singInDto);
        return ResponseEntity.ok(login);
    }

    /**
     *
     * @param dto
     * @return
     */
    @Operation(summary = "Create method",description = "user login qilish")
    @PostMapping("/create-user")
    public HttpEntity<?> register(@RequestBody AuthUserCreateDto dto) {
        ApiResponse create = service.create(dto);
        return ResponseEntity.status(create.isSuccess() ? 201 : 409).body(create);
    }

    /**
     * profilni tahrirlash
     *
     * @param dto
     * @return
     */
    @Operation(summary = "Update method",description = "profile update qilish")
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
    @Operation(summary = "Get one user method",description = "profileni ko'rish")
    @GetMapping("/get/{id}")
    public HttpEntity<?> get(@PathVariable String id) {
        ApiResponse apiResponse = service.get(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    /**
     * Profilni ochirish
     *
     * @param id
     * @return
     */
    @Operation(summary = "Delete method",description = "profile log out qilish")
    @DeleteMapping("/delete-user/{id}")
    public HttpEntity<?> delete(@PathVariable String id) {
        ApiResponse delete = service.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? 204 : 409).body(delete);
    }


}
