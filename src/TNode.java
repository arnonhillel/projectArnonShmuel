

/**
 * @author : Arnon Hillel 302943287
 * @author : Shmuel Stav
 *class TNode represent node with
 **/

public class TNode {

    private Data _data;     //student id and student name.
    private TNode _left,_right,_parent;
    private int numOfNodes;


    /**
     * constructor
     * @param data Data Object in the node.
     **/
    public TNode(Data data){
        this(data,null,null,null);
    }

    /**
     * @param _data -Represents a Data object .
     * @param _parent -Pointer to the parent node.
     * @param _left - Pointer to the left node.
     * @param _right - Pointer the the right node.
     * @throws NullPointerException - if the _data is null.
     */
    private TNode(Data _data,TNode _parent,TNode _left, TNode _right) {

        if(_data==null) {
            throw new NullPointerException("_data is not valid.");//comment
        }

        this._data = new Data(_data);
        this._parent = _parent;
        this._left = _left;
        this._right = _right;
        this.numOfNodes=1;

    }


    /**
     *
     * @return pointer to the Data object in the node.
     **/
    public Data getData() {
        if(_data==null){
            return null;
        }
        return new Data(_data);
    }

    /**
     * @return  pointer to the left son.
     */
    public TNode getLeft() {
        if(_left==null){
            return null;
        }
        return _left;
    }


    /**
     * @return pointer to the right son.
     */
    public TNode getRight() {
        if(_right==null){
            return null;
        }
        return _right;
    }

    /**
     * @return pointer to the parent node.
     */
    public TNode getParent() {
        return _parent;
    }



    /**
     * @param data  Data object .
     * @throws NullPointerException - if the _data is null.
     */
    public void setData(Data data) {
        if(_data==null) {
            throw new NullPointerException("_data is not valid!.");
        }
        this._data =data;
    }

    /**
     * @param left  pointer to the left node.
     */
    public void setLeft(TNode left) {
        this._left = left;
    }


    /**
     * @param right  pointer to the right node.
     */
    public void setRight(TNode right) {
        this._right = right;
    }


    /**
     * @param parent  pointer to the parent node.
     */
    public void setParent(TNode parent) {
        this._parent = parent;
    }


}