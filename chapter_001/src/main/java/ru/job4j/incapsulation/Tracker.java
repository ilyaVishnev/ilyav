package ru.job4j.incapsulation;

import java.util.*;

public class Tracker {
    /**
     * Массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<Item>();//Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;
    private final static Random RN = new Random();

    /**
     * Метод реализаущий добавление заявки в хранилище
     *
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);//this.items[this.position++] = item;
        this.position++;
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     *
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод возвращает Заявку по уник. ключу.
     *
     * @return Заявка.
     */
    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает полный список заявок.
     *
     * @return Список.
     */
    public List<Item> findAll() {
        List<Item> result = new ArrayList<Item>();//Item[] result = new Item[this.position];
        result.addAll(items);
        /*for (int index = 0; index != position; index++) {
            result[index] = this.items[index];
        }*/
        return result;
    }

    /**
     * Метод находит заявку по уникальному ключу в списке и меняет её на указаную в параметре.
     *
     * @return .
     */
    public void update(Item item) {
        for (int index = 0; index != items.size(); index++) {
            if (items.get(index) != null && items.get(index).getId().equals(item.getId())) {
                items.set(index, item);
                break;
            }
        }
    }

    /**
     * Метод находит заявку по уникальному ключу в списке и удаляет её.
     *
     * @return .
     */
    public void delete(Item item) {
        for (int index = 0; index != items.size(); index++) {
            if (items.get(index) != null && items.get(index).getId().equals(item.getId())) {
                items.set(index, null);
                break;
            }
        }
    }

    /**
     * Метод находит список заявок с указанным именем.
     *
     * @return Список.
     */
    public List<Item> findByName(String key) {
        // Item[] prevresult = new Item[this.position];
        List<Item> result = new ArrayList<Item>();
        // int i = 0;
        for (Item item : items) {
            if (item != null && item.getName().equals(key)) {
                result.add(item);
            }
        }
        /*Item[] result = new Item[i];
        for (int j = 0; j < i; j++) {
            result[j] = prevresult[j];
        }*/
        return result;
    }
}
