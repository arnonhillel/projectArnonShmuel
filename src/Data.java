

  /**
   * @author : Arnon Hillel 302943287
   * @author : Shmuel Stav
   *
   * this Class represent the data to be set in nods.
   */
public class Data {
    public int _studentId;// KEY
    public String _studentName;

    //constructors

    /**
     * @param studentId the student id in data ,represent the key value.
     * @param studentName the name of the student.
     *
     **/
    public Data(int studentId, String studentName) {
        this._studentName = studentName;
        this._studentId = studentId;
    }

    /**
     * @param: _data Data object that copied.
     * copy constructor
     **/
        public Data(Data _data){
        this(_data._studentId,_data._studentName);
    }


    /**
     * @return: the studnt id.
     **/
    public int get_studentId() {
        return _studentId;
    }




     /**
     * @param _studentId the studnt id to be set.
    **/
    public void set_studentId(int _studentId) {

        this._studentId = _studentId;
    }


    /**
     * @return: _studentName.
     * */
    public String get_studentName() {
        return _studentName;
    }

      /**
       * @param _studentName the studnt name to be set.
       **/
    public void set_studentName(String _studentName) {

        this._studentName = _studentName;
    }



      /**
       * Compares this Data object with the other Data object by the key value.
       * @param _data Data object to be compared.
       * @throws NullPointerException if _data is null.
       */
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
       * @return a string  of the Data.
       */
    @Override
    public String toString() {
        String str1= "(id: "+this._studentId+" , name: "+this._studentName+")";
        return str1;
    }


}//END class of DATA
