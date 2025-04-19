import org.junit.platform.engine.support.hierarchical.EngineExecutionContext;
import org.junit.platform.engine.support.hierarchical.Node;

import java.util.List;

import static comp1110.lib.Functions.Equals;

//implements List<T>
public class ConsListList<T>{//implement has been commented for testing

    /**
     * generate a new node first
     */
    private Node<T> first;

    public ConsListList() {
        first = null;
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        public Node(T data){
            this.data = data;
            next = null;
        }
    }
    /**
     * Returns the number of elements in this list.  If this list contains
     * more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this list
     */
//    @Override
    public int size() {
        Node<T> current = first;
        int count;
        for(count=0; current!=null; count++){
            current = current.next;
        }
        return count;
    }

    /**
     * Returns {@code true} if this list contains no elements.
     *
     * @return {@code true} if this list contains no elements
     */
//    @Override
    public boolean isEmpty() {
        if(first == null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @param t element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws UnsupportedOperationException if the {@code add} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     * @throws IllegalArgumentException      if some property of this element
     *                                       prevents it from being added to this list
     */
//    @Override
    public boolean add(T t) {
        Node<T> current = first;
        if(current == null){//determine whether the first element is null
            first = new Node<>(t);
            return true;
        }else{
            for(; current!=null; current=current.next){//go through all the elements
                if(current.next==null){//determine whether it is the last element
                    current.next = new Node<>(t);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).  If this list does not contain
     * the element, it is unchanged.  More formally, removes the element with
     * the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))}
     * (if such an element exists).  Returns {@code true} if this list
     * contained the specified element (or equivalently, if this list changed
     * as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     * @throws ClassCastException            if the type of the specified element
     *                                       is incompatible with this list
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified element is null and this
     *                                       list does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the {@code remove} operation
     *                                       is not supported by this list
     */
//    @Override
    public boolean remove(Object o) {
        Node<T> current = first;
        for(;current != null; current = current.next){
            if(current.next.data.equals(o)){
                current.next = current.next.next;
                return true;//times determines
            }else if(current.data.equals(o)){//determine whether the first element is the target
                first=current.next;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
//    @Override
    public T get(int index) {
        if(index<0||index>=this.size()){
            throw new IndexOutOfBoundsException(index);
        }
        Node<T> current = first;
        int i=0;
        for(; current!=null; current=current.next){
            if(i==index){
                return current.data;
            }
            i++;
        }
        return null;
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the {@code set} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
//    @Override
    public T set(int index, T element) {
        if(index<0||index>=this.size()){
            throw new IndexOutOfBoundsException(index);
        }
        T rValue = this.get(index);
        Node<T> current=first;
        int i=0;
        for(; current != null; current=current.next){
            if(i==index){
                current.data=element;
            }
            i++;
        }
        return rValue;
    }
}
