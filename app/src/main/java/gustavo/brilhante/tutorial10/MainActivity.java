package gustavo.brilhante.tutorial10;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final static int MULT = 1;
    final static int DIV = 2;
    final static int PLUS = 3;
    final static int LESS = 4;

    int currentOperation = 0;

    static boolean operationSelected = false;

    int number01=0, number02=0;

    String displayStr = "";

    TextView displayTextView;

    BoundService myService;

    boolean isBound = false;


    public ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayTextView = (TextView) findViewById(R.id.display_textview);

        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                BoundService.LocalBinder binder = (BoundService.LocalBinder)service;
                myService =  binder.getService();
                isBound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                isBound=false;
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(isBound){
            unbindService(connection);
            isBound=false;
        }
    }


    public void clickAC(View v){
        number01=0;
        number02=0;
        clearDisplay();
    }

    public void clickMoreLess(View v){

    }

    public void clickPercent(View v){

    }

    public void clickComma(View v){

    }

    public void operationIgual(View v){
        if(displayTextView.getText().toString().length()>0 && isBound) {
            number02 = Integer.parseInt(displayTextView.getText().toString());
            switch (currentOperation){
                case MULT:
                    number01 = myService.multiply(number01, number02);
                    currentOperation = 0;
                    clearDisplay();
                    addOnDisplay(""+number01);
                    break;
                case DIV:
                    number01 = myService.divide(number01, number02);
                    currentOperation = 0;
                    clearDisplay();
                    addOnDisplay(""+number01);
                    break;
                case LESS:
                    number01 = myService.less(number01, number02);
                    currentOperation = 0;
                    clearDisplay();
                    addOnDisplay(""+number01);
                    break;
                case PLUS:
                    number01 = myService.sum(number01, number02);
                    currentOperation = 0;
                    clearDisplay();
                    addOnDisplay(""+number01);
                    break;
                default:
                    break;

            }
        }
    }

    public void operationDivide(View v){
        if(currentOperation==0 && displayTextView.getText().toString().length()>0) {
            number01 = Integer.parseInt(displayTextView.getText().toString());
            currentOperation = DIV;
            clearDisplay();
        }
    }
    public void operationMult(View v){
        if(currentOperation==0 && displayTextView.getText().toString().length()>0) {
            number01 = Integer.parseInt(displayTextView.getText().toString());
            currentOperation = MULT;
            clearDisplay();
        }
    }
    public void operationLess(View v){
        if(currentOperation==0 && displayTextView.getText().toString().length()>0) {
            number01 = Integer.parseInt(displayTextView.getText().toString());
            currentOperation = LESS;
            clearDisplay();
        }

    }
    public void operationPlus(View v){
        if(currentOperation==0 && displayTextView.getText().toString().length()>0) {
            number01 = Integer.parseInt(displayTextView.getText().toString());
            currentOperation = PLUS;
            clearDisplay();
        }
    }

    public void number01(View v){
        addOnDisplay("1");
    }
    public void number02(View v){
        addOnDisplay("2");
    }
    public void number03(View v){
        addOnDisplay("3");
    }
    public void number04(View v){
        addOnDisplay("4");

    }
    public void number05(View v){
        addOnDisplay("5");

    }
    public void number06(View v){
        addOnDisplay("6");

    }
    public void number07(View v){
        addOnDisplay("7");

    }
    public void number08(View v){
        addOnDisplay("8");

    }
    public void number09(View v){
        addOnDisplay("9");

    }
    public void number00(View v){
        if(displayTextView.getText().toString().length()>0)addOnDisplay("0");

    }

    public void clearDisplay(){
        displayTextView.setText("");
    }

    public void addOnDisplay(String number){
        displayTextView.setText(displayTextView.getText().toString()+number);
    }

}
