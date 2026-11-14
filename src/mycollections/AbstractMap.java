package mycollections;

public abstract class AbstractMap<K,V> implements Map<K,V> { 
    public boolean isEmpty( ) { return size() == 0; }
    
    protected static class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K, V>> {
        private K k;
        private V v;
       
        public Entry(K key, V value) {
            k = key;
            v = value;
        }
        public K getKey( ) { return k; }
        public V getValue( ) { return v; }
        public void setKey(K key) { k = key; }
        public V setValue(V value){
            V old = v;
            v = value;
            return old;
        }
        public int compareTo(Entry<K, V> outra){return k.compareTo(outra.k); }
    }
}