import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr = (Item[]) new Object[2];;

    private int size;    

	public boolean isEmpty() {

        return size == 0;
	}

	public int size() {

        return size;
	}

	public void enqueue(Item item) {
		if (item == null) {
            throw new NullPointerException();
        }
        if (arr.length == size) {
            resize(size * 2);
        }
        arr[size] = item;
        
        size++;
	}

	public Item dequeue() {
		if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int i = getRandom();
        Item item = arr[i];
        arr[i] = null;
        if (i != size - 1) {
            Item temp = arr[i];
            arr[i] = arr[size - 1];
            arr[size - 1] = temp;            
        }
        
        size--;
        
        if (size > 0 && size < arr.length / 4) {
            resize(arr.length / 2);
        }
        
        return item;
	}

	public Item sample() { // return a random item (but do not remove it)
		if (isEmpty()) {
            throw new NoSuchElementException();
        }
		
        return arr[getRandom()];
	}

	public Iterator<Item> iterator() { // return an independent iterator over items in random order

        return new Iter();
	}

    private class Iter implements Iterator<Item> {
        private int[] indexOrder;
        private int index;

        public Iter() {
            indexOrder = new int[size];
            index = 0;
            for (int i = 0; i < indexOrder.length; i++) {
                indexOrder[i] = i;
            }
            StdRandom.shuffle(indexOrder);
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return arr[indexOrder[index++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
	public static void main(String[] args) { // unit testing (optional)

	}

    private int getRandom() {
        return StdRandom.uniform(size);
    }
    
    private void resize(final int sz) {
        Item[] temp = (Item[]) new Object[sz];
        for (int i = 0; i < size; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }
}