import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.util.List;
public class Reader {

    public Reader() throws Exception  {
    }

    public List <TNode>  reader (String address){
        BufferedReader diskInput;
        String word;
        List <TNode> nodes = new ArrayList<TNode>();

        try { //reads in words from a file


          /*  PrintWriter writer = new PrintWriter("input.txt", "UTF-8");
            writer.println("The first line");
            writer.println("The second line");
            writer.close();*/

            diskInput = new BufferedReader(new InputStreamReader(
                    new FileInputStream(
                            new File(address))));// file name is on command line
            Scanner input=new Scanner(diskInput);
            while (input.hasNext()) {
                String name="";
                String id =input.next();
                if (input.hasNext()){
                    name =input.next();
                }
                name = name.toLowerCase(); // use lower case only
                int number  = Integer.parseInt(id);

                nodes.add(new TNode(new Data(number,name)));
            }
        }
        catch (Exception e) {
            System.out.println("Problem with file exception");
        }
     return nodes;
    }

    // read in the words to create the Binary Search Tree


}
