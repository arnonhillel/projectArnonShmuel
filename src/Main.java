
public class Main {
    public static void main(String[] args){

       // Data data =new Data(0,"shmulic");
        Data data1 =new Data(1,"eli");
        Data data2 =new Data(2,"rami");
        Data data3 =new Data(3,"yuval");
        Data data4 =new Data(4,"yehoyachin");
        Data data5 =new Data(5,"yuval");
        Data data6 =new Data(6,"yehoyachin");
        Data data7 =new Data(7,"yehoyachin");

        WiredTree tree = new WiredTree();


        tree.add(data3);
        tree.add(data4);
        tree.add(data2);
        tree.add(data7);
        tree.add(data6);
        tree.add(data1);
      //  tree.add(data1);



        tree.inOrderPrint();
        System.out.println(tree.search(5)!=null);//false

        tree.add(data5);
        tree.inOrderPrint();
        System.out.println(tree.search(5)!=null);//true



        tree.delete(5);
        tree.inOrderPrint();
        System.out.println(tree.search(5)!=null);//false







    }





   /*       try{
            Reader read = new Reader();
            List<TNode> nodes = read.reader("input.txt");
            for (TNode temp : nodes) {
                tree.add(temp.getData());
            }

        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }


     System.out.println("inOrder : ");
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


}
