/**
 *
 *
 */
public class Data {
    public int _studentId;// KEY
    public String _studentName;




    /**
     *
     *
     **/
    public Data(int studentId, String studentName) {
        this._studentName = studentName;
        this._studentId = studentId;
    }

    /**
     *
     *
     **/
        public Data(Data _data){
        this(_data._studentId,_data._studentName);
    }


    /**
     *
     *
     **/
    public int get_studentId() {
        return _studentId;
    }




    /**
     *
     *
     **/
    public void set_studentId(int _studentId) {

        this._studentId = _studentId;
    }


    /**
     * Returns the student name.
     * @return: _studentName.
     * */
    public String get_studentName() {
        return _studentName;
    }


    public void set_studentName(String _studentName) {

        this._studentName = _studentName;
    }



    /**
     *
     *
     **/
    public int compareTo(Data _data) {

        int d1=this._studentId;
        int d2=_data._studentId;

        if(d1<d2) {
            return -1;
        }else if(d2<d1) {
            return 1;
        }else {
            return 0;
        }
    }




    /**
     *
     *
     **/
    @Override
    public String toString() {
        String str1= "(id: "+this._studentId+" , name: "+this._studentName+")";
        return str1;
    }


}//END DATA
