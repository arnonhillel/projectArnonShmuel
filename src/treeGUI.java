import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class treeGUI extends JFrame {

    private JPanel contentPane;
    public WiredTree tree;
    public DrawTree drawer;

    /**
     * Create the frame.
     */


    public static void main(String args[]) {
       //WiredTree tree = new WiredTree();
        Data data =new Data(5,"shmulic");
        Data data1 =new Data(3,"eli");
        Data data2 =new Data(4,"rami");
        //Data data3 =new Data(8,"yuval");
        //Data data4 =new Data(6,"yehoyachin");
        // Data data5 =new Data(8,"yuval");
        //  Data data6 =new Data(9,"yehoyachin");
        WiredTree tree = new WiredTree();


          tree.add(data);
         tree.add(data1);
         tree.add(data2);
        // tree.add(data3);

        //tree.add(data5);
        // tree.add(data6);

                treeGUI gui = new treeGUI(tree);



     //   SwingUtilities.invokeLater(r);

    }

    public treeGUI(WiredTree tree) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        drawer = new DrawTree(tree);

        contentPane.add(drawer);
        setContentPane(contentPane);
        this.tree = tree;
        setVisible(true);
    }

}

class DrawTree extends JPanel{

    public WiredTree tree;

    public DrawTree(WiredTree tree){
        this.tree = tree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub

        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        //g.drawString(String.valueOf(tree.root.data), this.getWidth()/2, 30);


        //DrawNode(g, tree.root,100, 50,2);

        DrawTree(g, 0, getWidth(), 0, getHeight() /4
                //tree.getheight(tree.root)
                , tree.get_root());
    }

    public void DrawNode(Graphics g,TNode n,int w,int h,int q){
        g.setFont(new Font("Tahoma", Font.BOLD, 20));

        if(n!=null){

            g.drawString(String.valueOf(n.getData()._studentId), (this.getWidth()/q)+w, h);
            if(n.getLeft() !=null)
                DrawNode(g, n.getLeft(), -w, h*2, q);
            //DrawNode(g, n.left, -w, h*2, q);
            //g.drawString(String.valueOf(n.left.data), (this.getWidth()/q)-w, h+50);
            if(n.getRight() !=null)
                DrawNode(g, n.getRight(), w*2, h*2, q);
            //g.drawString(String.valueOf(n.right.data), (this.getWidth()/q)+w, h+50);
        }




    }


    public void DrawTree(Graphics g, int StartWidth, int EndWidth, int StartHeight, int Level, TNode node) {
        String data = String.valueOf(node.getData()._studentId);
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();
        int dataWidth = fm.stringWidth(data);
      //  g.drawString(data, (StartWidth + EndWidth) / 2 - dataWidth / 2, StartHeight + Level / 2);
        g.drawString(data, (100) / 2 - 45 / 2,150 + 20 / 2);
        if (node.getLeft() != null)
            DrawTree(g, StartWidth, (StartWidth + EndWidth) / 2, StartHeight + Level, Level, node.getLeft());

        if (node.getRight() != null)
            DrawTree(g, (StartWidth + EndWidth) / 2, EndWidth, StartHeight + Level, Level, node.getRight());
    }


}