package ru.job4j.polymorphism;
import ru.job4j.incapsulation.*;

public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";
    /**
     * Константа меню для вывода всех заявок.
     */
    private static final String SHOW = "1";
    /**
     * Константа меню для редактирования заявок.
     */
    private static final String EDIT = "2";
    /**
     * Константа для удаления заявки.
     */
    private static final String DELETE = "3";
    /**
     * Константа для поиска по айди.
     */
    private static final String FINDID = "4";
    /**
     * Константа для поиска по имени.
     */
    private static final String FINDNAME = "5";
    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            switch (answer) {
                case ADD:
                this.createItem();
                break;
                case SHOW:
                    this.showAllItems();
                    break;
                case EDIT:
                    this.editItem();
                    break;
                case DELETE:
                     this.deleteItem();
                     break;
                case FINDID:
                     this.findItembyId();
                     break;
                case FINDNAME:
                     this.findItemsbyName();
                     break;
                case EXIT:
                      exit = true;
                      default:
                          break;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой языки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с getId : " + item.getId() + "-----------");
    }
    private void showAllItems() {
        System.out.println("Вывод всех заявок :");
        for (Item item : this.tracker.findAll()) {
            if (item != null) {
                System.out.println("Имя " + item.getName() + " описание: " + item.getDesc());
            }
        }
     }
    private void editItem() {
        System.out.println("Редактирование заявки :");
        System.out.println("Выберите нужную заявку по её id :");
        for (Item item : this.tracker.findAll()) {
            if (item != null) {
                System.out.println("Номер заявки " + item.getId());
            }
        }
        System.out.println("\n");
        String id = this.input.ask("Введите id заявки :");
        Item item = this.tracker.findById(id);
        item.setName(this.input.ask("Введите новое имя :"));
        item.setDesc(this.input.ask("Введите новое описание :"));
        this.tracker.update(item);
        System.out.println("Заявка отредактирована успешно");
    }
    private void deleteItem() {
        System.out.println("Удаление заявки :");
        System.out.println("Выберите нужную заявку по её id :");
        for (Item item : this.tracker.findAll()) {
            if (item != null) {
                System.out.println("Номер заявки " + item.getId());
            }
        }
        System.out.println("\n");
        String id = this.input.ask("Введите id заявки :");
        Item item = this.tracker.findById(id);
        this.tracker.delete(item);
        System.out.println("Заявка удалена");
    }
    private void findItembyId() {
        System.out.println("Поиск заявки по её id :");
        System.out.println("Список всех id :");
        for (Item item : this.tracker.findAll()) {
            if (item != null) {
                System.out.println(item.getId());
            }
        }
        String id = this.input.ask("Введите id заявки :");
        Item item = this.tracker.findById(id);
        System.out.println("Имя заявки: " + item.getName() + " описание заявки: " + item.getDesc());
    }
    private void findItemsbyName() {
        System.out.println("Поиск заявки по её имени :");
        String name = this.input.ask("Введите имя заявки :");
        for (Item item : this.tracker.findByName(name)) {
            if (item != null) {
                System.out.println("Имя заявки " + item.getName() + "Описание : " + item.getDesc() + " id: " + item.getId());
            }
        }
    }

    private void showMenu() {
        System.out.println("Меню.");
        // добавить остальные пункты меню.
    }

    /**
     * Запускт программы.
     * @param args
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
