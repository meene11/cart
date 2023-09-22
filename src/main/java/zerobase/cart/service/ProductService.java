package zerobase.cart.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zerobase.cart.domain.Product;
import zerobase.cart.repository.ProductRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    // 상품등록
    public void addProduct(Product product){
        productRepository.save(product);
    }

    // 상품조회
    public List<Product> getAllProduct(){

        return productRepository.findAll();
    }

    // 상품명으로 해당상품조회
    public List<Product> searchProduct(String name){

        return productRepository.findByName(name);
    }

}
