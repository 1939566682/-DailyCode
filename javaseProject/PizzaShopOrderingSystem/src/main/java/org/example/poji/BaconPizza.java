package org.example.poji;

/**
 * @author Yang QingBo
 * @date 2026-03-12 10:53
 */
public class BaconPizza extends Pizza {
    private int grams;

    public BaconPizza() {
    }

    public BaconPizza(String pizzaName, int pizzaSize, double pizzaPrice, int grams) {
        super(pizzaName, pizzaSize, pizzaPrice);
        this.grams = grams;
    }

    public int getGrams() {
        return grams;
    }

    public void setGrams(int grams) {
        this.grams = grams;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n培根克数：" + this.getGrams() + "克";
    }

}
