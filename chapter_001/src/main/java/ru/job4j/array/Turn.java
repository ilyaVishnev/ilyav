package ru.job4j.array;

public class Turn {
    public int[] back(int[] array){
        int buffer;
            for(int i=0;i<array.length/2;i++){
                buffer=array[i];
                array[i]=array[array.length-1-i];
                array[array.length-1-i]=buffer;
            }
        return array;
    }
}
