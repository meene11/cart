package zerobase.cart.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerobase.cart.dto.Item;
import zerobase.cart.repository.CartRepository;
import zerobase.cart.domain.Cart;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    // 장바구니에 상품 넣기
    public List<Cart> addCart(Cart cart){
        cartRepository.save(cart);
        int userId = cart.getUserId();
        log.info(" In Cart Service : " + userId);
        return cartRepository.findByUserId(userId);
    }

    public List<Object> allMyCart(int userId) {
        log.info(" null 인지 화ㅓㄱ인!!!! : " + userId);
        return  cartRepository.getMyCartList(userId);
    }

    public List<Item> myItem(int userId){
        return cartRepository.myItemMyCartList(userId);
    }
}
