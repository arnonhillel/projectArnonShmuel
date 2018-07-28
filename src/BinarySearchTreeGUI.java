import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Component;
import java.io.File;
import java.util.HashMap;

/**
 *
 * @author
 */
public class BinarySearchTreeGUI extends JPanel {
int count =0;
    WiredTree myBST = new WiredTree();
    //BinaryTreeNode node;
    static Graphics g;
    // the node location of the tree
    private HashMap nodeLocations = null;
    // the sizes of the subtrees
    private HashMap subtreeSizes = null;
    String value = "sadf";
    int traversalType, in_Order, pre_Order, post_Order;
    private Dimension empty = new Dimension(0, 0);
    private FontMetrics fm = null;
    // do we need to calculate locations
    private boolean dirty = true;

    public BinarySearchTreeGUI() {//constructors
        setBorder(BorderFactory.createLineBorder(Color.black));
        nodeLocations = new HashMap();
        subtreeSizes = new HashMap();
    }

    public static void main(String args[]) {
        Runnable r;
        r = new Runnable() {

            public void run() {
                BinarySearchTreeGUI demo;
                demo = new BinarySearchTreeGUI();
                demo.startGUI();
                demo.repaint();
            }
        };

        SwingUtilities.invokeLater(r);

    }

    public void startGUI() {
        setInsert();
        JFrame frame;
        JPanel buttonPanel, statusPanel;
        JLabel label, status;
        JButton clear, add, delete, find;
        JMenuBar menubar;
        JMenu file, traversal, help;
        JMenuItem save, exit, inOrder, preOrder, postOrder, helpContents;
        JScrollPane scrollPane;
        final JTextField input;
        final TreePanel tree = new TreePanel();
        final WiredTree bst = new WiredTree();

        // Create the framed window
        frame = new JFrame();
        frame.setTitle("A Graphical User Interface for Binary Search Tree Visulisation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Give the frame a layout manager that will arrange
        // how the components will laid out
        frame.setLayout(new BorderLayout());

        // Create the menu bar and pass it to the framed window
        menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        // Create a menu and add it to the menubar
        file = new JMenu();
        file.setText("File");
        file.setMnemonic(KeyEvent.VK_F);

        menubar.add(file);

        // Create some items, give them a handler and add then to the menu
        save = new JMenuItem();
        save.setText("Save");
        save.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                saveFileChooser();
            }
        });
        file.add(save);

        exit = new JMenuItem();
        exit.setText("Exit");
        exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                exit();
            }
        });
        file.add(exit);

        // Create a menu and add it to the menubar
        traversal = new JMenu();
        traversal.setText("Traversal");
        traversal.setMnemonic(KeyEvent.VK_T);
        menubar.add(traversal);

        // Create some items, give them a handler and add then to the menu
        inOrder = new JMenuItem();
        inOrder.setText("In-Order");
        inOrder.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                in_Order = 1;
                traversalType = in_Order;
                System.out.println("traversal = " + traversalType);
            }
        });
        traversal.add(inOrder);

        postOrder = new JMenuItem();
        postOrder.setText("Post-Order");
        postOrder.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                post_Order = 2;
                traversalType = post_Order;
                System.out.println("traversal = " + traversalType);
            }
        });
        traversal.add(postOrder);

        preOrder = new JMenuItem();
        preOrder.setText("Pre-Order");
        preOrder.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pre_Order = 3;
                traversalType = pre_Order;
                System.out.println("traversal = " + traversalType);
            }
        });
        traversal.add(preOrder);

        // Create a menu and add it to the menubar
        help = new JMenu();
        help.setText("Help");
        help.setMnemonic(KeyEvent.VK_H);
        menubar.add(help);

        // Create some items, give them a handler and add then to the menu
        helpContents = new JMenuItem();
        helpContents.setText("Help Contents");
        helpContents.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                helpMenu();
            }
        });
        help.add(helpContents);

        scrollPane = new JScrollPane(tree, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        frame.add(scrollPane, BorderLayout.CENTER);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        frame.add(buttonPanel, BorderLayout.NORTH);

        input = new JTextField(5);
        buttonPanel.add(input);

        clear = new JButton();
        clear.setText("Clear");
        clear.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //bst.nodeValue(value);
            }
        });
        buttonPanel.add(clear);

        add = new JButton();
        add.setText("Add...");
        add.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String root;
                //value = "sadf";//input.getText();
                if (!"".equals(value) && value != null) {
                    tree.setNode(value);
                    setInsert();
                    tree.repaint();
                    //tree.nodeValue(myBST);
                }
                System.out.println(myBST.get_root());
                System.out.println("Value " + value);
            }
        });
        buttonPanel.add(add);

        find = new JButton();
        find.setText("Find...");
        find.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //bst.nodeValue(value);
            }
        });
        buttonPanel.add(find);

        delete = new JButton();
        delete.setText("Delete...");
        delete.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //bst.nodeValue(value);
            }
        });
        buttonPanel.add(delete);

        statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout());
        frame.add(statusPanel, BorderLayout.SOUTH);

        label = new JLabel();
        label.setText("Status");
        label.setHorizontalAlignment(JLabel.LEFT);
        statusPanel.add(label);

        status = new JLabel();
        status.setBackground(Color.white);
        statusPanel.add(status);


        // splat the window on the screen
        frame.setSize(500, 550);
        frame.setVisible(true);

    } // end startGUI

    //helpMenu
    public void helpMenu() {
        System.out.println("Help pressed");
        Object help = (" Help Information");
        JOptionPane.showMessageDialog(null, help);

    }

    //exit
    public void exit() {
        System.out.println("Quit pressed");
        System.exit(0);
    }

    //saveFileChooser
    public void saveFileChooser() {

        String selectedFileSave;
        String contents;

        contents = getText();


        JFileChooser fileSave = new JFileChooser(System.getProperty("user.dir", "user.home"));
        int return_fileOpen = fileSave.showSaveDialog(null);

        if (return_fileOpen == JFileChooser.APPROVE_OPTION) {
            selectedFileSave = fileSave.getSelectedFile().getName();
            System.out.println("Selected file for save is " + selectedFileSave);

            try {
                PrintWriter out = new PrintWriter(selectedFileSave);
                out.println(contents);
                out.println("The root node of the BST: " + myBST.get_root());
                //out.println("The height of the BST: " + myBST.getTreeHeight();
                //out.println("The number of nodes in the BST: " + myBST.count());
                out.println("The contents of the BST: " + myBST.toString());
                out.close();
            } catch (IOException e) {
                System.out.println("Uh oh");
            }
        }

        System.out.println("SaveMenuItem pressed");
    }

    public String getText() {

        return value;
    }

    public void setInsert() {
        count = count +1;
        myBST.add(new Data(count,"grer"));
    }
    TNode node;
    TNode nodes[];
    int levels[] = {5, 10, 30, 20, 10};
    int index = 0;
    int level = 0;
    String data;
    String root;

    // gets a nodes value
    public void nodeValue(TNode node) {

        node = myBST.get_root();
        if (node != null) {
            node.getLeft();
            index++;
            nodes[index] = node;
            node.getRight();

        }
        System.out.println("TreePanel " + node);
    }
}