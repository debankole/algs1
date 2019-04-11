import java.lang.annotation.Retention;
import java.util.Iterator;

import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {

	class Node {
		Node next;
		Node prev;
		Item item;
	}

	Node head;
	Node tail;
	int size = 0;

	public Deque() { // construct an empty deque

	}

	public boolean isEmpty() { // is the deque empty?
		return head == null;
	}

	public int size() { // return the number of items on the deque
		return size;
	}

	public void addFirst(Item item) { // add the item to the front
		if (item == null)
			throw new java.lang.IllegalArgumentException();

		if (size == 0) {
			head = new Node();
			head.item = item;

			tail = head;

			head.next = tail;
		} else {
			Node temp = head;
			head = new Node();
			head.item = item;

			head.next = temp;
			temp.prev = head;
		}

		size++;
	}

	public void addLast(Item item) { // add the item to the end
		if (item == null)
			throw new java.lang.IllegalArgumentException();

		if (size == 0) {
			head = new Node();
			head.item = item;

			tail = head;

			head.next = tail;
		} else {
			Node temp = tail;
			tail = new Node();
			tail.item = item;

			temp.next = tail;
			tail.prev = temp;
		}

		size++;
	}

	public Item removeFirst() { // remove and return the item from the front
		if (size == 0)
			throw new java.util.NoSuchElementException();

		Node temp = head;

		head = head.next;

		if (size == 1) {
			tail = null;
		} else {
			head.prev = null;
		}

		size--;

		return temp.item;
	}

	public Item removeLast() { // remove and return the item from the end
		if (size == 0)
			throw new java.util.NoSuchElementException();

		Node temp = tail;

		tail = tail.prev;

		if (size == 1) {
			tail = null;
		} else {
			tail.next = null;
		}

		size--;

		return temp.item;
	}

	public Iterator<Item> iterator() { // return an iterator over items in order from front to end
		return new Iter();
	}
	
	class Iter implements Iterator<Item>{

		Node curr = head;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return curr != null;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			
			if(curr==null)
				throw new java.util.NoSuchElementException();
			
			Item item = curr.item;
			curr = curr.next;
			return item;
		}
		
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }		
	}

	public static void main(String[] args) { // unit testing (optional)
		Deque<Integer> d = new Deque<Integer>();
		
		d.addFirst(1);
		d.addFirst(2);
		d.addFirst(3);
		d.addFirst(4);
		
		

		d.addLast(5);
		d.addLast(6);
		d.addLast(7);
		d.addLast(8);
		
		StdOut.println(d.removeFirst());
		StdOut.println(d.removeFirst());
		StdOut.println(d.removeFirst());
		StdOut.println(d.removeFirst());
		StdOut.println(d.removeFirst());
		StdOut.println(d.removeFirst());
		StdOut.println(d.removeFirst());
		StdOut.println(d.removeFirst());
		StdOut.println(d.removeFirst());

		StdOut.println(d.removeLast());
		StdOut.println(d.removeLast());
		StdOut.println(d.removeLast());
	}
}

/*
Throw a
 * java.util.NoSuchElementException if the client calls the next() method in the
 * iterator when there are no more items to return. Throw a
 * java.lang.UnsupportedOperationException if the client calls the remove()
 * method in the iterator.
 */