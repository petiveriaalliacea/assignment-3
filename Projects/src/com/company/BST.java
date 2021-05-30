package com.company;


public class BST <K extends Comparable<K>, V>{
    private Node root;


    //   Node class  //
    private class Node {
        private K key;
        private V value;
        private Node left, right;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    //  Put method  //
    public void put(K key,V value){
        root = put( new Node(key, value),root);
    }

    private Node put(Node update,Node node) {
        if (node == null) node = update;

        else {
            if (update.key.equals(node.key))
                node.value = update.value;
            else if (update.key.compareTo(node.key) <= 0)
                node.left = put(update, node.left);
            else
                node.right = put(update, node.right);
        }
        return node;
    }


    //  Get method  //
    public V get(K key){
        if(key == null) throw new IllegalArgumentException("Null key");
        else return get(key, root);
    }

    private V get(K key, Node node){
        if(node==null) return null;
        else{
            K temp = node.key;
            if(key.equals(temp))
                return node.value;
            else if(key.compareTo(temp) <= 0)
                return get(key, node.left);
            else
                return get(key, node.right);
        }
    }


    //   Delete method   //
    public void delete(K key) {
        root = delete(key, root);
    }
    private Node delete(K value, Node node) {
        if (node == null) return null;

        if (value.compareTo(node.key) == 0) {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                node.key = getRightMost(node.left);
                node.left = delete(node.key, node.left);
            }
        } else {
            if (value.compareTo(node.key) < 0) {
                node.left = delete(value, node.left);
            } else {
                node.right = delete(value, node.right);
            }
        }
        return node;
    }

    private K getRightMost(Node node) {
        assert(node != null);
        Node right = node.right;
        if (right == null) {
            return node.key;
        } else {
            return getRightMost(right);
        }
    }


    //  Inorder traversal  //
    public void inorder()
    {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node root)
    {
        if (root == null) return;

        inorder(root.left);
        System.out.print(root.key + ":" + root.value + "  ");
        inorder(root.right);
    }


}
