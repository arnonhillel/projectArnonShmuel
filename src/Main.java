
public class Main {
    public static void main(String[] args){

        Data data =new Data(5,"shmulic");
        Data data1 =new Data(4,"eli");
        Data data2 =new Data(2,"rami");
        Data data3 =new Data(1,"yuval");
        Data data4 =new Data(7,"yehoyachin");

        WiredTree tree = new WiredTree();


        tree.add(data);
        tree.add(data1);
        tree.add(data2);
        tree.add(data3);
        tree.add(data4);



        System.out.println("inOrder : ");
        tree.inOrderPrint();
        System.out.println("postOrder : ");
        tree.postOrderPrint();
        System.out.println("the minimum is : "+tree.minimum().getData());
        System.out.println("the minimum is : "+tree.maximum().getData());




    }
}
