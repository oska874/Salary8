package com.salary10.ezio.salary8;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {


    final int OLDTT = 0;
    final int HEALTHTT = 1;
    final int UNEMPTT = 2;
    final int WORKINJTT = 3;
    final int BIRTHTT = 4;
    final int HOUSETT = 5;
    final int SOCIALBASETT = 0;
    final int HOUSEBASETT = 1;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private Button calBtn;
    private TextView atsTv;
    private CheckBox[] ck6CK = new CheckBox[6];
    private EditText[] ed6Ed = new EditText[6];
    private EditText salaryEt;
    private EditText[] payBase = new EditText[2];


    private Integer dataok = 1;
    private float salary;
    private float atsSalary;
    private float[] ratios=new float[6];//oldR,workinjurR,healthR,birthR,houseR,unempR;
    private float socialBase,houseBase,socialUp,houseUp;
    private float socialRatio = 0;

    private void locateWidget(){
        calBtn = (Button)findViewById(R.id.calRun);
        atsTv = (TextView)findViewById(R.id.caledsalaryVal);

        ck6CK[OLDTT] = (CheckBox)findViewById(R.id.oldOn);
        ck6CK[HEALTHTT] = (CheckBox)findViewById(R.id.healthOn);
        ck6CK[UNEMPTT] = (CheckBox)findViewById(R.id.unempOn);
        ck6CK[WORKINJTT] = (CheckBox)findViewById(R.id.workinjurOn);
        ck6CK[BIRTHTT] = (CheckBox)findViewById(R.id.birthOn);
        ck6CK[HOUSETT] = (CheckBox)findViewById(R.id.houseOn);

        ed6Ed[OLDTT] = (EditText)findViewById(R.id.oldRatio);
        ed6Ed[HEALTHTT] = (EditText)findViewById(R.id.healthRatio);
        ed6Ed[UNEMPTT] = (EditText)findViewById(R.id.unempRatio);
        ed6Ed[WORKINJTT] = (EditText)findViewById(R.id.workinjurRatio);
        ed6Ed[BIRTHTT] = (EditText)findViewById(R.id.birthRatio);
        ed6Ed[HOUSETT] = (EditText)findViewById(R.id.houseRatio);

        salaryEt = (EditText)findViewById(R.id.mysalaryVal);
        payBase[SOCIALBASETT] = (EditText)findViewById(R.id.socicalBaseVal);
        payBase[HOUSEBASETT] = (EditText)findViewById(R.id.providBaseVal);

        for(int i=0;i<6;i++){
            ck6CK[i].setChecked(true);
        }
    }

    private int getRatio(int city){

        return 0;
    }

    private void writeRatio (){

        payBase[SOCIALBASETT].setText("17379");
        payBase[HOUSEBASETT].setText("17379");

        ed6Ed[OLDTT].setText("0.08");
        ed6Ed[HEALTHTT].setText("0.02");
        ed6Ed[UNEMPTT].setText("0.002");
        ed6Ed[WORKINJTT].setText("0");
        ed6Ed[BIRTHTT].setText("0");
        ed6Ed[HOUSETT].setText("0.12");

    }

    private float getAts(){
        float temp=0;
        for(int i=0;i<6;i++){
            temp += ratios[i];
        }
        if (temp >=1 )
            return 0;

        temp = salary*(1-temp);

        return temp;
    }

    private int getData(){
        String temp;
        dataok = 1;
        //get salary
        temp = salaryEt.getText().toString();
        if (temp.isEmpty() != true) {
            salary = Float.valueOf(temp);
        }
        else{
            dataok = 0;
        }

        socialRatio=0;
        //get ratio from editbox
        for(int i=0;i<6;i++){
            if(ck6CK[i].isChecked() == true){

                temp = ed6Ed[i].getText().toString();
                System.out.println(temp);
                if (temp.isEmpty() != true)
                    ratios[i] = Float.valueOf(temp);
                else
                    dataok = 0;
            }
            else{
                ratios[i]=0;
            }
            socialRatio+=ratios[i];
        }
        socialRatio -= ratios[HOUSETT];
        System.out.println("SR:"+socialRatio);

        if (dataok == 0){
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);

            dlgAlert.setMessage("输入错误");
            dlgAlert.setTitle("Salary8");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();

            dlgAlert.setPositiveButton("Ok",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //dismiss the dialog
                        }
                    });

            ((LinearLayout)findViewById(R.id.resLo)).setVisibility(View.INVISIBLE);

            return -1;
        }

        temp = payBase[SOCIALBASETT].getText().toString();
        if (temp.isEmpty() != true) {
            socialBase = Float.valueOf(temp);
            if (socialBase > salary)
                socialBase =salary;
        }

        temp = payBase[HOUSEBASETT].getText().toString();
        if (temp.isEmpty() != true) {
            houseBase = Float.valueOf(temp);
            if (houseBase > salary)
                houseBase = salary;
        }

        atsSalary = (socialBase*socialRatio + houseBase*ratios[HOUSETT]);

        System.out.println("ats:"+atsSalary);

        atsSalary = salary - atsSalary;
        System.out.println("ats:"+atsSalary);
        return 0;
    }

    private int updateResult(){
        PitCal pcs = new PitCal();
        float temp = atsSalary - pcs.calcPit(atsSalary);
        atsTv.setText(String.valueOf(temp));

        System.out.println("cald:"+temp+"pic:"+pcs.calcPit(atsSalary));

        ((LinearLayout)findViewById(R.id.resLo)).setVisibility(View.VISIBLE);

        return 0;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //calcuate value

        locateWidget();
        getRatio(0);
        writeRatio();

        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getData() != 0 )
                    return;
                updateResult();
            }
        });
    }

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

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
