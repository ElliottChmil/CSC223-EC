package csc223.ec;

import java.util.Arrays;

public class MinHeap implements Heap {
    
    char[] heap;
    int size;
    int capacity;

    public MinHeap() {
        this.heap = new char[10];
        Arrays.fill(this.heap, '~');
        this.size = 0;
        this.capacity = 10;
    }

    // Add a new item to the heap
    public void insert(char value) {
        // Expand the array if capacity is reached
        if (this.size == this.capacity) {
            this.capacity = this.capacity * 2;
            char[] newArray = new char[this.capacity];
            for (int i = 0; i < this.capacity; i++) {
                newArray[i] = this.heap[i];
            }
            this.heap = newArray;
        }
        // Insert after the last item and calculate the parent
        int idx = this.size;
        this.heap[idx] = value;
        int parent = (int) Math.floor((idx - 1) / 2);

        // Bubble the item to its right spot
        while (this.heap[idx] < this.heap[parent]) {
            char temp = this.heap[parent];
            this.heap[parent] = this.heap[idx];
            this.heap[idx] = temp;
            idx = parent;
            parent = (int) Math.floor((idx - 1) / 2);
        }
        this.size += 1;
    }

    // Removes the top of the heap while maintaining heap order
    public void delete() {
        // Put the last item in the root and delete the last item
        this.heap[0] = this.heap[this.size - 1];
        this.heap[this.size - 1] = '~';
        this.size -= 1;

        // Start at root and its children
        int idx = 0;
        int leftChild = (2 * idx) + 1;
        int rightChild = (2 * idx) + 2;
        
        while ((this.heap[leftChild] < this.heap[idx]) || (this.heap[rightChild] < this.heap[idx])) {
        // If the left child is less than the right child
        if (this.heap[leftChild] <= this.heap[rightChild]) {
            char temp = this.heap[leftChild];
            this.heap[leftChild] = this.heap[idx];
            this.heap[idx] = temp;
            idx = leftChild;
            leftChild = (2 * idx) + 1;
            rightChild = (2 * idx) + 2;
        }
        // If the right child is less than the left child
        else {
                char temp = this.heap[rightChild];
                this.heap[rightChild] = this.heap[idx];
                this.heap[idx] = temp;
                // Recalculate position and children
                idx = rightChild;
                leftChild = (2 * idx) + 1;
                rightChild = (2 * idx) + 2;
            }
        }
    }

    // Returns the top of the heap
    public char peek() {
        return this.heap[0];
    }

    // Returns the size of the heap
    public int size() {
        return this.size;
    }

    // Returns whether or not the array is empty
    public boolean isEmpty() {
        return (this.heap[0] == '~');
    }

    // Clears the array
    public void clear() {
        this.heap = new char[10];
        Arrays.fill(this.heap, '~');
        this.size = 0;
        this.capacity = 10;
    }

    // Converts the array into a string and returns it
    public String toString() {
        String stringForm = "";
        for (int i = 0; i < this.capacity; i++) {
            if (this.heap[i] != '~') {
                stringForm = stringForm + this.heap[i] + ' ';
            }
        }
        return stringForm;
    }
}
