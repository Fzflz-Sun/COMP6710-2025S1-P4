import org.junit.platform.engine.support.hierarchical.EngineExecutionContext;
import org.junit.platform.engine.support.hierarchical.Node;

import java.util.Collection;
import java.util.Iterator;
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
     * Returns {@code true} if this list contains the specified element.
     * More formally, returns {@code true} if and only if this list contains
     * at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param o element whose presence in this list is to be tested
     * @return {@code true} if this list contains the specified element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
//    @Override
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
     * @param c collection to be checked for containment in this list
     * @return {@code true} if this list contains all of the elements of the
     * specified collection
     * @throws ClassCastException   if the types of one or more elements
     *                              in the specified collection are incompatible with this
     *                              list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection contains one
     *                              or more null elements and this list does not permit null
     *                              elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>),
     *                              or if the specified collection is null
     * @see #contains(Object)
     */
//    @Override
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
     * @param c collection containing elements to be removed from this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnsupportedOperationException if the {@code removeAll} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of this list
     *                                       is incompatible with the specified collection
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this list contains a null element and the
     *                                       specified collection does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
//    @Override
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
     * @param c collection containing elements to be retained in this list
     * @return {@code true} if this list changed as a result of the call
     * @throws UnsupportedOperationException if the {@code retainAll} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of an element of this list
     *                                       is incompatible with the specified collection
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this list contains a null element and the
     *                                       specified collection does not permit null elements
     *                                       (<a href="Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
//    @Override
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

    //determine whether there are continual targets
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

    //go through all elements from collection c when determine whether linked-list element is the target
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
     * @throws UnsupportedOperationException if the {@code clear} operation
     *                                       is not supported by this list
     */
//    @Override
    public void clear() {
        first = null;
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
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws UnsupportedOperationException if the {@code add} operation
     *                                       is not supported by this list
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws NullPointerException          if the specified element is null and
     *                                       this list does not permit null elements
     * @throws IllegalArgumentException      if some property of the specified
     *                                       element prevents it from being added to this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index > size()})
     */
//    @Override
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
                        break;//为什么不加break也能跑
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
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the {@code remove} operation
     *                                       is not supported by this list
     * @throws IndexOutOfBoundsException     if the index is out of range
     *                                       ({@code index < 0 || index >= size()})
     */
//    @Override
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
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
//    @Override
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
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
//    @Override
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
