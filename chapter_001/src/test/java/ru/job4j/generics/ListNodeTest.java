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
        ListNode.Node node = listNode.new Node(1);
        ListNode.Node node2 = listNode.new Node(2);
        ListNode.Node node3 = listNode.new Node(3);
        ListNode.Node node4 = listNode.new Node(4);
        ListNode.Node node5 = listNode.new Node(5);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        assertThat(listNode.hasCycle(node), is(false));
        node4.next = node2;
        assertThat(listNode.hasCycle(node), is(true));
    }
}
