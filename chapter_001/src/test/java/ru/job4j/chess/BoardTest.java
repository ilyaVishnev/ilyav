package ru.job4j.chess;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BoardTest {
    @Test
    public void whenOficerjumpThenrichPlace() {
        Oficer oficer = new Oficer(new Cell(2, 2));
        Cell[] arrayOficer = null;
        String wayOficer = new String();
        try {
            arrayOficer = oficer.way(new Cell(4, 4));
        } catch (ImposibleMoveException ex) {
            System.out.println(ex.toString());
        }
        for (Cell cell : arrayOficer) {
            wayOficer += (cell + "\n");
        }
        assertThat(wayOficer, is(" x: 3 y: 3\n" +
                " x: 4 y: 4\n"));
    }

    @Test
    public void whenOficerjumpThenNobodyontheWay() {
        Figure[] array = new Figure[]{new Tura(new Cell(0, 0)), new Oficer(new Cell(2, 0)), new Konj(new Cell(0, 1)), new Queen(new Cell(3, 1)), new Peshka(new Cell(1, 2))};
        Board board = new Board(array);
        String newPlaceOficer = new String();
        boolean result = false;
        try {
            result = board.move(new Cell(2, 0), new Cell(0, 2));
            for (Figure figure : array) {
                newPlaceOficer += (figure.getClass().getSimpleName() + " : " + figure.position + "\n");
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        assertThat(result, is(true));
        assertThat(newPlaceOficer, is("Tura :  x: 0 y: 0\nOficer :  x: 0 y: 2\nKonj :  x: 0 y: 1\nQueen :  x: 3 y: 1\nPeshka :  x: 1 y: 2\n"));
    }
}
