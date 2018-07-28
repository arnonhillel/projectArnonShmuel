import java.util.List;

public class Main {
    public static void main(String[] args){

        //Data data =new Data(5,"shmulic");
        //Data data1 =new Data(3,"eli");
        //Data data2 =new Data(4,"rami");
        //Data data3 =new Data(8,"yuval");
        //Data data4 =new Data(6,"yehoyachin");
       // Data data5 =new Data(8,"yuval");
      //  Data data6 =new Data(9,"yehoyachin");
        WiredTree tree = new WiredTree();


      //  tree.add(data);
       // tree.add(data1);
       // tree.add(data2);
       // tree.add(data3);

        //tree.add(data5);
       // tree.add(data6);







       try{
            Reader read = new Reader();
            List<TNode> nodes = read.reader("input.txt");
            for (TNode temp : nodes) {
                tree.add(temp.getData());
            }

        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }


     /*   System.out.println("inOrder : ");
        tree.inOrderPrint();
        System.out.println("postOrder : ");
        tree.postOrderPrint();
        System.out.println("preOrder : ");
        tree.preorderTreeWalk();
        System.out.println("the minimum is : "+tree.minimum().getData());
        System.out.println("the maximum is : "+tree.maximum().getData());
        System.out.println("the median is : "+tree.get_median().getData());
*/





      //
       //System.out.println("the median is : "+tree.get_median().getData());

      // System.out.println("inOrder : ");

        tree.inOrderPrint();
     //   tree.search(data4);
    //    tree.delete(11);
        tree.delete(4);
       // tree.delete(6);
       // tree.delete(8);
       // tree.delete(11);
        System.out.println("inOrder : ");
        tree.inOrderPrint();
    }
}
