package ru.job4j.shapes;

public class Square implements Shape {
    @Override
    public String pic() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("|-----|\n");
        stringBuilder.append("|     |\n");
        stringBuilder.append("|-----|\n");
        return stringBuilder.toString();
    }
}
