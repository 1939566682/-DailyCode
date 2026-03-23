package org.example.factory;

import org.example.pojo.Animal;
import org.example.pojo.Cat;
import org.example.pojo.Dog;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 14:21
 */

public class PetStore {

    public Animal getAnimal(String petName) {
        if ("cat".equals(petName)) {
            return new Cat();
        } else if ("dog".equals(petName)) {
            return new Dog();
        } else {
            return null;
        }
    }

    public static Animal getAnimal2(String petName) {
        if ("cat".equals(petName)) {
            return new Cat();
        } else if ("dog".equals(petName)) {
            return new Dog();
        } else {
            return null;
        }
    }
}
