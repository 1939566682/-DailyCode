package org.example.pojo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Yang QingBo
 * {@code @date} 2026-03-23 16:15
 */

public class Person {
    private String[] arr;
    private List<String> list;
    private Set<String> set;
    private Map<Integer, String> map;

    public void setArr(String[] arr) {
        this.arr = arr;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }

    public Person() {
    }

    public Person(String[] arr, List<String> list, Set<String> set, Map<Integer, String> map) {
        this.arr = arr;
        this.list = list;
        this.set = set;
        this.map = map;
    }

    @Override
    public String toString() {
        return "Person{" +
                "arr=" + Arrays.toString(arr) +
                ", list=" + list +
                ", set=" + set +
                ", map=" + map +
                '}';
    }
}
