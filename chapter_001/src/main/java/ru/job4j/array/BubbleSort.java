package ru.job4j.array;

public class BubbleSort {
    public int[] sort(int[] array){
        for(int i=1;i<array.length;i++) {
            int max = array[0];
            for (int j = 1; j < array.length; j++) {
                if (max > array[j]) {
                    array[j - 1] = array[j];
                    array[j] = max;
                }
                else{
                    max=array[j];
                }
            }
        }
        return array;
    }
}
