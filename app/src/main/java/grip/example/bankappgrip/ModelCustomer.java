package grip.example.bankappgrip;

public class ModelCustomer {
    String name;
    int bal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBal() {
        return bal;
    }

    @Override
    public String toString() {
        return "ModelCustomer{" +
                "name='" + name + '\'' +
                ", bal=" + bal +
                '}';
    }

    public void setBal(int bal) {
        this.bal = bal;
    }

    public ModelCustomer(String name, int bal) {
        this.name = name;
        this.bal = bal;
    }
}
