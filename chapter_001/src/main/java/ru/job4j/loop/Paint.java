package ru.job4j.loop;

public class Paint {
        public String piramid(int h){
            int widthPir = 2*h-1;
            StringBuilder stringbuilder=new StringBuilder();
            for(int i=0;i<h;i++){
                for(int j=0;j<widthPir;j++){
                    if(j>=widthPir/2-i && j<=widthPir/2+i){
                        stringbuilder.append("^");
                    }
                    else{
                        stringbuilder.append(" ");
                    }
                }
                stringbuilder.append("\n");
            }
            return stringbuilder.toString();
    }
}
