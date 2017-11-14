package ru.job4j.shapes;

import org.junit.Test;
import ru.job4j.condition.Point;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;

import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
public class ShapeTest {
   @Test
   public void whenDrawThenTriangle() {
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append("  ^   \n");
       stringBuilder.append(" / \\ \n");
       stringBuilder.append("/___\\\n");
       ByteArrayOutputStream out = new ByteArrayOutputStream();
       System.setOut(new PrintStream(out));
       new Paint().draw(new Triangle());
       assertThat(String.format(out.toString()), is(stringBuilder.toString()));
   }
    @Test
    public void whenDrawThenSquare() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|-----|\n");
        stringBuilder.append("|     |\n");
        stringBuilder.append("|-----|\n");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(String.format(out.toString()), is(stringBuilder.toString()));
    }
}
