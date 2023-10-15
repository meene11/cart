package zerobase.cart.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerobase.cart.domain.User;
import zerobase.cart.dto.Item;
import zerobase.cart.dto.ProductDto;
import zerobase.cart.repository.CartRepository;
import zerobase.cart.domain.Cart;
import zerobase.cart.repository.UserRepository;

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

        int proId = cart.getProductId();
        int userId = cart.getUserId();

        int count = 0;
        int id = 0;

        if(cartRepository.existsByProductIdAndUserId(proId, userId)){
            // 장바구니id 구하기
            id  = cartRepository.getCartId(userId, proId);

            log.info("장바구니에 동일 상품이 있습니다. 상품개수 누적하겠습니다." );
            int cnt = cartRepository.getCountByUserIdAndProductId(userId, proId);
            int plusCnt = cart.getCount();
            count = cnt+plusCnt;

            log.info("  합한 갯수 : " + count  + " 기존꺼 cnt:"+cnt + " 추가 새록 :" + plusCnt+ " id" +id);
            cartRepository.updCartById(count, id);
        } else {
            // 존재할경우 cnt 누적 갯수로 저장
            cartRepository.save(cart);
            log.info(" In Cart Service : " + userId);

        }
        return cartRepository.findByUserId(userId);
    }

    public List<ProductDto> allMyCart(int userId) {
        return  cartRepository.getMyCartList(userId);
    }

    // 장바구니 수정
    /*
     userId 확인
     productId 확인
     => 해당 cartId 찾기
     update cart set count = :count where id = :id;
     */
    public Optional<Cart> updateMyCart(int userId, int productId, int count) {

        log.info("In serivce:  userId: " + userId);
        log.info("In serivce:  productId: " + productId);
        log.info("In serivce:  count: " + count);

        // 장바구니id 구하기
        int id  = cartRepository.getCartId(userId, productId);
        log.info("In serivce:  get cartId: " + id);


        Optional<Integer> resultUpd = cartRepository.updCartById(count, id);
        log.info("$v   In serivce:  resultUpd: " + resultUpd);

        Optional<Cart> cart;
        if (resultUpd != null) {
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


    // 장바구시 삭제
    public List<ProductDto> deleteCart(int userId, int cartId) {
        if(!cartRepository.existsById(cartId)){
            throw new RuntimeException("장바구니ID를 확인해주세요");
        }
        cartRepository.deleteById(cartId);
       return cartRepository.getMyCartList(userId);
    }

    // 장바구니 전체 삭제
    public void allDeleteCart(int intUserId) {
        cartRepository.deleteAllMyCartById(intUserId);
    }
}
