package com.company;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.DeflaterOutputStream;

class MyArray {
    private Integer[] array;

    public MyArray(Integer[] array) {
        this.array = array;
    }

    public void sort(Comparator<Integer> comparator) {
        boolean isSort;
        int temp;
        int offset = 0;

        do {
            isSort = true;
            for (int i = 0; i < array.length - 1 - offset; i++) {
                if (comparator.compare(array[i], array[i + 1]) > 0) {
                    isSort = false;
                    temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }

            offset++;
        } while (isSort == false);
    }

    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void printByCondition(Predicate<Integer> predicate) {
        for (int i = 0; i < array.length; i++) {
            if (predicate.test(array[i]) == true) {
                System.out.print(array[i] + " ");
            }
        }
        System.out.println();
    }

    public void printByStream() {
        Arrays.stream(array).forEach(item -> System.out.print(item + " "));
        System.out.println();
    }

    public void printByConditionByStream(Predicate<Integer> predicate) {
        Arrays.stream(array).forEach(item -> System.out.print(predicate.test(item) ? item + " " : ""));
        System.out.println();
    }

    public void sortByStream(Comparator<Integer> comparator) {
        array = Arrays.stream(array).sorted(comparator).toArray(Integer[]::new);
    }
}

public class Main {

    public static void main(String[] args) {
        Integer[] array = new Integer[]{5, 3, 23, 12, 46};
        MyArray myArray = new MyArray(array);

        //myArray.print();
        //myArray.sort(new MaxToMinComparator());
        /*myArray.sort(new MyComparator() {
            @Override
            public boolean needChange(int a, int b) {
                return b < a;
            }
        });*/

        //myArray.sort((a, b) -> b - a);

        myArray.printByStream();
        myArray.printByConditionByStream(item -> item % 2 == 1);
        myArray.sortByStream((a, b) -> b - a);
        myArray.printByStream();
    }
}
