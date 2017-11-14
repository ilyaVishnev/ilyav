package ru.job4j.shapes;

public class Triangle implements Shape {
    @Override
    public String pic() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  ^   \n");
        stringBuilder.append(" / \\ \n");
        stringBuilder.append("/___\\\n");
        return stringBuilder.toString();
    }
}
