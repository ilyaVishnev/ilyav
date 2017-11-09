package ru.job4j.inharitance;

public class Doctor extends Proffesion{
public String directivity;
public String cure(Engineer engineer){
    return "Доктор "+this.GetName() +" лечит больного инженера "+engineer.GetName();
}
}
