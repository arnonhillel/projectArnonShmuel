
public class Main {
    public static void main(String[] args){

        Data data =new Data(32351251,"shmulic");
        Data data1 =new Data(52153252,"eli");
        Data data2 =new Data(12352513,"rami");
        Data data3 =new Data(12354523,"yuval");
        Data data4 =new Data(735436363,"yehoyachin");

        WiredTree tree = new WiredTree();

        tree.add(data);
        tree.add(data1);
        tree.add(data2);
        tree.add(data3);
        tree.add(data4);
        tree.print();


    }
}
