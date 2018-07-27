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

        if (search(data)!= null) {
            System.err.println("the student is exist!");
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
     * @param id - The key to look for.
     * @return Pointer to node with the data , null if not found.
     **/
    public TNode search(int id) {
       // String name=" ";
       // Data data=new Data(id,name);

        return search(new Data(id,""));
    }
    /**
     * search for data in the tree/
     *
     * @param data - The data to look for.
     * @return Pointer to node with the "data", null - not found.
     **/
    public TNode search(Data data) {
        TNode x = _root;
        while ((lChild(x) || rChild(x)) && !(data.compareTo(x.getData())==0)) {
            if (data.compareTo(x.getData()) < 0) {
                if(lChild(x)) {
                    x = x.getLeft();
                }
                else{
                    break;
                }
            } else {
                if(rChild(x)) {
                    x = x.getRight();
                }
                else{
                    break;
                }
            }
        }
        if ((x != null) && (data.compareTo(x.getData())==0)) {
            return x;// FOUND
        }
        else {
            return null;
        }


    }

    public void delete(int id){

        TNode z = search(id);
        if(z==null) {
            System.out.println("The student does not exist in the tree");
        }
        else{
           delete1(z);
     }
    }


    private void delete(TNode z) {//page 221
        TNode x, y;


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

        System.out.println("deleted! ");
        updateMedianDelete(z);
    }

    private void delete1(TNode nodeToDel) {//page 221
        TNode x, y, z;
        z = nodeToDel;

        if (!lChild(z) && !rChild(z)) {
            if (z.getParent()!=null) {
                if (z.getParent().getLeft() == z) z.getParent().setLeft(null);
                if (z.getParent().getRight() == z) z.getParent().setRight(null);

                if (!rChild(z.getLeft())&&z.getLeft()!=null ) {
                    z.getLeft().setRight(z.getRight());
                }

                if (!lChild(z.getRight())&z.getRight()!=null) {
                    z.getRight().setLeft(z.getLeft());
                }
            }
            z = null;
        }

        else {
            if (!lChild(z) && rChild(z)){
               this.replace(z ,z.getRight());
               // z.setData(new Data(z.getRight().getData()._studentId,z.getRight().getData()._studentName));
               // delete(z.getRight());
            }
            else{
                if(lChild(z) && !rChild(z)){
                    this.replace(z ,z.getLeft());
                 //   z.setData(new Data(z.getLeft().getData()._studentId,z.getLeft().getData()._studentName));
                  //  delete(z.getLeft());
                }
                else{
                    z.setData(new Data(treeSuccessor(z).getData()));
                    delete(treeSuccessor(z));
                }

            }
        }

    }

    private boolean rChild(TNode x) {
        return (x != null) && (x.getRight() != null) &&
                (x == x.getRight().getParent());
    }


    private boolean lChild(TNode x) {
        return (x != null) && (x.getLeft() != null) &&
                (x == x.getLeft().getParent());
    }


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
    public void preorderTreeWalk() {
        preorderTreeWalk(this._root);
        System.out.println();
    }

    private void preorderTreeWalk(TNode x) {
        if(x!=null) {
            System.out.println(x.getData().toString());
            if(lChild(x)) {
                preorderTreeWalk(x.getLeft());
            }
            if(rChild(x)){
                preorderTreeWalk(x.getRight());
            }
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
            if (state >= 0 && medianOdd == false)this._median = treeSuccessor(this._median);
            if (state <= 0 && medianOdd == true)this._median = treePredecessor(this._median);
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


    private void replace (TNode no1 ,TNode no2){
        if (no1.getParent()== null){
            this._root = no2;
        }

        else{
            if(no1.getParent().getRight() == no1){
                no2.setParent(no1.getParent());
                no1.getParent().setRight(no2);
            }
            else{
                no2.setParent(no1.getParent());
                no1.getParent().setLeft(no2);
            }
        }
    }



}