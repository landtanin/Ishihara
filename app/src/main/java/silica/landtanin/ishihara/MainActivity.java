package silica.landtanin.ishihara;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity { //class name must use Capital letter

    //Explicit ประกาศตัวแปร
    private TextView questionTextView;
    private ImageView ishiharaImageView;
    private RadioGroup choiceRadioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton
            ,choice3RadioButton, choice4RadioButton;
    private Button ansButton;
    private int radioAnInt, indexAnInt, scoreAnInt;
    private MyModel objMyModel;


    //Variables have declared but not bind with id in ui yet

    @Override
    protected void onCreate(Bundle savedInstanceState) {//Method is in class
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //create Button Controller
        buttonController();

        //create Button Controller
        radioController();

        //About MyModel
        aboutMyModel();


    } // onCreate is the main method that run at initial

    private void aboutMyModel() {
        objMyModel = new MyModel();
        objMyModel.setOnMyModelChangeListener(new MyModel.OnMyModelChangeListener() {
            @Override
            public void onMyModelChangeListener(MyModel myModel) {

                //Change View
                changeView(myModel.getModelAnInt());
            }//event
        });

    }//aboutMyModel

    private void changeView(int modelAnInt) {

        //Change Image
        int intDrawable[] = {R.drawable.ishihara_01,R.drawable.ishihara_02,
                R.drawable.ishihara_03,R.drawable.ishihara_04,R.drawable.ishihara_05,
                R.drawable.ishihara_06,R.drawable.ishihara_07,R.drawable.ishihara_08,
                R.drawable.ishihara_09,R.drawable.ishihara_10};
        ishiharaImageView.setImageResource(intDrawable[modelAnInt]);

        //Change Choice
        int intTimes[] = new int[10];
        intTimes[0] = R.array.times1;
        intTimes[1] = R.array.times2;
        intTimes[2] = R.array.times3;
        intTimes[3] = R.array.times4;
        intTimes[4] = R.array.times5;
        intTimes[5] = R.array.times6;
        intTimes[6] = R.array.times7;
        intTimes[7] = R.array.times8;
        intTimes[8] = R.array.times9;
        intTimes[9] = R.array.times10;

        String strChoice[] = new String[4];
        strChoice = getResources().getStringArray(intTimes[modelAnInt]);
        choice1RadioButton.setText(strChoice[0]);
        choice2RadioButton.setText(strChoice[1]);
        choice3RadioButton.setText(strChoice[2]);
        choice4RadioButton.setText(strChoice[3]);

    } //changeView

    private void radioController() {

        choiceRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //sound Effect
                MediaPlayer radioMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_shut);
                radioMediaPlayer.start();

                //Setup radio
                switch (checkedId) {

                    case R.id.radioButton:
                        radioAnInt = 1;
                        break;
                    case R.id.radioButton2:
                        radioAnInt = 2;
                        break;
                    case R.id.radioButton3:
                        radioAnInt = 3;
                        break;
                    case R.id.radioButton4:
                        radioAnInt = 4;
                        break;
                    default:
                        radioAnInt = 0;
                        break;
                }


            } //event
        });


    }

    private void buttonController() {

        ansButton.setOnClickListener(new View.OnClickListener() { // this form is use to recall the existed class
            @Override //ดึงคลาสสำเร็จรูปมาทำงาน กดสร้างอัตโนมัติมา
            public void onClick(View v) {

                //Sound Effect
                MediaPlayer buttonMediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.effect_btn_long);
                buttonMediaPlayer.start();

                //check zero
                checkZero();

            }//event
        });

    } //buttonController

    private void checkZero() {

        if (radioAnInt == 0) {
            Toast.makeText(MainActivity.this,"ตอบด้วย",Toast.LENGTH_SHORT).show();
        }

        else {
            //check score
            checkScore();

            //check times
            checkTimes();
        }

    }

    private void checkScore() {

        int intAnswerTrue[] = {1, 2, 3, 1, 2, 3, 1, 2, 4, 4};

        if (radioAnInt == intAnswerTrue[indexAnInt]) {
            scoreAnInt++;//score record
        }
    }//checkScore

    private void checkTimes() {

        if (indexAnInt == 9) {
            //Intent to ShowScore
            intentToShowScore();

        } else {
            //Increase indexAnInt
            indexAnInt += 1;

            //Controller Call View
            questionTextView.setText(Integer.toString(indexAnInt+1)+".What is this?"  );

            //Controller Call Model
            objMyModel.setModelAnInt(indexAnInt);
        }


    }//check times

    private void intentToShowScore() {

        Intent objIntent = new Intent(MainActivity.this, ShowScoreActivity.class); // ตามสูตร INHERIATE
        objIntent.putExtra("Score", scoreAnInt);
        startActivity(objIntent);
        finish();

    }//Intent to ShowScore

    private void bindWidget() {

        questionTextView = (TextView) findViewById(R.id.txtQuestion);
        ishiharaImageView = (ImageView) findViewById(R.id.ImgVishi);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        choice4RadioButton = (RadioButton) findViewById(R.id.radioButton4);
        choiceRadioGroup = (RadioGroup) findViewById(R.id.choiceRadioGroup);
        ansButton = (Button) findViewById(R.id.ansbutton);

    } //bindWidget

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
} //Main class
