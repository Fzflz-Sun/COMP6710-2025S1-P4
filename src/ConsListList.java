import org.junit.platform.engine.support.hierarchical.EngineExecutionContext;
import org.junit.platform.engine.support.hierarchical.Node;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

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

    /**
     * Performs the given action for each element of the {@code Iterable}
     * until all elements have been processed or the action throws an
     * exception.  Actions are performed in the order of iteration, if that
     * order is specified.  Exceptions thrown by the action are relayed to the
     * caller.
     * <p>
     * The behavior of this method is unspecified if the action performs
     * side-effects that modify the underlying source of elements, unless an
     * overriding class has specified a concurrent modification policy.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     * @implSpec <p>The default implementation behaves as if:
     * <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     * @since 1.8
     */
    @Override
    public void forEach(Consumer<? super T> action) {
        List.super.forEach(action);
    }

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this list.  (In other words, this method must
     * allocate a new array even if this list is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * <p>This method acts as bridge between array-based and collection-based
     * APIs.
     *
     * @return an array containing all of the elements in this list in proper
     * sequence
     * @see Arrays#asList(Object[])
     */
    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    /**
     * Returns an array containing all of the elements in this list in
     * proper sequence (from first to last element); the runtime type of
     * the returned array is that of the specified array.  If the list fits
     * in the specified array, it is returned therein.  Otherwise, a new
     * array is allocated with the runtime type of the specified array and
     * the size of this list.
     *
     * <p>If the list fits in the specified array with room to spare (i.e.,
     * the array has more elements than the list), the element in the array
     * immediately following the end of the list is set to {@code null}.
     * (This is useful in determining the length of the list <i>only</i> if
     * the caller knows that the list does not contain any null elements.)
     *
     * <p>Like the {@link #toArray()} method, this method acts as bridge between
     * array-based and collection-based APIs.  Further, this method allows
     * precise control over the runtime type of the output array, and may,
     * under certain circumstances, be used to save allocation costs.
     *
     * <p>Suppose {@code x} is a list known to contain only strings.
     * The following code can be used to dump the list into a newly
     * allocated array of {@code String}:
     *
     * <pre>{@code
     *     String[] y = x.toArray(new String[0]);
     * }</pre>
     * <p>
     * Note that {@code toArray(new Object[0])} is identical in function to
     * {@code toArray()}.
     *
     * @param a the array into which the elements of this list are to
     *          be stored, if it is big enough; otherwise, a new array of the
     *          same runtime type is allocated for this purpose.
     * @return an array containing the elements of this list
     * @throws ArrayStoreException  if the runtime type of the specified array
     *                              is not a supertype of the runtime type of every element in
     *                              this list
     * @throws NullPointerException if the specified array is null
     */
    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    /**
     * Returns an array containing all of the elements in this collection,
     * using the provided {@code generator} function to allocate the returned array.
     *
     * <p>If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     *
     * @param generator a function which produces a new array of the desired
     *                  type and the provided length
     * @return an array containing all of the elements in this collection
     * @throws ArrayStoreException  if the runtime type of any element in this
     *                              collection is not assignable to the {@linkplain Class#getComponentType
     *                              runtime component type} of the generated array
     * @throws NullPointerException if the generator function is null
     * @apiNote This method acts as a bridge between array-based and collection-based APIs.
     * It allows creation of an array of a particular runtime type. Use
     * {@link #toArray()} to create an array whose runtime type is {@code Object[]},
     * or use {@link #toArray(Object[]) toArray(T[])} to reuse an existing array.
     *
     * <p>Suppose {@code x} is a collection known to contain only strings.
     * The following code can be used to dump the collection into a newly
     * allocated array of {@code String}:
     *
     * <pre>
     *     String[] y = x.toArray(String[]::new);</pre>
     * @implSpec The default implementation calls the generator function with zero
     * and then passes the resulting array to {@link #toArray(Object[]) toArray(T[])}.
     * @since 11
     */
    @Override
    public <T1> T1[] toArray(IntFunction<T1[]> generator) {
        return List.super.toArray(generator);
    }

    private class ConsListIterator implements Iterator<T> {

        /**
         * The current node in the iteration.
         * Initially set to the first node of the outer ConsListList.
         */
        private ConsListList.Node<T> current;

        /**
         * Constructs a new iterator that starts from the beginning of the list.
         * Strategy: capture a reference to the outer list's first node.
         */
        public ConsListIterator(){
            this.current=ConsListList.this.first;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next()} would
         * return an element rather than throwing an exception.)
         *
         * Example:
         * - Given: list = [1, 2], current = node at 1
         *   Expect: true
         * Strategy: simple expression
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return current !=null;
        }

        /**
         * Returns the next element in the iteration and advances the cursor.
         *
         * Example:
         * - Given: list = [1, 2, 3], current at 1
         *   expect: returns 1, current now points to 2
         * Strategy: case distinction
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
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator (optional operation).  The behavior of this
     * operation is undefined if the specified collection is modified while
     * the operation is in progress.  (Note that this will occur if the
     * specified collection is this list, and it's nonempty.)
     *
     * @param c collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnsupportedOperationException if the {@code addAll} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of the specified
     *                                       collection prevents it from being added to this list
     * @throws NullPointerException          if the specified collection contains one
     *                                       or more null elements and this list does not permit null
     *                                       elements, or if the specified collection is null
     * @throws IllegalArgumentException      if some property of an element of the
     *                                       specified collection prevents it from being added to this list
     * @see #add(Object)
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    /**
     * Inserts all of the elements in the specified collection into this
     * list at the specified position (optional operation).  Shifts the
     * element currently at that position (if any) and any subsequent
     * elements to the right (increases their indices).  The new elements
     * will appear in this list in the order that they are returned by the
     * specified collection's iterator.  The behavior of this operation is
     * undefined if the specified collection is modified while the
     * operation is in progress.  (Note that this will occur if the specified
     * collection is this list, and it's nonempty.)
     *
     * @param index index at which to insert the first element from the
     *              specified collection
     * @param c     collection containing elements to be added to this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnsupportedOperationException if the {@code addAll} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of the specified
     *                                       collection prevents it from being added to this list
     * @throws NullPointerException          if the specified collection contains one
     *                                       or more null elements and this list does not permit null
     *                                       elements, or if the specified collection is null
     * @throws IllegalArgumentException      if some property of an element of the
     *                                       specified collection prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index > size()})
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
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
     * Removes all of the elements of this collection that satisfy the given
     * predicate (optional operation).  Errors or runtime exceptions thrown during
     * iteration or by the predicate are relayed to the caller.
     *
     * @param filter a predicate which returns {@code true} for elements to be
     *               removed
     * @return {@code true} if any elements were removed
     * @throws NullPointerException          if the specified filter is null
     * @throws UnsupportedOperationException if the {@code removeIf} operation
     *                                       is not supported by this collection
     * @implSpec The default implementation traverses all elements of the collection using
     * its {@link #iterator}.  Each matching element is removed using
     * {@link Iterator#remove()}.  If the collection's iterator does not
     * support removal then an {@code UnsupportedOperationException} will be
     * thrown on the first matching element.
     * @since 1.8
     */
    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return List.super.removeIf(filter);
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
     * Replaces each element of this list with the result of applying the
     * operator to that element (optional operation).  Errors or runtime
     * exceptions thrown by the operator are relayed to the caller.
     *
     * @param operator the operator to apply to each element
     * @throws UnsupportedOperationException if the {@code replaceAll} operation
     *                                       is not supported by this list
     * @throws NullPointerException          if the specified operator is null or
     *                                       if the operator result is a null value and this list does
     *                                       not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @implSpec The default implementation is equivalent to, for this {@code list}:
     * <pre>{@code
     *     final ListIterator<E> li = list.listIterator();
     *     while (li.hasNext()) {
     *         li.set(operator.apply(li.next()));
     *     }
     * }</pre>
     * <p>
     * If the list's list-iterator does not support the {@code set} operation
     * then an {@code UnsupportedOperationException} will be thrown when
     * replacing the first element.
     * @since 1.8
     */
    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        List.super.replaceAll(operator);
    }

    /**
     * Sorts this list according to the order induced by the specified
     * {@link Comparator} (optional operation).  The sort is <i>stable</i>:
     * this method must not reorder equal elements.
     *
     * <p>All elements in this list must be <i>mutually comparable</i> using the
     * specified comparator (that is, {@code c.compare(e1, e2)} must not throw
     * a {@code ClassCastException} for any elements {@code e1} and {@code e2}
     * in the list).
     *
     * <p>If the specified comparator is {@code null} then all elements in this
     * list must implement the {@link Comparable} interface and the elements'
     * {@linkplain Comparable natural ordering} should be used.
     *
     * <p>This list must be modifiable, but need not be resizable.
     *
     * @param c the {@code Comparator} used to compare list elements.
     *          A {@code null} value indicates that the elements'
     *          {@linkplain Comparable natural ordering} should be used
     * @throws ClassCastException            if the list contains elements that are not
     *                                       <i>mutually comparable</i> using the specified comparator
     * @throws UnsupportedOperationException if the {@code sort} operation
     *                                       is not supported by this list
     * @throws IllegalArgumentException      (<a href="Collection.html#optional-restrictions">optional</a>)
     *                                       if the comparator is found to violate the {@link Comparator}
     *                                       contract
     * @implSpec The default implementation obtains an array containing all elements in
     * this list, sorts the array, and iterates over this list resetting each
     * element from the corresponding position in the array. (This avoids the
     * n<sup>2</sup> log(n) performance that would result from attempting
     * to sort a linked list in place.)
     * @implNote This implementation is a stable, adaptive, iterative mergesort that
     * requires far fewer than n lg(n) comparisons when the input array is
     * partially sorted, while offering the performance of a traditional
     * mergesort when the input array is randomly ordered.  If the input array
     * is nearly sorted, the implementation requires approximately n
     * comparisons.  Temporary storage requirements vary from a small constant
     * for nearly sorted input arrays to n/2 object references for randomly
     * ordered input arrays.
     *
     * <p>The implementation takes equal advantage of ascending and
     * descending order in its input array, and can take advantage of
     * ascending and descending order in different parts of the same
     * input array.  It is well-suited to merging two or more sorted arrays:
     * simply concatenate the arrays and sort the resulting array.
     *
     * <p>The implementation was adapted from Tim Peters's list sort for Python
     * (<a href="http://svn.python.org/projects/python/trunk/Objects/listsort.txt">
     * TimSort</a>).  It uses techniques from Peter McIlroy's "Optimistic
     * Sorting and Information Theoretic Complexity", in Proceedings of the
     * Fourth Annual ACM-SIAM Symposium on Discrete Algorithms, pp 467-474,
     * January 1993.
     * @since 1.8
     */
    @Override
    public void sort(Comparator<? super T> c) {
        List.super.sort(c);
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
            T rValue;
            for(;current!=null;current=current.next){
                if(i==index&&index==0){
                    rValue=first.data;
                    first=first.next;
                    return rValue;
                }else if(i==index-1){
                    if(current.next==null){
                        return null;
                    }else{
                        rValue = current.next.data;
                        current.next=current.next.next;
                        return rValue;
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
     * Returns a list iterator over the elements in this list (in proper
     * sequence).
     *
     * @return a list iterator over the elements in this list (in proper
     * sequence)
     */
    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    /**
     * Returns a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be
     * returned by an initial call to {@link ListIterator#next next}.
     * An initial call to {@link ListIterator#previous previous} would
     * return the element with the specified index minus one.
     *
     * @param index index of the first element to be returned from the
     *              list iterator (by a call to {@link ListIterator#next next})
     * @return a list iterator over the elements in this list (in proper
     * sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   ({@code index < 0 || index > size()})
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    /**
     * Returns a view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.  (If
     * {@code fromIndex} and {@code toIndex} are equal, the returned list is
     * empty.)  The returned list is backed by this list, so non-structural
     * changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported
     * by this list.<p>
     * <p>
     * This method eliminates the need for explicit range operations (of
     * the sort that commonly exist for arrays).  Any operation that expects
     * a list can be used as a range operation by passing a subList view
     * instead of a whole list.  For example, the following idiom
     * removes a range of elements from a list:
     * <pre>{@code
     *      list.subList(from, to).clear();
     * }</pre>
     * Similar idioms may be constructed for {@code indexOf} and
     * {@code lastIndexOf}, and all of the algorithms in the
     * {@code Collections} class can be applied to a subList.<p>
     * <p>
     * The semantics of the list returned by this method become undefined if
     * the backing list (i.e., this list) is <i>structurally modified</i> in
     * any way other than via the returned list.  (Structural modifications are
     * those that change the size of this list, or otherwise perturb it in such
     * a fashion that iterations in progress may yield incorrect results.)
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex   high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     *                                   ({@code fromIndex < 0 || toIndex > size ||
     *                                   fromIndex > toIndex})
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return List.of();
    }

    /**
     * Creates a {@link Spliterator} over the elements in this list.
     *
     * <p>The {@code Spliterator} reports {@link Spliterator#SIZED} and
     * {@link Spliterator#ORDERED}.  Implementations should document the
     * reporting of additional characteristic values.
     *
     * @return a {@code Spliterator} over the elements in this list
     * @implSpec The default implementation creates a
     * <em><a href="Spliterator.html#binding">late-binding</a></em>
     * spliterator as follows:
     * <ul>
     * <li>If the list is an instance of {@link RandomAccess} then the default
     *     implementation creates a spliterator that traverses elements by
     *     invoking the method {@link List#get}.  If such invocation results or
     *     would result in an {@code IndexOutOfBoundsException} then the
     *     spliterator will <em>fail-fast</em> and throw a
     *     {@code ConcurrentModificationException}.
     *     If the list is also an instance of {@link AbstractList} then the
     *     spliterator will use the list's {@link AbstractList#modCount modCount}
     *     field to provide additional <em>fail-fast</em> behavior.
     * <li>Otherwise, the default implementation creates a spliterator from the
     *     list's {@code Iterator}.  The spliterator inherits the
     *     <em>fail-fast</em> of the list's iterator.
     * </ul>
     * @implNote The created {@code Spliterator} additionally reports
     * {@link Spliterator#SUBSIZED}.
     * @since 1.8
     */
    @Override
    public Spliterator<T> spliterator() {
        return List.super.spliterator();
    }

    /**
     * Returns a sequential {@code Stream} with this collection as its source.
     *
     * <p>This method should be overridden when the {@link #spliterator()}
     * method cannot return a spliterator that is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
     * for details.)
     *
     * @return a sequential {@code Stream} over the elements in this collection
     * @implSpec The default implementation creates a sequential {@code Stream} from the
     * collection's {@code Spliterator}.
     * @since 1.8
     */
    @Override
    public Stream<T> stream() {
        return List.super.stream();
    }

    /**
     * Returns a possibly parallel {@code Stream} with this collection as its
     * source.  It is allowable for this method to return a sequential stream.
     *
     * <p>This method should be overridden when the {@link #spliterator()}
     * method cannot return a spliterator that is {@code IMMUTABLE},
     * {@code CONCURRENT}, or <em>late-binding</em>. (See {@link #spliterator()}
     * for details.)
     *
     * @return a possibly parallel {@code Stream} over the elements in this
     * collection
     * @implSpec The default implementation creates a parallel {@code Stream} from the
     * collection's {@code Spliterator}.
     * @since 1.8
     */
    @Override
    public Stream<T> parallelStream() {
        return List.super.parallelStream();
    }

    /**
     * {@inheritDoc}
     *
     * @param t
     * @throws NullPointerException          {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     * @implSpec The implementation in this interface calls {@code add(0, e)}.
     * @since 21
     */
    @Override
    public void addFirst(T t) {
        List.super.addFirst(t);
    }

    /**
     * {@inheritDoc}
     *
     * @param t
     * @throws NullPointerException          {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     * @implSpec The implementation in this interface calls {@code add(e)}.
     * @since 21
     */
    @Override
    public void addLast(T t) {
        List.super.addLast(t);
    }

    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException {@inheritDoc}
     * @implSpec If this List is not empty, the implementation in this interface returns the result
     * of calling {@code get(0)}. Otherwise, it throws {@code NoSuchElementException}.
     * @since 21
     */
    @Override
    public T getFirst() {
        return List.super.getFirst();
    }

    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException {@inheritDoc}
     * @implSpec If this List is not empty, the implementation in this interface returns the result
     * of calling {@code get(size() - 1)}. Otherwise, it throws {@code NoSuchElementException}.
     * @since 21
     */
    @Override
    public T getLast() {
        return List.super.getLast();
    }

    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException        {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     * @implSpec If this List is not empty, the implementation in this interface returns the result
     * of calling {@code remove(0)}. Otherwise, it throws {@code NoSuchElementException}.
     * @since 21
     */
    @Override
    public T removeFirst() {
        return List.super.removeFirst();
    }

    /**
     * {@inheritDoc}
     *
     * @throws NoSuchElementException        {@inheritDoc}
     * @throws UnsupportedOperationException {@inheritDoc}
     * @implSpec If this List is not empty, the implementation in this interface returns the result
     * of calling {@code remove(size() - 1)}. Otherwise, it throws {@code NoSuchElementException}.
     * @since 21
     */
    @Override
    public T removeLast() {
        return List.super.removeLast();
    }

    /**
     * {@inheritDoc}
     *
     * @return a reverse-ordered view of this collection, as a {@code List}
     * @implSpec The implementation in this interface returns a reverse-ordered List
     * view. The {@code reversed()} method of the view returns a reference
     * to this List. Other operations on the view are implemented via calls to
     * public methods on this List. The exact relationship between calls on the
     * view and calls on this List is unspecified. However, order-sensitive
     * operations generally behave as if they delegate to the appropriate method
     * with the opposite orientation. For example, calling {@code getFirst} on
     * the view might result in a call to {@code getLast} on this List.
     * @since 21
     */
    @Override
    public List<T> reversed() {
        return List.super.reversed();
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
