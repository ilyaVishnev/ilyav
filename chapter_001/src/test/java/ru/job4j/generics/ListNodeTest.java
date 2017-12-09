package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListNodeTest {
    @Test
    public void whenHaveCycleThenTrue() {
        ListNode listNode = new ListNode();
        ListNode.Node n1 = listNode.new Node(1);
        ListNode.Node n2 = listNode.new Node(2);
        ListNode.Node n3 = listNode.new Node(3);
        ListNode.Node n4 = listNode.new Node(4);
        ListNode.Node n5 = listNode.new Node(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n3;
        assertThat(listNode.hasCycle(n1), is(true));
    }
}
