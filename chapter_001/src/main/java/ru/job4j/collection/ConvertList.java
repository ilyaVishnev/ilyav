package ru.job4j.collection;

import java.util.*;

public class ConvertList {
    public static List<Integer> toList(int[][] array) {
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                list.add(array[i][j]);
            }
        }
        return list;
    }

    public static int[][] toArray(List<Integer> list, int rows) {
        Iterator<Integer> iterator = list.listIterator();
        int rest = list.size() % rows == 0 ? 0 : 1;
        int[][] array = new int[rows][list.size() / rows + rest];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < list.size() / rows + rest; j++) {
                array[i][j] = iterator.hasNext() ? iterator.next() : 0;
            }
        }
        return array;
    }

    public static List<Integer> convert(List<int[]> list) {
        List<Integer> newlist = new LinkedList<Integer>();
        Iterator<int[]> iterator = list.listIterator();
        for (int i = 0; iterator.hasNext(); i++) {
            int[] array = iterator.next();
            for (int j = 0; j < array.length; j++) {
                newlist.add(array[j]);
            }
        }
        return newlist;
    }
}
