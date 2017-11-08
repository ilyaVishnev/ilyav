package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class CheckTest {
    @Test
    public void whenOriginHasThenTrue(){
        Check check=new Check();
        assertThat(check.contains("Privet","ive"),is(Boolean.TRUE));
    }
}
