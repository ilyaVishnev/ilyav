package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class RotateArrayTest {
    @Test
            public void whenArrayThreeThenRotate(){
        RotateArray rotateArray=new RotateArray();
        assertThat(rotateArray.rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}}),is(new int[][]{{7,4,1},{8,5,2},{9,6,3}}));
    }
    @Test
    public void whenArrayTwoThenRotate(){
        RotateArray rotateArray=new RotateArray();
        assertThat(rotateArray.rotate(new int[][]{{1,2},{3,4}}),is(new int[][]{{3,1},{4,2}}));
    }

}
