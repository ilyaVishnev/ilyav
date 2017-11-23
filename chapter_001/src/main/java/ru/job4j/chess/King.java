package ru.job4j.chess;

public class King extends Figure {
    public King(Cell source) {
        super(source);
    }

    public Cell[] way(Cell dist) throws ImposibleMoveException {
        Cell[] way;
        if (Math.abs(this.position.x - dist.x) <= 1 && Math.abs(this.position.y - dist.y) <= 1) {
            way = new Cell[2];
            way[0] = new Cell(this.position.x, this.position.y);
            way[1] = new Cell(dist.x, dist.y);
        } else {
            throw new ImposibleMoveException();
        }
        return way;
    }
    public Figure clone(Cell dist){
        return new King(dist);
    }
}
