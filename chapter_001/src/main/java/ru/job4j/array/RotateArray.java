package ru.job4j.array;

public class RotateArray {
    public int[][] rotate(int[][] array){
        int buffer1=0;
        int buffer2=0;
        for(int i=0;i<array.length/2;i++){
            for(int j=0+i;j<array.length-1-i;j++){
                buffer1=array[j][array.length-1-i];
                array[j][array.length-1-i]=array[i][j];
                buffer2=array[array.length-1-i][array.length-1-j];
                array[array.length-1-i][array.length-1-j]=buffer1;
                buffer1=array[array.length-1-j][i];
                array[array.length-1-j][i]=buffer2;
                array[i][j]=buffer1;
            }
        }
        return array;
    }
}
