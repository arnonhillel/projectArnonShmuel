public class TNode {

    private Data _data;
    private TNode _left,_right,_parent;
    private int numOfNodes;


    /**
     *
     *
     **/
    public TNode(Data data){
        this(data,null,null,null);
    }

    /**
     *
     *
     **/
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
     *
     **/
    public Data getData() {
        if(_data==null){
            return null;
        }
        return new Data(_data);
    }

    /**
     *
     *
     **/
    public TNode getLeft() {
        if(_left==null){
            return null;
        }
        return _left;
    }


    /**
     *
     *
     **/
    public TNode getRight() {
        if(_right==null){
            return null;
        }
        return _right;
    }


    public TNode getParent() {
        return _parent;
    }


    public void setData(Data data) {
        if(_data==null) {
            throw new NullPointerException("_data is not valid!.");
        }
        this._data = new Data(_data.get_studentId(),data.get_studentName());
    }

    /**
     *
     *
     **/
    public void setLeft(TNode left) {
        this._left = left;
    }

    /**
     *
     *
     **/
    public void setRight(TNode right) {
        this._right = right;
    }

    /**
     *
     *
     **/
    public void setParent(TNode parent) {
        this._parent = parent;
    }

    public int getNumOfNodes(){
        return this.numOfNodes;
    }

    /**
     *
     **/
    public void increaseNumOfNods(){
        this.numOfNodes = numOfNodes+1;
    }

    /**
     *
     **/
    public void decreasNumOfNods(){
        this.numOfNodes = numOfNodes-1;
    }

}