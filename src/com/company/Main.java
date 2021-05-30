package com.company;

public class Main {

    public static void main(String[] args) {

        System.out.println("HashTable:");
        MyHashTable example1 =new MyHashTable<>();
        example1.put(1,"one");
        example1.put(2,"two");
        example1.put(3,"three");
        example1.put(4,"four");
        example1.put(5,"five");
        example1.remove(4);
        System.out.println("Get method (get by key 5) " + example1.get(5));
        System.out.println("Contains method (does it contain four?): ");
        if(example1.contains("three")) System.out.println("Yes");
        else System.out.println("No");
        System.out.println("Get Key method (value: three):" + example1.getKey("three"));
        System.out.println("Print the hash table:");
        System.out.println(example1);


        System.out.println();
        System.out.println("Binary Search Tree:");
        BST example2 = new BST();
        example2.put(1,"one");
        example2.put(2,"two");
        example2.put(3,"three");
        example2.put(4,"four");
        example2.put(5,"five");
        System.out.println("Get method (get by key 4): " + example2.get(4));
        example2.delete(4);
        System.out.println("Inorder Traversal:");
        example2.inorder();

    }
}
