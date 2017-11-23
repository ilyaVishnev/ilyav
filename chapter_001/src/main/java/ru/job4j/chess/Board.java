package ru.job4j.chess;

public class Board {
    static Figure[] figures;
    static int index = 0;

    public Board(Figure[] figures) {
        this.figures = figures;
    }

    boolean move(Cell source, Cell dist) throws ImposibleMoveException, OccupiedWayException, FigureNotFoundException {
        Figure figure = indexOf(source);
        if (figure == null) {
            throw new FigureNotFoundException();
        }
        if (occupied(figure, dist)) {
            throw new OccupiedWayException();
        } else {
            figures[index] = figure.clone(dist);
            return true;
        }
    }

    public static Figure indexOf(Cell source) {
        Figure figure = null;
        for (int i = 0; i != figures.length; i++) {
            if (source.equals(figures[i].position)) {
                figure = figures[i];
                index = i;
                break;
            }
        }
        return figure;
    }

    public static boolean occupied(Figure figure, Cell dist) throws ImposibleMoveException {
        boolean occupied = false;
        Cell[] way = figure.way(dist);
        for (int i = 0; i != way.length; i++) {
            for (int j = 0; j != figures.length; j++) {
                if (way[i].equals(figures[j].position)) {
                    occupied = true;
                    break;
                }
            }
        }
        return occupied;
    }
}
