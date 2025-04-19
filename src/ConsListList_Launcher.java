public class ConsListList_Launcher {
    public static void main(String[] args){
        ConsListList<Integer> l1 = new ConsListList();
        boolean a = l1.add(123);
        boolean b = l1.add(456);
        System.out.println(b);
        System.out.println(l1.size());
    }
}
