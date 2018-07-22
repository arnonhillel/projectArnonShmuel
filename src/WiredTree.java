

public class WiredTree {
    private TNode _root;
    private static Data _median;


    /**
     * empty constructor, define root to null.
     **/
    public WiredTree() {
        _root = null;
        _median = null;
    }

    /**
     *
     *
     **/
    public WiredTree(TNode node) {
        if (_root == null) {
            _root = node;
            _median = node.getData();
        }
    }

    public void add(Data data) {//page 220
        TNode node;
        TNode x = _root;
        TNode y = null;

        if (search(data) != null) {
            System.out.println("the student is exist!");
            return;

        }

        //Looking for the right location
        node = new TNode((data));
        while (x != null) {//Pseudo-code on page 220
            y = x;
            x.increaseNumOfNods();//update
            if (node.getData().compareTo(x.getData()) < 0) {
                if (lChild(x)) {
                    x = x.getLeft();
                } else {
                    break;
                }

            } else {
                if (rChild(x)) {
                    x = x.getRight();
                } else {
                    break;
                }
            }
        }

        //insert
        node.setParent(y);
        if (y == null) {//if the tree is empty
            _root = node;
        } else if (node.getData().compareTo(y.getData()) < 0) {
            y.setLeft(node);
        } else {
            y.setRight(node);
        }
        node.setLeft(treePredecessor(node));
        node.setRight(treeSuccessor(node));

      //  updateMedian();//update the pointer on the median
    }//end of add method/


    /**
     * search for data in the tree/
     *
     * @param data - The data to look for.
     * @return Pointer to node with the "data", null if not found.
     **/
    private TNode search(Data data) {
        TNode x = _root;
        while ((lChild(x) || rChild(x)) && !(data.equals(x.getData()))) {
            if (data.compareTo(x.getData()) < 0) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }
        if ((x != null) && (data.equals(x.getData()))) {
            return x;// FOUND
        }
        return null;


    }


    /* TODO */
    public void delete(TNode nodeToDel) {//page 221
        TNode x, y, z;
        TNode current;
        z = nodeToDel;

        if (!lChild(z) || !rChild(z)) {
            y = z;
        } else {
            y = treeSuccessor(z);
        }

        if (lChild(y)) {
            x = y.getLeft();
        } else if (rChild(y)) {
            x = y.getRight();
        } else {
            x = null;
        }

        if (y.getParent() == null) {
            _root = x;
        } else if (lChild(y)) {
            y.getParent().setLeft(x);
        } else {
            y.getParent().setRight(x);
        }

        if (y != z) {
            z.setData(y.getData());
        }

        /*TODO : update the numbers of nude and than the median*/

        //updateMedian();

    }

    /* TODO */
    private boolean rChild(TNode x) {
        return (x != null) && (x.getRight() != null) &&
                (x == x.getRight().getParent());
    }

    /* TODO */
    private boolean lChild(TNode x) {
        return (x != null) && (x.getLeft() != null) &&
                (x == x.getLeft().getParent());
    }

    /* TODO */
    private TNode treeSuccessor(TNode x) {
        //methude in page 218
        //assuming the nude given is from the tree
        if (rChild(x)) {
            return minimum(x.getRight());
        } else if (x.getRight() == null) {
            TNode y = x.getParent();
            while ((y != null) && (x == y.getRight())) {
                x = y;
                y = y.getParent();
            }
            return y;
        } else {
            return x.getRight();

        }
    }


    /* TODO */
    private TNode treePredecessor(TNode x) {
        //methude in page 219
        if (lChild(x)) {
            return maximum(x.getLeft());
        } else if (x.getLeft() == null) {
            TNode y = x.getParent();
            while (y != null && x == y.getLeft()) {
                x = y;
                y = y.getParent();
            }
            return y;
        } else {
            return x.getLeft();
        }
    }

    private TNode maximum(TNode root) {
        TNode max;
        if (root == null) {
            root = this._root;
        }

        max = root;
        while (rChild(max)) {
            max = max.getRight();
        }

        return max;

    }


    private TNode minimum(TNode root) {
        TNode min;

        if (root == null) {
            root = this._root;
        }

        min = root;
        while (lChild(min)) {
            min = min.getLeft();
        }

        return min;
    }

    public void print(){

            TNode current;

            if(_root==null) {
                return;
            }

            current=getLeftMostTreeNode(_root);
            while(current!=null) {
                System.out.printf("%s", current.getData().toString());
                if(!rChild(current)) {
                    current=treeSuccessor(current);
                }else {
                    current=getLeftMostTreeNode(current.getRight());
                }
            }
            System.out.println();


    }
    private TNode getLeftMostTreeNode(TNode subTreeRoot) {
        TNode leftMostTreeNode;

        leftMostTreeNode=subTreeRoot;

        while(lChild(leftMostTreeNode)) {
            leftMostTreeNode=leftMostTreeNode.getLeft();
        }

        return leftMostTreeNode;
    }


    /* TODO */
    //private void updateMedian () {
    //       return;
    //   }



}
