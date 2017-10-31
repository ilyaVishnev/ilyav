package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Ilya Vishnevsky (mailto:dong85@mail.ru)
* @version $Id$
* @since 0.1
*/
public class CalculateTest {
 /**
* Test echo.
*/ @Test
public void whenTakeNameThenTreeEchoPlusName() {
    String input = "Ilya Vishnevsky";
    String expect = "Echo, echo, echo : Ilya Vishnevsky"; 
    Calculate calc = new Calculate();
    String result = calc.echo(input);
    assertThat(result, is(expect));
}
}