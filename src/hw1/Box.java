package hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box <T extends Fruit> {
    private ArrayList<T> fruits;

    public Box(T... fruits) {
        this.fruits = new ArrayList<T>(Arrays.asList(fruits));
    }

    public void add(T... items) {
        fruits.addAll(Arrays.asList(items));
    }

    public void remove(T... items) {
        for (T item : items)
            fruits.remove(item);
    }

    public void clear() {
        fruits.clear();
    }

    public ArrayList<T> getItems() {
        return new ArrayList<T>(fruits);
    }

    public float getWeight () {
        float sum = 0;
        for (T fruit : fruits) {
            sum += fruit.getWeight();
        }
        return sum;
    }

    public boolean compare(Box box) {
        return (this.getWeight() == box.getWeight());
    }

    public void transfer(Box<? super T> box) {
        box.fruits.addAll(this.fruits);
        clear();
    }
}
