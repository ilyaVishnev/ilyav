package ru.job4j.array;

public class Check {
   public boolean contains(String origin, String sub){
        char[]originArray=origin.toCharArray();
        char[]subArray=sub.toCharArray();
        int lengthSub=0,j=0,i=0;
        while(i<originArray.length && lengthSub!=subArray.length) {
            if (originArray[i++]==subArray[j++]){
             lengthSub++;
            }
            else{
             j=0;
             lengthSub=0;
            }
        }
        return lengthSub==subArray.length;
    }
}
