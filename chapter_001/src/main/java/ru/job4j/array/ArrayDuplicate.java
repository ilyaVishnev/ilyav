package ru.job4j.array;
import java.util.Arrays;

public class ArrayDuplicate {
    public String[] remove(String[] array){
        int n=0;
     for(int i=0;i<array.length;i++){
     for(int j=0;j<array.length;j++){
     if(array[i]!=null && array[i].equals(array[j]) && i!=j)
         array[j]=(String) null;
     }
     }
    for(int i=0;i<array.length;i++){
         if(array[i]==null){
             n++;
            for(int j=i+1;j<array.length;j++){
                 if(array[j]!=null) {
                     array[i] = array[j];
                     array[j]=null;
                     n--;
                     break;
                 }
             }
         }
     }
     return Arrays.copyOf(array,array.length-n);
    }
}
