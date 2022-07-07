package com.example.index_market.controller.frame;

import com.example.index_market.controller.AbstractController;
import com.example.index_market.dto.frame.FrameCreateDto;
import com.example.index_market.dto.frame.FrameUpdateDto;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.frame.FrameServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/frame")
public class FrameController extends AbstractController<FrameServiceImpl> {

    public FrameController(FrameServiceImpl service) {
        super(service);
    }

    @GetMapping
    public ResponseEntity<?> getAllFrame() {
        ApiResponse response = service.getAll();
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneFrame(@PathVariable String id) {
        ApiResponse response = service.get(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFrameById(@PathVariable String id) {
        ApiResponse response = service.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PostMapping
    public ResponseEntity<?> saveFrame(@RequestBody FrameCreateDto frameCreateDto) {
        ApiResponse response = service.create(frameCreateDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFrame(@RequestBody FrameUpdateDto frameUpdateDto) {
        ApiResponse response = service.update(frameUpdateDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

}