package mycollections;
import java.util.Iterator;

public class LinkedMap<K extends Comparable<K>, V> extends AbstractMap<K,V> {
    private SortedDoublyLinkedList<Entry<K,V>> list;
   
    public LinkedMap(){
        list = new SortedDoublyLinkedList<>();
    }
    public int size() {return list.size(); }
    public V get(K key) {
        Entry<K,V> e=new Entry<>(key,null);
        Entry<K,V> f=list.find(e);
        if(f==null) return null;
        else return f.getValue();
    }
    public V put(K key, V value) {
        Entry<K,V> e=new Entry<>(key,value);
        Entry<K,V> f=list.put(e);
        if(f==null) return null;
        else return f.getValue();
    }
    public V remove(K key) {
        Entry<K,V> e=new Entry<>(key,null);
        Entry<K,V> f=list.remove(e);
        if(f==null) return null;
        else return f.getValue();
    }
    private class KeyIterator implements Iterator<K> {
        private Iterator<Entry<K,V>> it = list.iterator( );
        public boolean hasNext( ) { return it.hasNext( ); }
        public K next( ) { return it.next( ).getKey( ); } 
    }
    private class KeyIterable implements Iterable<K> {
        public Iterator<K> iterator( ) { return new KeyIterator( ); }
    }
    public Iterable<K> keySet( ) { return new KeyIterable( ); }
}
