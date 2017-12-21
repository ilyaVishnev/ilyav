package ru.job4j.tree;

import java.util.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.*;
import javax.xml.stream.*;
public class OrderBook {


    public static void main(String[] args)
    {
        final String fileName = "orders.xml";
        List<Book> bookList = new ArrayList<>();
        try {
            XMLStreamReader reader = XMLInputFactory.newInstance().createXMLStreamReader(fileName, new FileInputStream(fileName));
            OrderBook orderBook=new OrderBook();
            bookList=orderBook.readDocument(reader);
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        }
        for (Book book1 : bookList) {
            System.out.println("Order book: " + book1.name);
            System.out.println("BID       ASK");
            for (Order order1 : book1.bid) {
                for (Order order2 : order1.match) {
                    System.out.println(order1.volume + "@" + order1.price + "    " + order2.volume + "@" + order2.price);
                }
            }
        }
}
    private List<Book> readDocument(XMLStreamReader reader) throws XMLStreamException {
        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals("Orders"))
                        return orders(reader);
                    break;
                case XMLStreamReader.END_ELEMENT:
                    break;
            }
        }
        throw new XMLStreamException("Premature end of file");
    }
    private List<Book> orders(XMLStreamReader reader) throws XMLStreamException {
        List<Book> books = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();
            switch (eventType) {
                case XMLStreamReader.START_ELEMENT:
                    String elementName = reader.getLocalName();
                    if (elementName.equals("AddOrder")){
                        books.add(readOrder(reader,books));
                    }else{
                        Book book=readOrder(reader,books);
                        Iterator<Book> iterator = books.iterator();
                        Book example = new Book();
                        while (iterator.hasNext()) {
                            if ((example = iterator.next()).equals(book)) {
                                example=book;
                                break;
                            }
                        }
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    return books;
            }
        }
        throw new XMLStreamException("Premature end of file");
    }
    private Book readOrder(XMLStreamReader reader,List<Book>bookList) throws XMLStreamException {
        Book book = new Book();
        Order order = new Order();
        boolean ask = false;

        for (int i = 0; i < reader.getAttributeCount(); i++) {
            switch (reader.getAttributeLocalName(i)) {
                case "book":
                    Iterator<Book> iterator = bookList.iterator();
                    Book example = new Book();
                    book.name = reader.getAttributeValue(i);
                    boolean faund = false;
                    while (iterator.hasNext()) {
                        if ((example = iterator.next()).equals(book)) {
                            book = example;
                            faund = true;
                            break;
                        }
                    }
                    if (!faund) {
                        bookList.add(book);
                    }
                    break;
                case "operation":
                    if (reader.getAttributeValue(i).equals("SELL")) {
                        book.ask.add(order);
                        ask = true;
                    } else {
                        book.bid.add(order);
                    }
                    break;
                case "price":
                    order.price = Double.parseDouble(reader.getAttributeValue(i));
                    break;
                case "volume":
                    order.volume = Integer.parseInt(reader.getAttributeValue(i));
                    break;
                case "orderId":
                    order.orderId = Integer.parseInt(reader.getAttributeValue(i));
                    break;
            }
        }
        return book;
    }
}
