import java.awt.*;
import javax.swing.*;
import java.awt.Event;
import java.util.HashMap;

/**
 *
 * @author
 */
public class TreePanel extends JPanel {

    WiredTree myBST = null;
    BinarySearchTreeGUI gui = new BinarySearchTreeGUI();
    TNode node;
    WiredTree value[];
    // the node location of the tree
    private HashMap nodeLocations = null;
    // the sizes of the subtrees
    private HashMap subtreeSizes = null;
    private Dimension empty = new Dimension(0, 0);
    private FontMetrics fm = null;
    // do we need to calculate locations
    private boolean dirty = true;
    int levels[] = {5, 10, 30, 20, 10};
    int index = 0;
    int level = 0;
    String data;
    String root;

    public TreePanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    //gets node value from the gui
    public void setNode(String mark) {
        this.data = mark;
    }

    // calculate node locations
    private void calculateLocations() {
        nodeLocations.clear();
        subtreeSizes.clear();
        TNode root = myBST.get_root();
        if (root != null) {
            calculateSubtreeSize(root);
            calculateLocation(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        }
    }

    // calculate the size of a subtree rooted at n
    private Dimension calculateSubtreeSize(TNode n) {
        if (n == null) {
            return new Dimension(0, 0);
        }
        String s = "" +n.getData()._studentId;
        Dimension ld = calculateSubtreeSize(n.getLeft());
        Dimension rd = calculateSubtreeSize(n.getRight());
        int h = fm.getHeight() + 20 + Math.max(ld.height, rd.height);
        int w = ld.width + 20 + rd.width;
        Dimension d = new Dimension(w, h);
        subtreeSizes.put(n, d);
        return d;
    }

    // calculate the location of the nodes in the subtree rooted at n
    private void calculateLocation(TNode n, int left, int right, int top) {
        if (n == null) {
            return;
        }
        Dimension ld = (Dimension) subtreeSizes.get(n.getLeft());
        if (ld == null) {
            ld = empty;
        }
        Dimension rd = (Dimension) subtreeSizes.get(n.getRight());
        if (rd == null) {
            rd = empty;
        }
        int center = 0;
        if (right != Integer.MAX_VALUE) {
            center = right - rd.width - 20 / 2;
        } else if (left != Integer.MAX_VALUE) {
            center = left + ld.width + 20 / 2;
        }
        int width = fm.stringWidth(n.getData().toString());
        Rectangle r = new Rectangle(center - width / 2 - 3, top, width + 6, fm.getHeight());
        nodeLocations.put(n, r);
        calculateLocation(n.getLeft(), Integer.MAX_VALUE, center - 20 / 2, top + fm.getHeight() + 20);
        calculateLocation(n.getRight(), center + 20 / 2, Integer.MAX_VALUE, top + fm.getHeight() + 20);
    }

    // draw the tree using the pre-calculated locations
    private void drawTree(Graphics2D g, TNode n, int px, int py, int yoffs) {
        if (n == null) {
            return;
        }
        Rectangle r = (Rectangle) nodeLocations.get(n);
        g.draw(r);
        g.drawString(n.getData().toString(), r.x + 3, r.y + yoffs);
        if (px != Integer.MAX_VALUE) {
            g.drawLine(px, py, r.x + r.width / 2, r.y);
        }
        drawTree(g, n.getLeft(), r.x + r.width / 2, r.y + r.height, yoffs);
        drawTree(g, n.getRight(), r.x + r.width / 2, r.y + r.height, yoffs);
    }

    public void paint(Graphics g) {
        super.paint(g);
        fm = g.getFontMetrics();
        dirty = false;
        // if node locations not calculated
        if (dirty) {
            calculateLocations();
            dirty = false;
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth() / 2, 20);
        drawTree(g2d, myBST.get_root(), Integer.MAX_VALUE, Integer.MAX_VALUE, fm.getLeading() + fm.getAscent());
        fm = null;
    }
}