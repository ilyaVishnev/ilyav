package ru.job4j.collection;

import java.util.*;

public class ConvertList {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new LinkedList<Integer>();
        for (int[] firstArray : array) {
            for (int element : firstArray) {
                list.add(element);
            }
        }
        return list;
    }

    public int[][] toArray(List<Integer> list, int rows) {
        Iterator<Integer> iterator = list.listIterator();
        int rest = list.size() % rows == 0 ? 0 : 1;
        int[][] array = new int[rows][list.size() / rows + rest];
        for (int[] firstArray : array) {
            for (int i = 0; i < firstArray.length; i++) {
                firstArray[i] = iterator.hasNext() ? iterator.next() : 0;
            }
        }
        return array;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> newlist = new LinkedList<Integer>();
        Iterator<int[]> iterator = list.listIterator();
        for (int[] array : list) {
            for (int j = 0; j < array.length; j++) {
                newlist.add(array[j]);
            }
        }
        return newlist;
    }
}
