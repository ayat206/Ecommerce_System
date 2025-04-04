import java.util.ArrayList;
import java.util.List;

class CheckOut {
    public static void checkingOut(Customer customer, Cart cart) {
        double subtotal = 0;
        double shippingFees = 0;
        double total = 0;
        double totalWeight = 0;

        if (cart.getProducts().isEmpty()) {
            System.out.println("Error, the cart is EMPTY");
            return;
        }

        List<Shippable> shippingProducts = new ArrayList<>();

        System.out.println("** Shipment Notice **");

        for (CartItem item : cart.getProducts()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();

            if (product.isProductShippable() && product instanceof Shippable) {
                shippingProducts.add((Shippable) product);
                double weight = ((Shippable) product).getWeight() * quantity;
                System.out.println(quantity + "x " + product.getName() + " " + weight + "kg");
                totalWeight += weight;
            }
        }

        System.out.println("Total package weight: " + totalWeight + "kg\n");

        System.out.println("** Checkout Receipt **");

        for (CartItem item : cart.getProducts()) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            subtotal += product.getPrice() * quantity;

            if (product.isProductShippable() && product instanceof Shippable) {
                System.out.println(quantity + "x " + product.getName() + " " + (product.getPrice() * quantity));
            }
        }

        if (!shippingProducts.isEmpty()) {
            shippingFees = 30; // Flat shipping fee
        }

        total = subtotal + shippingFees;

        // Check if the customer has enough balance
        if (customer.getAccBalance() < total) {
            System.out.println("Error, Customer's balance is INSUFFICIENT");
            return;
        }

        customer.deductBalance(total);

        for (CartItem item : cart.getProducts()) {
            item.getProduct().quantityAfterPurchase(item.getQuantity());
        }

        if (!shippingProducts.isEmpty()) {
            ShippingService.processShipping(shippingProducts);
        }

        System.out.println("** Checkout Receipt **");

        for (CartItem item : cart.getProducts()) {
            if (item.getProduct().isProductShippable()) {
                System.out.println(item.getQuantity() + "x " + item.getProduct().getName() +
                        " " + (item.getProduct().getPrice() * item.getQuantity()));
            }
        }

        for (CartItem item : cart.getProducts()) {
            if (!item.getProduct().isProductShippable()) {
                System.out.println(item.getQuantity() + "x " + item.getProduct().getName() +
                        " " + (item.getProduct().getPrice() * item.getQuantity()));
            }
        }

        System.out.println("----------------------");
        System.out.println("Subtotal: " + subtotal);
        System.out.println("Shipping: " + shippingFees);
        System.out.println("Amount: " + total);

    }
}

class ShippingService {
    public static void processShipping(List<Shippable> items) {
        System.out.println("** Shipment Notice **");
        double totalWeight = 0;
        for (Shippable item : items) {
            System.out.println(item.getName() + " " + item.getWeight() + "kg");
            totalWeight += item.getWeight();
        }
        System.out.println("Total package weight: " + totalWeight + "kg\n");
    }
}