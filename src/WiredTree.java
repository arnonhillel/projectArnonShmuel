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
    /**
     * Delete mode from tree.
     * @param id the key of the node to delete
     *   calling for private search method with the id and get a pointer to node to delete if found
     *   send the pointer to node with the key to a private delete method
     **/
    public void delete(int id){

        TNode z = search(id);
        if(z==null) {
            System.out.println("The student does not exist in the tree");
        }
        else{
            updateMedianDelete(z);
            delete1(z);
     }
    }
    /**
     *  a private delete method
     * @param nodeToDel the key of the node to delete.
     **/
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
                updateOrder(z);
               // z.setData(new Data(z.getRight().getData()._studentId,z.getRight().getData()._studentName));
               // delete(z.getRight());
            }
            else{
                if(lChild(z) && !rChild(z)){

                    this.replace(z ,z.getLeft());
                    updateOrder(z);
                 //   z.setData(new Data(z.getLeft().getData()._studentId,z.getLeft().getData()._studentName));
                  //  delete(z.getLeft());
                }
                else{
                    z.setData(new Data(treeSuccessor(z).getData()));
                    delete1(treeSuccessor(z));
                }

            }
        }

    }
    /**
     *  check if the right son of the x node is wired or a real child
     * @param x The node that checks whether he has a real right son or wired/
     * @return true if x has a real right son or not.
     **/
    private boolean rChild(TNode x) {
        return (x != null) && (x.getRight() != null) &&
                (x == x.getRight().getParent());
    }

    /**
     *  check if the left son of the x node is wired or a real child
     * @param x The node that checks whether he has a real left son or wired/
     * @return true if x has a real left son or not.
     **/
    private boolean lChild(TNode x) {
        return (x != null) && (x.getLeft() != null) &&
                (x == x.getLeft().getParent());
    }

    /**
     * method for get the successor of given node.
     * @param pNode- the pointer to the node to look for a successor.
     * @return The pointer on successor of x.
     */
    private TNode treeSuccessor(TNode pNode) {
        //methude in page 218
        //assuming the nude given is from the tree
        if (rChild(pNode)) {
            return minimum(pNode.getRight());
        } else if (pNode.getRight() == null) {
            TNode y = pNode.getParent();
            while ((y != null) && (pNode == y.getRight())) {
                pNode = y;
                y = y.getParent();
            }
            return y;
        } else {
            return pNode.getRight();

        }
    }


    /**
     * method for get the predecessor of given node.
     * @param pNode- the pointer to the node to look for a predecessor.
     * @return The pointer on predecessor of x.
     */
    private TNode treePredecessor(TNode pNode) {
        //methude in page 219
        if (lChild(pNode)) {
            return maximum(pNode.getLeft());
        } else if (pNode.getLeft() == null) {
            TNode y = pNode.getParent();
            while (y != null && pNode == y.getLeft()) {
                pNode = y;

                y = y.getParent();
            }
            return y;
        } else {
            return pNode.getLeft();
        }
    }

    /**
     * call for the max method with the root as a parameter.
     */
    public  TNode maximum(){
        return maximum(_root);
    }

    /**
     * Return the node with the maximum key value on tree .
     * @param root - the root of the tree to look for a max.
     * @return - A pointer to the the node with the maximum key value.
     */
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


    /**
     * call for the max method with the root as a parameter.
     */
    public  TNode minimum(){
        return minimum(_root);
    }
    /**
     * Return the node with the minimum key value on tree .
     * @param root - the root of the tree to look for a minimum.
     * @return - A pointer to the the node with the minimum key value.
     */
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
    /**
     * print the tree with inorder traverse.
     */
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

    /**
     * print the tree with postorder traverse.
     * call for a private postOrderPrint metode.
     */
    public  void postOrderPrint(){
        postOrderPrint(this._root);
        System.out.println();
    }
    /**
    * print the tree with postorder traverse.
     */
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
    /**
     * print the tree with preorder traverse.
     * call for a private preorder print metode.
     */
    public void preorderTreeWalk() {
        preorderTreeWalk(this._root);
        System.out.println();
    }
    /**
     * print the tree with preorder traverse.
     */
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


    /**
     * looking for the leftmost TreeNode in subtree .
     * @param  subTreeRoot  the pointer of the subtree root.
     * @return pointer for the leftmost TreeNode.
     */
    private TNode getLeftMostTreeNode(TNode subTreeRoot) {
        TNode leftMostTreeNode;

        leftMostTreeNode=subTreeRoot;

        while(lChild(leftMostTreeNode)) {
            leftMostTreeNode=leftMostTreeNode.getLeft();
        }

        return leftMostTreeNode;
    }

    /**
     * update the median after a delete.
     */
    private void updateMedianDelete (TNode current) {

            int state = this._median.getData().compareTo(current.getData());
            if (state < 0 && medianOdd == false);
            if (state >= 0 && medianOdd == false)this._median = treeSuccessor(this._median);
            if (state <= 0 && medianOdd == true)this._median = treePredecessor(this._median);
            if (state > 0 && medianOdd == true) ;
            this.medianOdd = !this.medianOdd;
    }
    /**
     * update the median after adding.
     */
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
    /**
     * @return the pointer of node with the median.
     */
    public  TNode get_median(){
        return this._median;
    }


    private void replace (TNode no1 ,TNode no2){
        if (no1.getParent()== null){

            this._root = no2;
            this._root.setParent(null);
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


    private void updateOrder (TNode node){
        TNode pre =this.treePredecessor(node);
        TNode after =this.treeSuccessor(node);
        if(pre == null){
            after.setLeft(pre);
        }
        else {
            if (pre.getRight() == node){
                pre.setRight(after);
            }
        }

        if(after == null){
            pre.setRight(after);
        }
        else{
            if(after.getLeft() == node){
                after.setLeft(pre);
            }
        }
    }
}