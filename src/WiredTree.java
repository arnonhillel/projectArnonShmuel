

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
        if(y==null){//if the tree is empty
            _root=node;
        }
        else if (node.getData().compareTo(y.getData())<0){
            y.setLeft(node);
        }
        else {
            y.setRight(node);
        }
        node.setLeft(treePredecessor(node));
        node.setRight(treeSuccessor(node));

        updateMedian();//update the pointer on the median
    }//end of add method/


    /**
     *search for data in the tree/
     * @param data - The data to look for.
     * @return Pointer to node with the "data", null if not found.
     *
     **/
    private TNode search(Data data) {
        TNode x =_root;
        while ((lChild(x) || rChild(x))&& !(data.equals(x.getData()))) {
            if(data.compareTo(x.getData())<0) {
                x=x.getLeft();
            }else {
                x=x.getRight();
            }
        }
        if((x!=null)&&(data.equals(x.getData()))) {
            return x;// FOUND
        }
        return null;


    }



    /* TODO */
    public void delete(TNode nodeToDel){//page 221

    }

    /* TODO */
    private boolean rChild(TNode x) {
        //return if is a child or wired/
    }

    /* TODO */
    private boolean lChild(TNode x) {
        //return if is a child or wired/
    }

    /* TODO */
    private TNode treeSuccessor(TNode node) {
        //methude in page 218

    }

    /* TODO */
    private TNode treePredecessor(TNode node) {
        //methude in page 219
    }


    /* TODO */
    private void updateMedian() {
        //update the median
    }




}
