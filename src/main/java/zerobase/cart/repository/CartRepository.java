package zerobase.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import zerobase.cart.domain.Cart;
import zerobase.cart.domain.Product;
import zerobase.cart.domain.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {




}
