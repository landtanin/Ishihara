package silica.landtanin.ishihara;

/**
 * Created by landtanin on 6/21/15 AD.
 */
public class MyModel {

    //Explicit
    private int modelAnInt;


    //Create Interface class
    public interface OnMyModelChangeListener {

        void onMyModelChangeListener(MyModel myModel);

    } //Interface Class

    private OnMyModelChangeListener onMyModelChangeListener;

    public void setOnMyModelChangeListener(OnMyModelChangeListener onMyModelChangeListener) {
        this.onMyModelChangeListener = onMyModelChangeListener;
    }//setter to interface

    public int getModelAnInt() {
        return modelAnInt;
    } //getter

    public void setModelAnInt(int modelAnInt) {
        this.modelAnInt = modelAnInt;

        if (this.onMyModelChangeListener != null) {
            this.onMyModelChangeListener.onMyModelChangeListener(this);

        }

    } //setter
}// MyModel Class
