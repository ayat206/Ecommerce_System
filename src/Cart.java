import java.util.ArrayList;
import java.util.List;

// Shopping Cart that holds the selected products.
public class Cart {
    private List<CartItem> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public List<CartItem> getProducts() {
        return this.products;
    }

    public void addProduct(Product product, int quantity) {
        if (product.getQuantity() < quantity) {
            System.out.println("Error, product is OUT OF STOCK");
            return;
        }

        if (product.isProductExpired()) {
            System.out.println("Error, product is EXPIRED");
            return;
        }

        // If product already exists, just add the quantity
        for (CartItem item : products) {
            if (item.getProduct().equals(product)) {
                item.addQuantity(quantity);
                return;
            }
        }
        products.add(new CartItem(product, quantity));
    }
}

// Represents a single product item in the cart.
class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void addQuantity(int amount) { this.quantity += amount; }
}