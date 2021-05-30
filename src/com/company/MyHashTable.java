package com.company;

public class MyHashTable <K, V> {

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    //   Node class  //
    private class HashNode <K, V>{
        private K key;
        private V value;
        private HashNode<K, V> next;

        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }


    public MyHashTable(){
        chainArray = new HashNode[M];
    }

    public MyHashTable(int M){
        chainArray = new HashNode[M];
    }


    //   hashCode method  //
    private int hash(K key){
        int hash = (key.hashCode() &0x7fffffff)%M;
        return hash;
    }


    //  The load factor  //
    private void rehash(){
        HashNode<K, V>[] temp = chainArray;
        int tempM = M;
        M = 2 * M;
        chainArray = new HashNode[M];
        HashNode<K, V> headNode;
        for (int i = 0; i < tempM; i++){
            headNode = temp[i];
            while (headNode!=null){
                put(headNode.key, headNode.value);
                headNode = headNode.next;
            }
        }
    }


    //  Put method  //
    public void put(K key, V value){
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        HashNode<K, V> newNode = new HashNode<>(key, value);

        if(head==null) {
            chainArray[index] = newNode;
            size++;
        }else{
            while (head != null){
                if(head.key.equals(key)){
                    head.value = value;
                    size++;
                    break;
                }
                head = head.next;
            }
        }

        if (((1.0 * size) / M )> 0.75) rehash();
    }


    //  Get method  //
    public V get(K key){
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        while (head!=null){
            if (head.key.equals(key)) return head.value;
            head = head.next;
        }
        return null;
    }


    //  Remove method  //
    public V remove(K key){
        int index = hash(key);
        HashNode<K, V> head = chainArray[index];
        if(head == null) return null;

        if(head.key.equals(key)){
            V removed = head.value;
            head = head.next;
            chainArray[index] = head;
            size--;
            return removed;
        }else {
            HashNode<K, V> prev = null;
            while (head!=null){
                if (head.key.equals(key)){
                    prev.next = head.next;
                    size--;
                    return head.value;
                }
                prev = head;
                head = head.next;
            }
        }
        return null;
    }


    //  Contains value method  //
    public boolean contains(V value){

        HashNode<K, V>[] table = chainArray;
        for (int i = 0; i < table.length; i++) {
            for (HashNode<K, V > e = table[i]; e!=null; e = e.next){
                if (e.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }


    //  Get key of the value method  //
    public K getKey(V value){
        HashNode<K, V>[] table = chainArray;
        for (int i = 0; i < table.length; i++) {
            for (HashNode<K, V > e = table[i]; e!=null; e = e.next){
                if (e.value.equals(value)) {
                    return e.key;
                }
            }
        }
        return null;
    }


    @Override
    public String toString() {
        String str = "";
        HashNode<K,V> current;

        for (int i=0;i<M;i++)
        {
            str+=i+"";
            current= chainArray[i];
            while(current != null)
            {
                str+=": "+current.toString();
                current = current.next;
            }
            str+="\n";
        }
        return str;
    }
}
