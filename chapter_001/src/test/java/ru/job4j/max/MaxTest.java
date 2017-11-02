package ru.job4j.max;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class MaxTest {
    @Test
    public void whenOneLessThenFour() {
        Max maxim = new Max();
        int result = maxim.max(1, 4);
        assertThat(result, is(4));
    }
}