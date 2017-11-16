package ru.job4j.polymorphism;

import ru.job4j.incapsulation.*;

class FindItembyName extends BaseAction {
    /* @Override
     public int key() {
         return 5;
     }*/
    public FindItembyName(String nameInfo, int key) {
        super(nameInfo, key);
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

    /*@Override
    public String info() {
        return String.format("%s. %s", this.key(), "find Item by name");
    }*/
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
        this.actions[0] = new AddItem("add the new Item", 0);
        this.actions[1] = new ShowItems("show all Items", 1);
        this.actions[2] = new EditItem("edit Item", 2);
        this.actions[3] = new DeleteItem("delete Item", 3);
        this.actions[4] = new FindItembyId("find Item by id", 4);
        this.actions[5] = new FindItembyName("find Item by name", 5);
    }

    public void select(Input input) {
        int[] range = {0, 1, 2, 3, 4, 5};
        this.actions[input.ask("Select: ", range)].execute(this.input, this.tracker);
    }

    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    private class AddItem extends BaseAction {
        /*   @Override
           public int key() {
               return 0;
           }*/
        public AddItem(String nameInfo, int key) {
            super(nameInfo, key);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Please , enter the task's name: ");
            String desc = input.ask("Please , enter the task's description: ");
            Item item = new Task(name, desc);
            tracker.add(item);
            System.out.println("Новая заявка с getId : " + item.getId());
        }

 /*       @Override
        public String info() {
            return String.format("%s. %s", super.key, "add the new Item");
        }*/
    }

    private static class ShowItems extends BaseAction {
        /* @Override
         public int key() {
             return 1;
         }*/
        public ShowItems(String nameInfo, int key) {
            super(nameInfo, key);
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

       /* @Override
        public String info() {
            return String.format("%s. %s", this.key(), "show all Items");
        }*/
    }

    private static class EditItem extends BaseAction {
        /* @Override
         public int key() {
             return 2;
         }*/
        public EditItem(String nameInfo, int key) {
            super(nameInfo, key);
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

    /*    @Override
        public String info() {
            return String.format("%s. %s", this.key(), "edit Item");
        }*/
    }

    private static class DeleteItem extends BaseAction {
        /* @Override
         public int key() {
             return 3;
         }*/
        public DeleteItem(String nameInfo, int key) {
            super(nameInfo, key);
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

        /*@Override
        public String info() {
            return String.format("%s. %s", this.key(), "delete Item");
        }*/
    }

    private static class FindItembyId extends BaseAction {
        /* @Override
         public int key() {
             return 4;
         }*/
        public FindItembyId(String nameInfo, int key) {
            super(nameInfo, key);
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

  /*      @Override
        public String info() {
            return String.format("%s. %s", this.key(), "find Item by id");
        }*/
    }
}
