package ru.job4j.inharitance;

public class Teacher extends Proffesion {
    public String directivity;
    public String teaches(Person person){
        return "Преподаватель " + this.GetName() + " учит " + person.name;
    }
}
