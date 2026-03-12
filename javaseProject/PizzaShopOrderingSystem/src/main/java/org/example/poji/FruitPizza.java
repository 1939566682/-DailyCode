package org.example.poji;

/**
 * @author Yang QingBo
 * @date 2026-03-12 10:53
 */
public class FruitPizza extends Pizza {
    private String fruit;

    public FruitPizza() {
    }

    public FruitPizza(String pizzaName, int pizzaSize, double pizzaPrice, String fruit) {
        super(pizzaName, pizzaSize, pizzaPrice);
        this.fruit = fruit;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n配料水果：" + this.getFruit();
    }
}
