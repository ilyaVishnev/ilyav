package ru.job4j.collection;

import java.util.*;

public class Time {
    public static long add(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add("newName" + i);
        }
        return System.currentTimeMillis() - start;
    }

    public static long delete(Collection<String> collection, int amount) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.remove("newName" + i);
        }
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<String>();
        long timeAddLink = add(linkedList, 100000);
        long timeDelLink = delete(linkedList, 100000);
        ArrayList<String> arrayList = new ArrayList<String>();
        long timeAddArray = add(arrayList, 100000);
        long timeDelArray = delete(arrayList, 100000);
        TreeSet<String> treeSet = new TreeSet<String>();
        long timeAddTree = add(treeSet, 100000);
        long timeDelTree = delete(treeSet, 100000);
        System.out.println("Время добавления для LinkedList : " + timeAddLink + " Время удаления для LinkedList : " + timeDelLink);
        System.out.println("Время добавления для ArrayList : " + timeAddArray + " Время удаления для ArrayList : " + timeDelArray);
        System.out.println("Время добавления для TreeSet : " + timeAddTree + " Время удаления для TreeSet : " + timeDelTree);
    }
}
