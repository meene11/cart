package zerobase.cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zerobase.cart.domain.Cart;
import zerobase.cart.domain.Product;
import zerobase.cart.domain.User;
import zerobase.cart.dto.Item;
import zerobase.cart.dto.ProductDto;
import zerobase.cart.service.CartService;
import zerobase.cart.service.ProductService;
import zerobase.cart.service.UserService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;

    // 장바구니 상품 넣기: 유저id/상품id    ||   body 에 상품갯수
    // ex) http://localhost:8080/cart/addItem/QQQ/5   || {"count" : "10"}
    @PostMapping("/addItem/{userId}/{productId}")
    public ResponseEntity<?> addCartItem(@PathVariable("userId") String userId, @PathVariable("productId") int productId, @RequestBody Cart request) {

        Optional<User> user= userService.findById(userId);
        int urId = 0;
        if(user != null){
           urId = user.get().getId();
           log.info("In carController:  userId: "+ urId);
        } else {
            new RuntimeException("ID를 다시 확인해주세요.");
        }

        Optional<Product> product = productService.findById(productId);
        if(product != null){
            String getProductName = product.stream().map(e->e.getName()).toString();
            log.info("In carController:  getProductName 상품명!!!!: "+ getProductName);
        } else {
            new RuntimeException("해당 상품이 존재하지 않습니다.");
        }

        int cnt = request.getCount();
        Cart cart = new Cart();
        cart.setUserId(urId);
        cart.setProductId(productId);
        cart.setCount(cnt);

        List<Cart> list = cartService.addCart(cart);

        return ResponseEntity.ok(list);
    }

    // 장바구니 조회
    @GetMapping("/{userId}")
    public ResponseEntity<?> myCart(@PathVariable("userId") String userId) {
        Optional<User> user= userService.findById(userId);
        int urId = 0;
        if(user != null){
            urId = user.get().getId();
            log.info("In carController:  userId: "+ urId);
        } else {
            new RuntimeException("ID를 다시 확인해주세요.");
        }
        List<ProductDto> list = cartService.allMyCart(urId);
        return ResponseEntity.ok(list);
    }



}
