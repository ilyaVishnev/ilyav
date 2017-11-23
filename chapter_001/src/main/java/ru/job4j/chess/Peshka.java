package ru.job4j.chess;

public class Peshka extends Figure {
    public Peshka(Cell source) {
        super(source);
    }

    public Cell[] way(Cell dist) throws ImposibleMoveException {
        Cell[] way;
        if (this.position.y == 1 && dist.x == this.position.x && dist.y - this.position.y == 2) {
            way = new Cell[3];
            way[0] = new Cell(this.position.x, this.position.y);
            way[1] = new Cell(this.position.x, this.position.y + 1);
            way[2] = new Cell(this.position.x, dist.y);
        } else if (dist.x == this.position.x && dist.y - this.position.y == 1) {
            way = new Cell[2];
            way[0] = new Cell(this.position.x, this.position.y);
            way[2] = new Cell(this.position.x, dist.y);
        } else {
            throw new ImposibleMoveException();
        }
        return way;
    }
    public Figure clone(Cell dist){
        return new Peshka(dist);
    }
}
