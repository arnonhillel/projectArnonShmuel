
public class Main {
    public static void main(String[] args){

        Data data =new Data(7,"asfas");
        Data data1 =new Data(5,"dasd");
        Data data2 =new Data(10,"dassd");
        Data data3 =new Data(12,"dassssssd");
        Data data4 =new Data(25,"dasaaaad");

        WiredTree tree = new WiredTree();

        tree.add(data);
        tree.add(data1);
        tree.add(data2);
        tree.add(data3);
        tree.add(data4);
        System.out.print(tree.toString());





    }
}
