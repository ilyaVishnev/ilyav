package ru.job4j.chess;

public class Konj extends Figure {
    public Konj(Cell source) {
        super(source);
    }

    public Cell[] way(Cell dist) throws ImposibleMoveException {
        Cell[] way;
        if (Math.abs(dist.y - this.position.y) == 2 && Math.abs(dist.x - this.position.x) == 1) {
            way = new Cell[Math.abs(this.position.y - dist.y) + 1];
            for (int index = 0; index != way.length - 1; index++) {
                way[index] = new Cell(this.position.x, this.position.y + (index + 1) * ((dist.y - this.position.y) / Math.abs(this.position.y - dist.y)));
            }
            way[way.length - 1] = dist;
        } else if (Math.abs(dist.x - this.position.x) == 2 && Math.abs(dist.y - this.position.y) == 1) {
            way = new Cell[Math.abs(this.position.x - dist.x) + 1];
            for (int index = 0; index != way.length - 1; index++) {
                way[index] = new Cell(this.position.x + (index + 1) * ((dist.x - this.position.x) / Math.abs(this.position.x - dist.x)), this.position.y);
            }
            way[way.length - 1] = dist;
        } else {
            throw new ImposibleMoveException();
        }
        return way;
    }
}
