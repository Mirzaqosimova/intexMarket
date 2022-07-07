package com.example.index_market.controller.order;

import com.example.index_market.controller.AbstractController;
import com.example.index_market.dto.order.OrderCreateDto;
import com.example.index_market.dto.order.OrderUpdateDto;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.order.OrderServiceImpl;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController extends AbstractController<OrderServiceImpl> {

    public OrderController(OrderServiceImpl service) {
        super(service);
    }

    /**
     * Barcha orderlar ro'yxatini ko'rish Admin uchun
     *
     * @return
     */
    @GetMapping("/get-all-orders")
    public HttpEntity<?> getAll() {
        ApiResponse all = service.getAll();
        return ResponseEntity.ok(all);
    }

    /**
     * Buyurtmani o'chirb tashlash
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete-order/{id}")
    public HttpEntity<?> delete(@PathVariable String id) {
        ApiResponse delete = service.delete(id);
        return ResponseEntity.status(delete.isSuccess() ? 204 : 409).body(delete);
    }


    /**
     * buyurtmani tahrirlash
     *
     * @param dto
     * @return
     */
    @PutMapping("/edit-order")
    public HttpEntity<?>edit(@RequestBody OrderUpdateDto dto){
        ApiResponse update = service.update(dto);
        return ResponseEntity.status(update.isSuccess() ? 202 : 409).body(update);
    }


    /**
     * maxsulot buyurtma qilish
     *
     * @param dto
     * @return
     */
    @PostMapping("/add-order")
    public HttpEntity<?>add(@RequestBody OrderCreateDto dto){
        ApiResponse response = service.create(dto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }
}
