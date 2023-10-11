package zerobase.cart.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerobase.cart.dto.Item;
import zerobase.cart.dto.ProductDto;
import zerobase.cart.repository.CartRepository;
import zerobase.cart.domain.Cart;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    // 장바구니에 상품 넣기
    public List<Cart> addCart(Cart cart){
        // 장바구니내에 동일상품 존재유무 확인
        // 존재할경우 cnt 누적 갯수로 저장


        cartRepository.save(cart);
        int userId = cart.getUserId();
        log.info(" In Cart Service : " + userId);
        return cartRepository.findByUserId(userId);
    }

    public List<ProductDto> allMyCart(int userId) {
        return  cartRepository.getMyCartList(userId);
    }

    public List<Item> myItem(int userId){
        return cartRepository.myItemMyCartList(userId);
    }

    // 장바구니 수정
    // userId 확인
    // productId 확인
    // => 해당 cartId 찾기
    //    update cart set count = :count where id = :id;
    //select id from cart where userId=3 and productId=5;

    public Optional<Cart> updateMyCart(int userId, int productId, int count) {

        log.info("In serivce:  userId: " + userId);
        log.info("In serivce:  productId: " + productId);
        log.info("In serivce:  count: " + count);

        // 장바구니id 구하기
        int id = cartRepository.getCartId(userId, productId);
        log.info("In serivce:  get cartId: " + id);
        /*
        org.springframework.aop.AopInvocationException: Null return value from advice does not match primitive return type for: public abstract int zerobase.cart.repository.CartRepository.getCartId(int,int)
         */

        int resultUpd = cartRepository.updCartById(count, id);
        log.info("@#$@#%$#%#$v   In serivce:  resultUpd: " + resultUpd);

        Optional<Cart> cart;
        if (resultUpd == 1) {
            cart = cartRepository.findById(id);
        } else {
            throw new RuntimeException("장바구니 수정에 실패했습니다.");
        }
        log.info("In serivce:  최종 카트  " + cart);
        return cart;
    }

    public boolean checkProductId(int productId) {
        return cartRepository.existsByProductId(productId);
    }


}
