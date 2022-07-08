package com.example.index_market.controller.detail;

import com.example.index_market.controller.AbstractController;
import com.example.index_market.dto.detail.DetailCreateDto;
import com.example.index_market.dto.detail.DetailUpdateDto;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.detail.DetailServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/detail")
public class DetailController extends AbstractController<DetailServiceImpl> {

    public DetailController(DetailServiceImpl service) {
        super(service);
    }

    @GetMapping
    public ResponseEntity<?> getAllDetail() {
        ApiResponse response = service.getAll();
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @GetMapping("/get-order/{id}")
    public ResponseEntity<?> getOneDetail(@PathVariable String id) {
        ApiResponse response = service.get(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }


    @DeleteMapping("/delete-detail/{id}")
    public ResponseEntity<?> deleteDetailById(@PathVariable String id) {
        ApiResponse response = service.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PostMapping("add-detail")
    public ResponseEntity<?> saveDetail(@RequestBody DetailCreateDto detailCreateDto) {
        ApiResponse response = service.create(detailCreateDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/edit-detail")
    public ResponseEntity<?> updateDetail(@RequestBody DetailUpdateDto detailUpdateDto) {
        ApiResponse response = service.update(detailUpdateDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

}
