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

    public void addProduct(Product product){
        productRepository.save(product);
    }

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public List<Product> searchProduct(String name){
        return productRepository.findByName(name);
    }

}
