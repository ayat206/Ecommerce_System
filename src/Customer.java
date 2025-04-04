import java.util.List;

public class Customer {

    private String name;
    private Cart cart;
    private double accBalance;

    public Customer(String name, double accBalance, List<Product> products) {
        this.name = name;
        this.accBalance = accBalance;
        this.cart = new Cart();
    }

    public String getName(){
        return name;
    }

    public Cart getCart(){
        return cart;
    }

    public double getAccBalance(){
        return accBalance;
    }

    public void deductBalance(double amount){
        this.accBalance -= amount;
    }
}
