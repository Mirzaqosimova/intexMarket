package com.example.index_market.controller.product;

import com.example.index_market.controller.AbstractController;
import com.example.index_market.dto.product.ProductCreateDto;
import com.example.index_market.dto.product.ProductUpdateDto;
import com.example.index_market.response.ApiResponse;
import com.example.index_market.service.product.ImageService;
import com.example.index_market.service.product.ProductServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/api/product")
public class ProductController extends AbstractController<ProductServiceImpl> {

    private final ImageService imageService;

    public ProductController(ProductServiceImpl service, ImageService imageService) {
        super(service);
        this.imageService = imageService;
    }



    @GetMapping("/get-all-products-for-admin")
    public ResponseEntity<?> getAllProductsForAdmin(){
        ApiResponse response = service.getAllForAdmin();
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }



    @GetMapping("/get-all-products-for-user")
    public ResponseEntity<?> getAllProductsForUser(){
        ApiResponse response = service.getAllForUser();
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }



    @GetMapping("/get-product/{id}")
    public ResponseEntity<?> getOneProduct(@PathVariable String id) {
        ApiResponse response = service.get(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }


    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable String id) {
        ApiResponse response = service.delete(id);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @ResponseBody
    public ResponseEntity<?> saveProduct(@RequestPart("image") MultipartHttpServletRequest request, @RequestPart("product") ProductCreateDto productCreateDto) {
        String url ;
        try {
            url = imageService.getFileFromRequest(request);
        } catch (Exception e) {
            return ResponseEntity.status(409).body(new ApiResponse(false,"Cannot cast image"));
        }
        productCreateDto.setImageUrl(url);
        ApiResponse response = service.create(productCreateDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping(path = "/edit-product",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> updateProduct(@RequestPart MultipartHttpServletRequest request,@RequestPart ProductUpdateDto productUpdateDto) {

        String url ;
        try {
            url = imageService.getFileFromRequest(request);
        } catch (Exception e) {
          return ResponseEntity.status(409).body(new ApiResponse(false,"Cannot cast image"));
        }
        productUpdateDto.setImageUrl(url);
        ApiResponse response = service.update(productUpdateDto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }


}
