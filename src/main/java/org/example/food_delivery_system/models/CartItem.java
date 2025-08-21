package org.example.food_delivery_system.models;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.example.food_delivery_system.dtos.CartItemDto;

@Entity
@Getter
@Setter
public class CartItem extends BaseModel {
    @OneToOne
    private FoodItem foodItem;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;
    private double price;

    public CartItemDto toDto() {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setFoodItemId(this.getId());
        cartItemDto.setFoodItemName(this.getFoodItem().getName());
        cartItemDto.setQuantity(this.getQuantity());
        cartItemDto.setPrice(this.getFoodItem().getPrice());
        cartItemDto.setSum(this.getPrice());

        return cartItemDto;
    }
}
