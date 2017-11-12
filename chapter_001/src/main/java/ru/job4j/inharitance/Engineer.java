package ru.job4j.inharitance;

public class Engineer extends Proffesion {
    public String post;
    public String build() {
        return "Инженер" + this.getName() + " строит здание";
    }
}
