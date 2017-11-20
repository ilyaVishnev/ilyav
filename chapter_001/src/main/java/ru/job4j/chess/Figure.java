package ru.job4j.chess;

public abstract class Figure implements Cloneable {
    final Cell position;

    public Figure(Cell position) {
        this.position = position;
    }

    abstract Cell[] way(Cell dist) throws ImposibleMoveException;

    public Figure clone(Cell dist) {
        String type = this.getClass().getSimpleName().toString();
        Figure figure = null;
        switch (type) {
            case ("Tura"):
                figure = new Tura(dist);
                break;
            case ("King"):
                figure = new King(dist);
                break;
            case ("Queen"):
                figure = new Queen(dist);
                break;
            case ("Oficer"):
                figure = new Oficer(dist);
                break;
            case ("Peshka"):
                figure = new Peshka(dist);
                break;
            case ("Konj"):
                figure = new Konj(dist);
                break;
        }
        return figure;
    }
}
