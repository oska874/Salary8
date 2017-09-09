package com.salary10.ezio.salary8;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.res.Resources;
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

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {


    public static final int OLDTT = 0;
    public static final int HEALTHTT = 1;
    public static final int UNEMPTT = 2;
    public static final int WORKINJTT = 3;
    public static final int BIRTHTT = 4;
    public static final int HOUSETT = 5;
    public static final int RATIOSNUM=6;

    public static final int SOCIALBASETT = 0;
    public static final int HOUSEBASETT = 1;
    public static final int SOCIALBASEDOWNTT = 2;
    public static final int HOUSEBASEDOWNTT = 3;

    public static final int BEIJING=0;
    public static final int SHANGHAI=1;
    public static final int GUANGDONG=2;
    public static final int SHENZEHN=3;
    public static final int SHANNXI=4;
    public static final int TIANJIN=5;


    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }


    private Button calBtn;
    private TextView atsTv;
    private TextView taxedTv;
    private CheckBox[] ck6CK = new CheckBox[6];
    private EditText[] ed6Ed = new EditText[6];
    private EditText[] edcom6Ed = new EditText[6];
    private EditText salaryEt;
    private EditText[] payBase = new EditText[2];
    private DrawerLayout leftDly;
    private ListView leftDlt;
    private String[] leftPt;
    private TextView citynameTv;


    private Integer dataok = 1;
    private float salary;
    private float atsSalary;
    private float comCost;
    private float[] ratios=new float[6];//oldR,workinjurR,healthR,birthR,houseR,unempR;
    private float[] ratiosCom=new float[6];
    private float socialBase,houseBase,socialDown,houseDown;
    private float socialRatio = 0;
    private float socialcomRatio = 0;

    //找出要用的控件變量
    private void locateWidget(){
        calBtn = (Button)findViewById(R.id.calRun);
        atsTv = (TextView)findViewById(R.id.caledsalaryVal);
        taxedTv = (TextView)findViewById(R.id.taxedsalaryVal);
        citynameTv =(TextView)findViewById(R.id.cityName);

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

        edcom6Ed[OLDTT] = (EditText)findViewById(R.id.oldComRatio);
        edcom6Ed[HEALTHTT] = (EditText)findViewById(R.id.healthComRatio);
        edcom6Ed[UNEMPTT] = (EditText)findViewById(R.id.unempComRatio);
        edcom6Ed[WORKINJTT] = (EditText)findViewById(R.id.workinjurComRatio);
        edcom6Ed[BIRTHTT] = (EditText)findViewById(R.id.birthComRatio);
        edcom6Ed[HOUSETT] = (EditText)findViewById(R.id.houseComRatio);

        salaryEt = (EditText)findViewById(R.id.mysalaryVal);
        payBase[SOCIALBASETT] = (EditText)findViewById(R.id.socicalBaseVal);
        payBase[HOUSEBASETT] = (EditText)findViewById(R.id.providBaseVal);

        for(int i=0;i<6;i++){
            ck6CK[i].setChecked(true);
        }

        leftDly = (DrawerLayout)findViewById(R.id.left_drawer);
        leftDlt = (ListView)findViewById(R.id.left_list);
        leftPt = getResources().getStringArray(R.array.city_array);

    }

    private int getRatio(int city){

        return 0;
    }

    //更新不同地方五險一金比例
    private void writeRatio (int site){

        getRatio(0);

        Resources res =getResources();
        String[] ratios=new String[RATIOSNUM*2+4];
        switch (site){
            case BEIJING:
                ratios = res.getStringArray(R.array.bjSin);
                break;
            case SHANGHAI:
                ratios = res.getStringArray(R.array.shSin);
                break;
            case SHANNXI:
                ratios = res.getStringArray(R.array.snxSin);
                break;
            case GUANGDONG:
                ratios = res.getStringArray(R.array.gdSin);
                break;
            case SHENZEHN:
                ratios = res.getStringArray(R.array.szSin);
                break;
            case TIANJIN:
                ratios = res.getStringArray(R.array.tjSin);
                break;
            default:
                System.out.println("error positon");
                break;
        }


        ed6Ed[OLDTT].setText(ratios[OLDTT]);
        ed6Ed[HEALTHTT].setText(ratios[HEALTHTT]);
        ed6Ed[UNEMPTT].setText(ratios[UNEMPTT]);
        ed6Ed[WORKINJTT].setText(ratios[WORKINJTT]);
        ed6Ed[BIRTHTT].setText(ratios[BIRTHTT]);
        ed6Ed[HOUSETT].setText(ratios[HOUSETT]);

        edcom6Ed[OLDTT].setText(ratios[OLDTT+RATIOSNUM]);
        edcom6Ed[HEALTHTT].setText(ratios[HEALTHTT+RATIOSNUM]);
        edcom6Ed[UNEMPTT].setText(ratios[UNEMPTT+RATIOSNUM]);
        edcom6Ed[WORKINJTT].setText(ratios[WORKINJTT+RATIOSNUM]);
        edcom6Ed[BIRTHTT].setText(ratios[BIRTHTT+RATIOSNUM]);
        edcom6Ed[HOUSETT].setText(ratios[HOUSETT+RATIOSNUM]);

        payBase[SOCIALBASETT].setText(ratios[RATIOSNUM+RATIOSNUM+SOCIALBASETT]);
        payBase[HOUSEBASETT].setText(ratios[RATIOSNUM+RATIOSNUM+HOUSEBASETT]);

        socialDown=Float.valueOf(ratios[RATIOSNUM+RATIOSNUM+SOCIALBASEDOWNTT]);
        houseDown=Float.valueOf(ratios[RATIOSNUM+RATIOSNUM+HOUSEBASEDOWNTT]);

    }

    /*
    //計算要上稅的工資
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
    */

    //從空間讀取五險一金比例，并計算出單位成本和上稅工資金額
    private int getData(){
        String temp;
        float temp2,temp3;
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
        socialcomRatio=0;
        //get ratio from editbox
        for(int i=0;i<6;i++){
            if(ck6CK[i].isChecked() == true){

                temp = ed6Ed[i].getText().toString();

                if (temp.isEmpty() != true) {
                    ratios[i] = Float.valueOf(temp);
                }

                temp = edcom6Ed[i].getText().toString();

                if (temp.isEmpty() != true) {
                    ratiosCom[i] = Float.valueOf(temp);
                }

                else
                    dataok = 0;
            }
            else{
                ratios[i]=0;
                ratiosCom[i]=0;
            }

            socialRatio += ratios[i];
            socialcomRatio += ratiosCom[i];
        }
        socialRatio -= ratios[HOUSETT];
        socialcomRatio -= ratiosCom[HOUSETT];

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
            if (socialDown > salary)
                temp2 = socialDown;
            else if (socialBase > salary)
                temp2 = socialBase;
            else {
                temp2 = salary;
            }
        }
        else {
            temp2 = 0;
        }

        temp = payBase[HOUSEBASETT].getText().toString();
        if (temp.isEmpty() != true) {
            temp3 = Float.valueOf(temp);
            if (houseDown > salary) {
                temp3 = houseDown;
            } else if (houseBase > salary) {
                temp3 = houseBase;
            }
            else {
                temp3 = salary;
            }
        }
        else{
            temp3 = 0;
        }

        System.out.println("su:"+socialBase+"\nsd:"+socialDown+"\nhu:"+houseBase+"\nhd:"+houseDown);

        atsSalary = temp2*socialRatio + temp3*ratios[HOUSETT];
        comCost = temp2*socialcomRatio + temp3*ratiosCom[HOUSETT];

        comCost += salary;
        atsSalary = salary - atsSalary;

        return 0;
    }

    //更新最總顯示結果
    private int updateResult(){
        PitCal pcs = new PitCal();

        float temp = atsSalary - pcs.calcPit(atsSalary);

        atsTv.setText(String.valueOf(temp));

        taxedTv.setText((String.valueOf(comCost)));

        System.out.println("cald:"+temp+"pic:"+pcs.calcPit(atsSalary)+comCost);

        ((LinearLayout)findViewById(R.id.resLo)).setVisibility(View.VISIBLE);

        return 0;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    //選擇不同城市、省份，刷新界面顯示
    private void selectItem(int position) {
        leftDlt.setItemChecked(position,true);
        citynameTv.setText(leftPt[position]);
        writeRatio(position);
        leftDly.closeDrawer(leftDlt);
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
        writeRatio(BEIJING);

        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getData() != 0 )
                    return;
                updateResult();
            }
        });

        leftDlt.setAdapter(new ArrayAdapter<String>(this,
                R.layout.fg_content,leftPt));
        leftDlt.setOnItemClickListener(new DrawerItemClickListener());
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
