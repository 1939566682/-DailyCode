package org.example.poji;

/**
 * @author Yang QingBo
 * @date 2026-03-12 10:50
 */
public abstract class Pizza {
    private String pizzaName;
    private int pizzaSize;
    private double pizzaPrice;

    public Pizza() {
    }

    public Pizza(String pizzaName, int pizzaSize, double pizzaPrice) {
        this.pizzaName = pizzaName;
        this.pizzaSize = pizzaSize;
        this.pizzaPrice = pizzaPrice;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public int getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(int pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }

    @Override
    public String toString() {
        return "名称：" + this.getPizzaName() +
                "\n价格：" + this.getPizzaPrice() + "元" +
                "\n大小：" + this.getPizzaSize() + "寸";
    }
}
