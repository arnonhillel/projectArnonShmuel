import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WiredTree tree = new WiredTree();

        boolean run = true;

        while (run) {
            try {
                System.out.println("for insert press 1, delete 2 ,  preorder 3 , inorder 4  , postorder 5 , get median 6 ");
                System.out.println(" get predecessor 7, get successor 8 , get maximum 9 , get minimum 10, exit 11");

                switch (Integer.parseInt(scanner.nextLine())) {
                    case 1: {
                        System.out.println("Please insert the  student id");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Please insert the  student name");
                        String name = scanner.nextLine();
                        Data data = new Data(id, name);
                        tree.add(data);
                        break;
                    }
                    case 2: {
                        System.out.println("Please insert the student id");
                        int id = Integer.parseInt(scanner.nextLine());
                        tree.delete(id);
                        break;
                    }
                    case 3: {
                        tree.preorderPrint();
                        break;
                    }
                    case 4: {
                        tree.inOrderPrint();
                        break;
                    }
                    case 5: {
                        tree.postOrderPrint();
                        break;
                    }
                    case 6: {
                        tree.get_median().getData();
                        break;
                    }

                    case 7: {
                        System.out.println("Please insert the student id");
                        int id = Integer.parseInt(scanner.nextLine());
                        tree.treePredecessor(id);
                        break;
                    }
                    case 8: {
                        System.out.println("Please insert the student id");
                        int id = Integer.parseInt(scanner.nextLine());
                        tree.treeSuccessor(id);
                        break;
                    }
                    case 9: {
                        System.out.println(" the number of the maximum is  " + tree.maximum().getData().toString());

                        break;
                    }
                    case 10: {
                        System.out.println(" the number of the minimum is  " + tree.minimum().getData().toString());
                        break;
                    }
                    case 11:

                    {
                        System.out.println("thank you!");
                        run = false;
                        break;
                    }
                    default: {
                        System.out.println("not a statment!");
                        break;
                    }
                }
            }
            catch (Exception ex){
                System.out.println("wrong input");
            }
        }
       /* try {
            Reader read = new Reader();
            List<TNode> nodes = read.reader("input.txt");
            for (TNode temp : nodes) {
                tree.add(temp.getData());
            }


            try {
                Reader read = new Reader();
                List<TNode> nodes = read.reader("input.txt");
                for (TNode temp : nodes) {
                    tree.add(temp.getData());
                }

            } catch (Exception ex) {
                System.out.println(ex.toString());
            }

            System.out.println("inOrder : ");
            tree.inOrderPrint();

            System.out.println("treePredecessor : " + tree.treePredecessor(1));
            System.out.println("treeSuccessor : " + tree.treeSuccessor(1));

        }*/
    }
}