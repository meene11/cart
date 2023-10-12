package zerobase.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
//@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto {
    private int id;
    private String category;
    private String name;
    private int price;
    private int count;
    private int productId;


    public ProductDto(int id, String category, String name, int price, int count, int productId) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.count = count;
        this.productId = productId;
    }

}
