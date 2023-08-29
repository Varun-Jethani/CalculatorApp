package com.example.calulator_mycap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {
    TextView workingsTV;
    TextView resultsTV;
    String workings ="";
    String formula = "";
    String tempFormula = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews(){
        workingsTV = (TextView) findViewById(R.id.data);
        resultsTV = (TextView) findViewById(R.id.result);
    }

    private void setWorkings(String givenValue){
        workings = workings + givenValue;
        workingsTV.setText(workings);
    }

    public void equalsOnClick(View view){
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        formula=workings;
        checkForPowerOf();
        checkForPercent();
        try{
            result = (double)engine.eval(formula);
        }
        catch (ScriptException e){
            Toast.makeText(this,"Invalid Input", Toast.LENGTH_SHORT).show();
        }
        if (result != null) {
            resultsTV.setText(String.valueOf(result.doubleValue()));
        }

    }

    private void checkForPercent(){
        ArrayList<Integer> indexOfPercents = new ArrayList<>();
        for (int i=0; i<workings.length();i++){
            if (workings.charAt(i) == '%'){
                indexOfPercents.add(i);
            }
            tempFormula = formula;
            for (Integer indexP : indexOfPercents){
                changeFormulaPer(indexP);
            }
            formula = tempFormula;
        }
    }

    private void changeFormulaPer(Integer indexP) {
        String numberLeftP = "";
        String numberRightP = "";

        for (int i = indexP + 1; i < workings.length(); i++) {
            if (isNumeric(workings.charAt(i))) {
                numberRightP = numberRightP + workings.charAt(i);
            } else
                break;
        }

        for (int i = indexP - 1; i >= 0; i--) {
            if (isNumeric(workings.charAt(i))) {
                numberLeftP = workings.charAt(i) + numberLeftP;
            } else
                break;
        }
        String original = numberLeftP + "%" + numberRightP;
        String changed = "(" + numberLeftP + "/100)*" + numberRightP;
        tempFormula = tempFormula.replace(original, changed);
    }
    private void checkForPowerOf(){
        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for (int i=0; i<workings.length();i++){
            if (workings.charAt(i) == '^'){
                indexOfPowers.add(i);
            }
            tempFormula = formula;
            for (Integer index : indexOfPowers){
                changeFormula(index);
            }
            formula = tempFormula;
        }
    }


    private void changeFormula(Integer index){
        String numberLeft = "";
        String numberRight = "";

        for (int i= index+1; i<workings.length();i++){
            if (isNumeric(workings.charAt(i))){
                numberRight = numberRight +workings.charAt(i);
            }
            else
                break;
        }

        for (int i= index-1; i>=0;i--){
            if (isNumeric(workings.charAt(i))){
                numberLeft = workings.charAt(i) + numberLeft;
            }
            else
                break;
        }
        String original = numberLeft+"^"+numberRight;
        String changed = "(Math.pow("+numberLeft+","+numberRight+"))";
        tempFormula = tempFormula.replace(original, changed);
    }

    private boolean isNumeric(char ch){
        if ((ch>='0' && ch<='9') || (ch == '.'))
            return true;
        return false;
    }

    public void clearOnClick(View view){
        workingsTV.setText("");
        workings = "";
        resultsTV.setText("");
        leftBracket = true;
    }

    boolean leftBracket = true;

    public void bracketsOnClick(View view){
        if (leftBracket == true){
            setWorkings("(");
            leftBracket = false;
        }
        else{
            setWorkings(")");
            leftBracket = true;
        }
    }

    public void powerOfOnClick(View view){
        setWorkings("^");
    }
    public void percentOnClick(View view){
        setWorkings("%");
    }
    public void addOnClick(View view){
        setWorkings("+");
    }
    public void subOnClick(View view){
        setWorkings("-");
    }
    public void prodOnClick(View view){
        setWorkings("*");
    }
    public void divisionOnClick(View view){
        setWorkings("/");
    }
    public void dotOnClick(View view){
        setWorkings(".");
    }
    public void zeroOnClick(View view){
        setWorkings("0");
    }
    public void oneOnClick(View view){
        setWorkings("1");
    }
    public void twoOnClick(View view){
        setWorkings("2");
    }
    public void threeOnClick(View view){
        setWorkings("3");
    }
    public void fourOnClick(View view){
        setWorkings("4");
    }
    public void fiveOnClick(View view){
        setWorkings("5");
    }
    public void sixOnClick(View view){
        setWorkings("6");
    }
    public void sevenOnClick(View view){
        setWorkings("7");
    }
    public void eightOnClick(View view){
        setWorkings("8");
    }
    public void nineOnClick(View view){
        setWorkings("9");
    }




//TODO create more operations

}
