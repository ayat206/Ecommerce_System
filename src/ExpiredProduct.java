import java.util.Date;

public class ExpiredProduct extends Product{
    private Date expireDate;

    public ExpiredProduct(String name, double price, int quantity, Date expireDate) {
        super(name, price, quantity);
        this.expireDate = expireDate;
    }


    @Override
    public boolean isProductExpired() {
        return new Date().after(expireDate); //lw date nharda rg3 false yb2a tmm msh expired
    }

    @Override
    public boolean isProductShippable() {
        return false;
    }
}
