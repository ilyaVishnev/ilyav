package ru.job4j.chess;

public class Queen extends Figure {
    public Queen(Cell source) {
        super(source);
    }

    public Cell[] way(Cell dist) throws ImposibleMoveException {
        Oficer oficer = new Oficer(this.position);
        Tura tura = new Tura(this.position);
        if ((this.position.x == dist.x) || (this.position.y == dist.y)) {
            return tura.way(dist);
        } else if (Math.abs(this.position.x - dist.x) == Math.abs(this.position.y - dist.y)) {
            return oficer.way(dist);
        } else {
            throw new ImposibleMoveException();
        }
    }
}
