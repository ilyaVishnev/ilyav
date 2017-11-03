package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class PaintTest {
   @Test
   public void whenHighisTwoThenWidthThree(){
Paint paint=new Paint();
String result=paint.piramid(2);
assertThat(result,is(" ^ \n^^^\n"));
   }
    @Test
    public void whenHighThreeThenWidthFive(){
        Paint paint=new Paint();
        String result=paint.piramid(3);
        assertThat(result,is("  ^  \n ^^^ \n^^^^^\n"));
    }
}
