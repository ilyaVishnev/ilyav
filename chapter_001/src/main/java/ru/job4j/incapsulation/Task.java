package ru.job4j.incapsulation;

public class Task extends Item {
public Task(String name, String desc, Long create) {
    super(name, desc, create);
}
    public Task(String name, String desc) {
        super(name, desc);
    }
    public String calculatePrice() {
        return "100%";
    }
}
