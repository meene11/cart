package zerobase.cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.cart.domain.Product;
import zerobase.cart.service.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/prod")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // 상품등록
    @PostMapping("/addProd")
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        log.info("add product -> " + product.getName());
        productService.addProduct(product);
        return ResponseEntity.ok().build();
    }

    // 상품정보 리스트 조회
    @GetMapping("/allProd")
    public ResponseEntity<?> getAllProduct(){
        List<Product> list = productService.getAllProduct();
        log.info("allProduct list  -> " + list.get(0).getName());
        return ResponseEntity.ok(list);
    }

    // 상품명으로 검색
    @PostMapping("/searchProd")
    public ResponseEntity<?> searchProduct(@RequestBody Product product){

        String itemName = product.getName();
        List<Product> list = productService.searchProduct(itemName);
        log.info("searchProduct list  -> " + list.get(0).getDetail());

        return ResponseEntity.ok(list);
    }

}
