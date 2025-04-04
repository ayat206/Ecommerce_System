import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create products
        Product cheese = new ShippableProduct("Cheese", 100, 5, 0.4);
        Product tv = new ShippableProduct("TV", 300, 2, 7.5);
        Product scratchCard = new Product("Scratch Card", 50, 10) {

            @Override
            public boolean isProductExpired() {
                return false;
            }

            @Override
            public boolean isProductShippable() {
                return false;
            }
        };

        // Create a list of products to pass to the customer
        List<Product> productList = new ArrayList<>();
        productList.add(cheese);
        productList.add(tv);
        productList.add(scratchCard);

        // Create a customer with a balance of 590 and pass the product list
        Customer customer = new Customer("Ayat",590, productList);

        // Add products to the cart
        Cart cart = customer.getCart();
        cart.addProduct(cheese, 2); // Add 2 Cheese
        cart.addProduct(tv, 1);     // Add 1 TV
        cart.addProduct(scratchCard, 1); // Add 1 Scratch Card

        // Process the checkout
        CheckOut.checkingOut(customer, cart);
    }
}
