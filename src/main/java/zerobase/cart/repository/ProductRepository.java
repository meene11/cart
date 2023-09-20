package zerobase.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zerobase.cart.domain.Product;
import zerobase.cart.domain.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
