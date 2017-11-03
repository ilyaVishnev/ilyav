package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class TurnTest {
    @Test
    public void whenOddArraythen(){
        Turn turn=new Turn();
        int[]a = new int[]{1, 2, 3, 4, 5};
        assertThat(turn.back(a),is(new int[]{5,4,3,2,1}));
    }
    @Test
    public void whenEvenArraythen(){
        Turn turn=new Turn();
        assertThat(turn.back(new int[]{4, 1, 6, 2}),is(new int[]{2,6,1,4}));
    }
}
