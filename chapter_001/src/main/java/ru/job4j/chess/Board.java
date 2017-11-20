package ru.job4j.chess;

public class Board {
    Figure[] figures;

    public Board(Figure[] figures) {
        this.figures = figures;
    }

    boolean move(Cell source, Cell dist) throws ImposibleMoveException, OccupiedWayException, FigureNotFoundException {
        boolean find = false;
        boolean occupied = false;
        Figure figure = null;
        int index = 0;
        for (int i = 0; i != figures.length; i++) {
            if (source.equals(figures[i].position)) {
                find = true;
                figure = figures[i];
                index = i;
                break;
            }
        }
        if (!(figure instanceof Konj)) {
            Cell[] way = figure.way(dist);
            for (int i = 0; i != way.length; i++) {
                for (int j = 0; j != figures.length; j++) {
                    if (way[i].equals(figures[j].position)) {
                        occupied = true;
                        break;
                    }
                }
            }
        } else {
            for (int j = 0; j != figures.length; j++) {
                if (dist.equals(figures[j].position)) {
                    occupied = true;
                    break;
                }
            }
        }
        if (!find) {
            throw new FigureNotFoundException();
        } else if (occupied) {
            throw new OccupiedWayException();
        } else {
            figures[index] = figure.clone(dist);
            return true;
        }
    }
}
