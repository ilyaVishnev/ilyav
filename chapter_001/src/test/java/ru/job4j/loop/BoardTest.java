package ru.job4j.loop;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class BoardTest {
    @Test
    public void whenThreeOnThreeThen(){
        Board board=new Board();
        String result=board.paint(3,3);
        assertThat(result,is("X X\n X \nX X\n"));
    }
    @Test
    public void whenFiveOnFourThen(){
        Board board=new Board();
        String result=board.paint(5,4);
        assertThat(result,is("X X X\n X X \nX X X\n X X \n"));
    }
}
