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

    private String category;
    private String name;
    private int price;
    private int count;

    public ProductDto(String category, String name, int price, int count) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.count = count;
    }

}
