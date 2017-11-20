package ru.job4j.chess;

public class Oficer extends Figure {
    public Oficer(Cell source) {
        super(source);
    }

    public Cell[] way(Cell dist) throws ImposibleMoveException {
        Cell[] way = new Cell[Math.abs(this.position.y - dist.y)];
        if ((this.position.x - dist.x) == (this.position.y - dist.y)) {
            for (int index = 0; index != way.length; index++) {
                way[index] = new Cell(this.position.x + (index + 1) * ((dist.x - this.position.x) / Math.abs(this.position.x - dist.x)), this.position.y + (index + 1) * ((dist.y - this.position.y) / Math.abs(this.position.y - dist.y)));
            }
        } else if ((this.position.x - dist.x) == -1 * (this.position.y - dist.y)) {
            for (int index = 0; index != way.length; index++) {
                way[index] = new Cell(this.position.x + (index + 1) * ((dist.x - this.position.x) / Math.abs(this.position.x - dist.x)), this.position.y + (index + 1) * ((dist.y - this.position.y) / Math.abs(this.position.y - dist.y)));
            }
        } else {
            throw new ImposibleMoveException();
        }
        return way;
    }
}
