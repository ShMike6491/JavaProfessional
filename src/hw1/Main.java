package hw1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {1, 2, 3, 4};
        String[] arr1 = {"one", "two", "three", "four"};
        String test = "adfaf";

//        System.out.println(swap(arr, 5, 1));
//        System.out.println(Arrays.toString(arr));
//        System.out.println(swap(arr1, 0, 1));
//        System.out.println(Arrays.toString(arr1));

//        ArrayList myArr = convert(arr);

        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();

        Orange orange1 = new Orange();
        Orange orange2 = new Orange();

        Box<Apple> box1 = new Box<>(apple1, apple2, apple3);
        Box<Orange> box2 = new Box<>(orange1, orange2);

        System.out.println(box1.compare(box2));

        Box<Orange> box3 = new Box<>();
        box2.transfer(box3);

    }

    public static <T> boolean swap (T[] type, int a, int b) {
        try{
            T temp = type[a];
            type[a] = type[b];
            type[b] = temp;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }

    public static <T> ArrayList convert (T... type) {
        return new ArrayList<>(Arrays.asList(type));
    }
}
