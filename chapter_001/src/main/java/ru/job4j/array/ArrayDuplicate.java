package ru.job4j.array;
import java.util.Arrays;

public class ArrayDuplicate {
    public String[] remove(String[] array){
        int dublicate=array.length-1;
 for(int out=0;out<array.length;out++){
     for(int in=out+1;in<=dublicate;in++){
         if(array[out].equals(array[in])){
             array[in]=array[dublicate];
             dublicate--;
         }

     }
 }
     return Arrays.copyOf(array,dublicate+1);
    }
}
