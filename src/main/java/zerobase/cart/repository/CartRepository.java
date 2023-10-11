package zerobase.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import zerobase.cart.domain.Cart;
import zerobase.cart.domain.Product;
import zerobase.cart.domain.User;
import zerobase.cart.dto.Item;
import zerobase.cart.dto.ProductDto;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserId(int userId);
    @Query("select new zerobase.cart.dto.ProductDto(p.category, p.name, p.price, c.count, c.productId) from product p inner join cart c on p.id = c.productId where c.userId = :userId")
    List<ProductDto> getMyCartList(@Param("userId") int userId);
    // dto projection 할 때 클래스 경로까지 맞춰줘야 인식 올바르겜 함.(new zerobase.cart.dto~ 없을시 오류발생)

    @Query("select p.category, p.name, p.price, c.count from product p inner join cart c on p.id = c.productId where c.userId = :userId")
    List<Item> myItemMyCartList(@Param("userId") int userId);

    // 테이블:cart 의 id
    @Query("select id from cart where userId=:userId and productId=:productId")
    int getCartId(@Param("userId") int userId, @Param("productId") int productId);

    // 장바구니 수정
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update cart c set c.count = :count where c.id = :id")
    int updCartById(@Param("count") int count, @Param("id") int id);

    boolean existsByProductId(int productId);



}
