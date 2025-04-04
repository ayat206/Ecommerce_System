public interface Shippable {
    double getWeight();
    String getName();
}

class ShippableProduct extends Product implements Shippable{

    private double weight;

    public ShippableProduct(String name, double price, int quantity,double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }


    @Override
    public boolean isProductExpired() {
        return false;
    }

    @Override
    public boolean isProductShippable() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}
