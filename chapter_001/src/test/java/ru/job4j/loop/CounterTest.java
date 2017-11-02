package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class CounterTest {
   @Test
   public void whenFromTwotoeightThenTwenty(){
       Counter counter=new Counter();
       int result=counter.add(2,8);
       assertThat(result,is(20));
   }
}
