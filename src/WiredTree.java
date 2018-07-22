public class WiredTree {
    private TNode _root;
    private TNode _median;
    private boolean medianOdd = false;


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
            _median = node;
            medianOdd = true;
        }
    }

    public TNode get_root() {
        return _root;
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
        updateMedianAdd(node);
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

        updateMedianDelete(nodeToDel);

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
    public  TNode maximum(){
        return maximum(_root);
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


    public  TNode minimum(){
        return minimum(_root);
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

    public void inOrderPrint(){

        TNode current;

        if(_root==null) {
            return;
        }

        current=getLeftMostTreeNode(_root);
        while(current!=null) {
            System.out.println(current.getData().toString());
            if(!rChild(current)) {
                current=treeSuccessor(current);
            }else {
                current=getLeftMostTreeNode(current.getRight());
            }
        }
        System.out.println();


    }
    public  void postOrderPrint(){
        postOrderPrint(this._root);
        System.out.println();
    }
    private void postOrderPrint(TNode x){
        if(x!=null){
            if(lChild(x)){
                postOrderPrint(x.getLeft());
            }

            if (rChild(x)){
                postOrderPrint(x.getRight());
            }

            System.out.println(x.getData().toString());
        }
    }

    private TNode getLeftMostTreeNode(TNode subTreeRoot) {
        TNode leftMostTreeNode;

        leftMostTreeNode=subTreeRoot;

        while(lChild(leftMostTreeNode)) {
            leftMostTreeNode=leftMostTreeNode.getLeft();
        }

        return leftMostTreeNode;
    }


    private void updateMedianDelete (TNode current) {

            int state = this._median.getData().compareTo(current.getData());
            if (state < 0 && medianOdd == false);
            if (state >= 0 && medianOdd == false)this._median = minimum(this._median.getRight());
            if (state <= 0 && medianOdd == true)this._median = maximum(this._median.getLeft());
            if (state > 0 && medianOdd == true) ;
            this.medianOdd = !this.medianOdd;
    }

    private void updateMedianAdd (TNode current) {
        if(this.get_median()== null){
            this._median = current;
            this.medianOdd = true;
        }
        else{
            int state = this._median.getData().compareTo(current.getData());
            if (state <= 0 && medianOdd == false) this._median = treeSuccessor(this._median);
            if (state > 0 && medianOdd == false);
            if (state < 0 && medianOdd == true);
            if (state >= 0 && medianOdd == true)this._median = treePredecessor(this._median);
            this.medianOdd = !this.medianOdd;
        }
    }

    public  TNode get_median(){
        return this._median;
    }

    /* TODO */
    //private void updateMedian () {
    //       return;
    //   }



}