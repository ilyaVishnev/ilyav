package ru.job4j.chess;

public class Konj extends Figure {
    public Konj(Cell source) {
        super(source);
    }

    public Cell[] way(Cell dist) throws ImposibleMoveException {
        Cell[] way = new Cell[1];
        if ((Math.abs(dist.y - this.position.y) == 2 && Math.abs(dist.x - this.position.x) == 1) || (Math.abs(dist.x - this.position.x) == 2 && Math.abs(dist.y - this.position.y) == 1)) {
            way[0] = new Cell(dist.x, dist.y);
        } else {
            throw new ImposibleMoveException();
        }
        return way;
    }

    public Figure clone(Cell dist) {
        return new Konj(dist);
    }
}
