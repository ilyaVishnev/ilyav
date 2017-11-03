package ru.job4j.loop;

public class Board {
    public String paint(int width, int height){
        StringBuilder stringbuilder=new StringBuilder();
        for(int i=1;i<=height;i++){
            for(int j=1;j<=width;j++){
                if(i%2!=0 && j%2!=0){
                    stringbuilder.append("X");
                }
                else if(i%2==0 && j%2==0){
                    stringbuilder.append("X");
                }
                else{
                    stringbuilder.append(" ");
                }
            }
            stringbuilder.append("\n");
        }
        return  stringbuilder.toString();
    }
}
