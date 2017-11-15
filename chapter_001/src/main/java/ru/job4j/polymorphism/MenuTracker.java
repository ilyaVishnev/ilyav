package ru.job4j.polymorphism;

import ru.job4j.incapsulation.*;

class FindItembyName implements UserAction {
    @Override
    public int key() {
        return 5;
    }

    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("Поиск заявки по её имени :");
        String name = input.ask("Введите имя заявки :");
        for (Item item : tracker.findByName(name)) {
            if (item != null) {
                System.out.println("Имя заявки " + item.getName() + " Описание : " + item.getDesc() + " id: " + item.getId());
            }
        }
    }

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "find Item by name");
    }
}

public class MenuTracker {
    private Input input;
    private Tracker tracker;
    private UserAction[] actions = new UserAction[6];

    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void fillAction() {
        this.actions[0] = new AddItem();
        this.actions[1] = new ShowItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindItembyId();
        this.actions[5] = new FindItembyName();
    }

    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please , enter the task's name: ");
            String desc = input.ask("Please , enter the task's description: ");
            Item item=new Task(name, desc);
            tracker.add(item);
            System.out.println("Новая заявка с getId : " + item.getId());
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "add the new Item");
        }
    }

    private static class ShowItems implements UserAction {
        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("Вывод всех заявок :");
            for (Item item : tracker.findAll()) {
                if (item != null) {
                    System.out.println("Имя " + item.getName() + " описание: " + item.getDesc());
                }
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "show all Items");
        }
    }

    private static class EditItem implements UserAction {
        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("Редактирование заявки :");
            System.out.println("Выберите нужную заявку по её id :");
            for (Item item : tracker.findAll()) {
                if (item != null) {
                    System.out.println("Номер заявки " + item.getId());
                }
            }
            System.out.println("\n");
            String id = input.ask("Введите id заявки :");
            Item item = tracker.findById(id);
            item.setName(input.ask("Введите новое имя :"));
            item.setDesc(input.ask("Введите новое описание :"));
            tracker.update(item);
            System.out.println("Заявка отредактирована успешно");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "edit Item");
        }
    }

    private static class DeleteItem implements UserAction {
        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("Удаление заявки :");
            System.out.println("Выберите нужную заявку по её id :");
            for (Item item : tracker.findAll()) {
                if (item != null) {
                    System.out.println("Номер заявки " + item.getId());
                }
            }
            System.out.println("\n");
            String id = input.ask("Введите id заявки :");
            Item item = tracker.findById(id);
            tracker.delete(item);
            System.out.println("Заявка удалена");
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "delete Item");
        }
    }

    private static class FindItembyId implements UserAction {
        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("Поиск заявки по её id :");
            System.out.println("Список всех id :");
            for (Item item : tracker.findAll()) {
                if (item != null) {
                    System.out.println(item.getId());
                }
            }
            String id = input.ask("Введите id заявки :");
            Item item = tracker.findById(id);
            System.out.println("Имя заявки: " + item.getName() + " описание заявки: " + item.getDesc());
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "find Item by id");
        }
    }
}
