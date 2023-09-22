package zerobase.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.cart.domain.Product;
import zerobase.cart.domain.User;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // 상품명으로 상품 조회
    List<Product> findByName(String name);
}
