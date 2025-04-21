import java.util.ArrayList;

public class ConsListList_Launcher {
    public static void main(String[] args){
        ConsListList<Integer> l1 = new ConsListList<>();
        ConsListList<Integer> l2 = new ConsListList<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(123);
        list1.add(456);
        boolean a = l1.add(123);
        boolean b = l1.add(456);
        boolean a1 = l1.add(123);
        boolean b1 = l1.add(456);
        System.out.println(l1.set(0,789));
        System.out.println(l1.containsAll(list1));
//        System.out.println(l1.contains(456));
//        System.out.println(l1.get(3));
//        System.out.println(l1.size());
//        System.out.println(l1.get(1));
//        System.out.println(l1.size());
//        boolean c = l1.remove(456);
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println(l1.size());
//        System.out.println(l1.size());
//        System.out.println(l1.isEmpty());
//        System.out.println(l2.isEmpty());

    }
}
