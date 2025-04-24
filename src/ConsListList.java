import org.junit.platform.engine.support.hierarchical.EngineExecutionContext;
import org.junit.platform.engine.support.hierarchical.Node;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

import static comp1110.lib.Functions.Equals;


public class ConsListList<T> implements List<T>{//implement has been commented for testing

    /**
     * The first node in the linked list (head pointer).
     */
    private Node<T> first;

    /**
     * Constructs an empty ConsListList.
     * Example:
     * - Given: new ConsListList<>()
     *   Expect: size() returns 0
     * Strategy: simple expression
     */
    public ConsListList() {
        first = null;
    }

    /**
     * Node class used to store list elements and the reference to the next node.
     */
    private static class Node<T> {
        /** Data stored in this node */
        T data;
        /** Reference to the next node */
        Node<T> next;

        /**
         * Constructs a new node with the given data.
         * @param data the value to store in the node
         */
        public Node(T data){
            this.data = data;
            next = null;
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new ConsListIterator();
    }

    private class ConsListIterator implements Iterator<T> {


        private ConsListList.Node<T> current;

        public ConsListIterator(){
            this.current=ConsListList.this.first;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current.next!=null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if(current==null){
                throw new NoSuchElementException();
            }else{
                T rvalue= current.data;
                current = current.next;
                return rvalue;
            }
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.
         * <p>
         * The behavior of an iterator is unspecified if the underlying collection
         * is modified while the iteration is in progress in any way other than by
         * calling this method, unless an overriding class has specified a
         * concurrent modification policy.
         * <p>
         * The behavior of an iterator is unspecified if this method is called
         * after a call to the {@link #forEachRemaining forEachRemaining} method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
        @Override
        public void remove() {
            Iterator.super.remove();
        }

        /**
         * Performs the given action for each remaining element until all elements
         * have been processed or the action throws an exception.  Actions are
         * performed in the order of iteration, if that order is specified.
         * Exceptions thrown by the action are relayed to the caller.
         * <p>
         * The behavior of an iterator is unspecified if the action modifies the
         * collection in any way (even by calling the {@link #remove remove} method
         * or other mutator methods of {@code Iterator} subtypes),
         * unless an overriding class has specified a concurrent modification policy.
         * <p>
         * Subsequent behavior of an iterator is unspecified if the action throws an
         * exception.
         *
         * @param action The action to be performed for each element
         * @throws NullPointerException if the specified action is null
         * @implSpec <p>The default implementation behaves as if:
         * <pre>{@code
         *     while (hasNext())
         *         action.accept(next());
         * }</pre>
         * @since 1.8
         */
        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    /**
     * Returns the number of elements in this list. If this list contains
     * more than {@code Integer.MAX_VALUE} elements, returns {@code Integer.MAX_VALUE}.
     * Example:
     * - Given: [1, 2, 3]
     *   Expect: 3
     * Strategy: iteration
     * @return the number of elements in the list
     */
    @Override
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
     * Example:
     * - Given: []
     *   Expect: true
     * Strategy: case distinction
     * @return true if list is empty
     */
    @Override
    public boolean isEmpty() {
        if(first == null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     * Example:
     * - Given: [1, 2, 3], contains(2)
     *   Expect: true
     * Strategy: iteration
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     */
    @Override
    public boolean contains(Object o) {
        int i = 0;
        Node <T> current=first;
        for(;current != null; current = current.next){
            if(current.data.equals(o)){
                return true;
            }
        }
        return false;
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
     * Example:
     * - Given: [1, 2], add(3)
     * Expect: [1, 2, 3]
     * Strategy: iteration
     * @param t element to be appended to this list
     * @return {@code true} (as specified by {@link Collection#add})
     */
    @Override
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
     * Example:
     * - Given: [1, 2, 3], remove(2)
     * Expect: [1, 3]
     * Strategy: iteration
     * @param o element to be removed from this list, if present
     * @return {@code true} if this list contained the specified element
     */
    @Override
    public boolean remove(Object o) {
        Node<T> current = first;
        for(;current != null; current = current.next){
//            System.out.println(current.next.data.equals(o));
//            System.out.println(current.next.data);
            if(current.next.data.equals(o)){
                if(current.next.next!=null){//determine whether the next next element is null
                    current.next = current.next.next;
                }else{
                    current.next = null;
                }
                return true;//times determines
            }else if(current.data.equals(o)){//determine whether the first element is the target
                first=current.next;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns {@code true} if this list contains all of the elements of the
     * specified collection.
     *
     * Example:
     * Given: list = [1, 2, 3], collection = [2, 3]
     * Expect: true
     * Given: list = [1, 2, 3], collection = [2, 4]
     * Expect: false
     *
     * Strategy: iteration
     * @param c collection to be checked for containment in this list
     * @return {@code true} if this list contains all of the elements of the
     * specified collection
     * @throws NullPointerException if the specified collection contains one
     *                              or more null elements and this list does not permit null
     *                              elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>),
     *                              or if the specified collection is null
     * @see #contains(Object)
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        if(c == null){
            throw new NullPointerException();
        }
//        boolean temp = false;//insert a temporary variable for recording whether the current element in collection is contained
        Iterator<?> i = c.iterator();
        while(i.hasNext()){
            Object o = i.next();
            if(this.contains(o)){
//                temp = true;
//            System.out.println(i.hasNext());
                if(!i.hasNext()){
                    return true;
                }
            }else{
                return false;
            }
        }

        return false;
    }

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection (optional operation).
     *
     * Example:
     * - Given: list = [1, 2, 3, 2], collection = [2]
     *   Expect: list = [1, 3]
     *
     * Strategy: iteration
     * @param c collection containing elements to be removed from this list
     * @return {@code true} if this list changed as a result of the call
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        Iterator<?> i =c.iterator();
        while(i.hasNext()){//go through collection c
            Object o = i.next();
            removeAllElements(o);
            if(!i.hasNext()){
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all consecutive occurrences of the specified element from the list.
     * Handles both the head and internal nodes if they match the target.
     *
     * Example:
     * - Given: list = [1, 2, 2, 3], removeAllElements(2)
     *   Expect: list = [1, 3]
     *
     * Strategy: case distinction
     * @param o the element to be removed from the list
     * @return true if at least one element was removed
     */
    public boolean removeAllElements(Object o){
        Node<T> current = first;
        for(; current != null; current = current.next){//go through linked-list
//            System.out.println("current data");
//            System.out.println(current.data);
//            System.out.println(current.next.data);
//            System.out.println(first.data.equals(o));
            if(first.data.equals(o)){//determine whether the first element is the target and whether it has continuity
                //new method
                first=removeContinueSame(current,o);
//                System.out.println("rectify");
//                System.out.println(current.data);
            }else if(current.next==null){
                return true;
            }else if(current.next.data.equals(o)){//determine whether the current.next element is the target and whether it has continuity
                current.next=removeContinueSame(current.next,o);
            }

        }
        return true;
    }

    /**
     * Helper method that skips over consecutive nodes that match the target element.
     * Used to remove continuous occurrences of an element in the list.
     * Example:
     * - Given: list segment = [2, 2, 2, 3], o = 2
     *   Expect: return node containing 3
     * Strategy: iteration
     * @param current the node to start from
     * @param o the value to skip over
     * @return the first node that does not match o, or null if all match
     */
    Node<T> removeContinueSame(Node<T> current, Object o){
        for(; current.data.equals(o); current=current.next){
            if(current.next!=null){
                if(!current.next.data.equals(o)){//without continuity
//                    System.out.println("rcs next data");
//                    System.out.println(current.next.data);
                    return current.next;
                }
            }else{
                return null;
            }
        }
        return current;

    }

    /**
     * Retains only the elements in this list that are contained in the
     * specified collection (optional operation).  In other words, removes
     * from this list all of its elements that are not contained in the
     * specified collection.
     *
     * Example:
     * - Given: list = [1, 2, 3], collection = [2, 4]
     *   Expect: list = [2]
     * Strategy: iteration
     * @param c collection containing elements to be retained in this list
     * @return {@code true} if this list changed as a result of the call
     */
    @Override
    public boolean retainAll(Collection<?> c){
        Node<T> current = first;
        for(;current!= null; current = current.next){
            if(!allCollections(first, c)){
                first=retainContinueElement(first,c);
            }else if(current.next==null){
                return true;
            }else if(!allCollections(current.next, c)){
                current.next=retainContinueElement(current.next,c);
            }
        }
        return false;
    }

    /**
     * Skips over all consecutive nodes that are not present in the specified collection,
     * and returns the first node that should be retained.
     * Used in the retainAll logic to preserve only valid nodes.
     *
     * Example:
     * - Given: list = [1, 4, 5, 2, 3], c = [2, 3]
     *   Call: retainContinueElement(node with 1, [2, 3])
     *   Expect: node with 2
     * - Given: list = [1, 1, 1, 4, 3], c = [2, 3]
     *   Call: retainContinueElement(node with 1, [2, 3])
     *   Expect: node with 4
     * Strategy: iteration
     * @param current the starting node to examine
     * @param c the collection containing allowed elements
     * @return the first node that is present in the collection, or null if none found
     */
    public Node<T> retainContinueElement(Node<T> current, Collection<?> c){
        if(current.next!=null){
            if(!allCollections(current.next, c)){//the next target is not the element
                for(;!allCollections(current.next, c);current=current.next){//determine which is the target after the next
                    if(current.next.next!=null){
                        if(allCollections(current.next.next, c)){//the next next element is the target
                            return current.next.next;
                        }
                    }else{
                        return null;
                    }
                }
            }else if(allCollections(current.next, c)){//the next element is the target
                return current.next;
            }
        }else{
            return null;
        }

        return null;
    }

    /**
     * Checks whether the data of the given node exists in the specified collection.
     * Example:
     * - Given: node with value 5, c = [1, 2, 5]
     *   Expect: true
     * - Given: node with value 9, c = [1, 2, 3]
     *   Expect: false
     * Strategy: iteration
     * @param current the node whose data is to be tested
     * @param c the collection against which data is compared
     * @return true if the node's data is present in the collection
     */
    public boolean allCollections(Node<T> current, Collection<?> c){
        Iterator<?> i = c.iterator();
        while(i.hasNext()){
            Object o= i.next();
            if(current.data.equals(o)){
                return true;
            }
        }
        return false;
    }

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     *
     * Example:
     * - Given: list = [1, 2, 3]
     *   Expect: list = []
     *
     * Strategy: simple expression
     */
    @Override
    public void clear() {
        first = null;
    }

    /**
     * Returns the element at the specified index in this list.
     * Example:
     * - Given: list = [10, 20, 30], get(1)
     *   Expect: 20
     * Strategy: iteration
     * @param index index of the element to return
     * @return the element at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index >= size()})
     */
    @Override
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
     * Example:
     * - Given: list = [1, 2, 3], set(1, 99)
     *   Expect: list = [1, 99, 3], return = 2
     * Strategy: iteration
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
    @Override
    public T set(int index, T t) {
        if(index<0||index>=this.size()){
            throw new IndexOutOfBoundsException(index);
        }
        T rValue = this.get(index);
        Node<T> current=first;
        int i=0;
        for(; current != null; current=current.next){
            if(i==index){
                current.data=t;
            }
            i++;
        }
        return rValue;
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     * Example:
     * - Given: list = [1, 2, 3], add(1, 99)
     *   Expect: list = [1, 99, 2, 3]
     * Strategy: iteration
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index > size()})
     */
    @Override
    public void add(int index, T element) {
        if(index<0||index>this.size()){
            throw new IndexOutOfBoundsException(index);
        }else{
            Node<T> current = first;
            int i =0;
            Node<T> temp = new Node<>(element);
            if(first==null){
                first=temp;
            }else{
                for(; current!=null; current=current.next){
                    if(i == index&& index ==0){
                        temp.next=first;
                        first=temp;
                    }
//                else if(i ==index&& index ==1){
//                    first.next=temp;
//                    temp.next=current;
//                }
                    else if(i ==index-1){//-1 for insert in current element, rather than current.next
                        Node<T> after = current.next;
                        current.next = temp;
                        temp.next=after;
                    }
                    i++;
                }
            }
        }
    }

    /**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     * Example:
     * - Given: list = [1, 2, 3], remove(1)
     *   Expect: list = [1, 3], return = 2
     * Strategy: iteration
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
    @Override
    public T remove(int index) {
        if(index<0||index>=this.size()){
            throw new IndexOutOfBoundsException(index);
        }else{
            Node<T> current=first;
            int i =0;
            for(;current!=null;current=current.next){
                if(i==index&&index==0){
                    first=first.next;
                }else if(i==index-1){
                    if(current.next==null){
                        return null;//会出现这种情况吗
                    }else{
                        current.next=current.next.next;
                    }

                }
                i++;
            }
        }
        return null;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     * Example:
     * - Given: list = [1, 2, 3, 2], indexOf(2)
     *   Expect: 1
     * Strategy: iteration
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    @Override
    public int indexOf(Object o) {
        Node<T> current = first;
        int i =0;
        for(;current!=null;current=current.next){
            if(current.data.equals(o)){
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     * Example:
     * - Given: list = [1, 2, 3, 2], lastIndexOf(2)
     *   Expect: 3
     * Strategy: iteration
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     */
    @Override
    public int lastIndexOf(Object o) {
        Node<T> current = first;
        int i =0;
        for(;current!=null;current=current.next){
            if(current.data.equals(o)){
                if(isLast(current,o)){
                    return i;
                }
            }
            i++;
        }
        return -1;
    }

    /**
     * Determines whether the given node is the last occurrence of a target value in the list.
     *
     * Example:
     * - Given: list = [1, 2, 3, 2], o = 2
     *   Expect: isLast(node at index 3, 2) true
     *   Expect: isLast(node at index 1, 2) false
     * - Given: list = [5, 6], o = 6
     *   Expect: isLast(node at index 1, 6) true
     *
     * Strategy: iteration
     * @param current the node to check from
     * @param o the target value to test against
     * @return true if current is the last node with value o, false otherwise
     */
    public boolean isLast(Node<T> current, Object o){
        if(current.next==null){
            return true;
        }else{
            for(; current!=null; current=current.next){
                if(current.next==null) {
                    return true;
                }else{
                    if(current.next.data.equals(o)){
                        return false;
                    }
                }

            }
        }
        return true;
    }
}
