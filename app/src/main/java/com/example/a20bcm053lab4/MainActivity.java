package com.example.a20bcm053lab4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.a20bcm053lab4.ExpressionEvaluation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;
import java.util.*;

interface Operations{public long factorial(int n);}

public class MainActivity extends AppCompatActivity {
    static int map_size = 18;
    static int iterator = -1;
    Button[] btn = new Button[map_size];
    Button evalExp,clearScr,factorialOfExp,sqrtOfExp,floatingPoint;
    ImageButton popElement;
    TextView textView;
    HashMap<Integer,Character> expression;
    int[] expression_keys = {R.id.b0,R.id.b1,R.id.b2,R.id.b3,R.id.b4,R.id.b5,R.id.b6,R.id.b7,
                             R.id.b8,R.id.b9,R.id.b_add,R.id.b_sub,R.id.b_div,R.id.b_mult,
                             R.id.b_mod,R.id.b_pow,R.id.b_left_parenthesis,R.id.b_right_parenthesis};
    char[] expression_values = {'0','1','2','3','4','5','6','7','8','9','+','-','/','*','%','^','(',')'};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        expression = new HashMap<>();
//        for(int i=0;i<map_size;i++){expression.put(expression_keys[i],expression_values[i]);}
        // getElementsByID
        textView       = findViewById(R.id.textView);
        evalExp        = findViewById(R.id.b_eval);
        clearScr       = findViewById(R.id.b_ac);
        factorialOfExp = findViewById(R.id.b_factorial);
        sqrtOfExp      = findViewById(R.id.b_square_root);
        floatingPoint  = findViewById(R.id.b_dot);
        popElement     = findViewById(R.id.b_back);
        for(int i=0;i<map_size;i++){btn[i]=(Button)findViewById(expression_keys[i]);}

        //eventListener
        for(Button b:btn){
             b.setOnClickListener(new View.OnClickListener(){
                 @Override
                 public void onClick(View view) {
                     try{
//                         char getChar = expression.get(expression_keys[++iterator]);
                         String expr = textView.getText().toString().concat(b.getText().toString());
                         textView.setText(expr);
                         Toast.makeText(MainActivity.this,b.getText(),
                                               Toast.LENGTH_SHORT).show();
                     }catch(Exception e){
                         Toast.makeText(MainActivity.this,expression_keys[iterator]+
                                               " : "+e, Toast.LENGTH_SHORT).show();
                     }
                 }
             });
        }

        factorialOfExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String numString = textView.getText().toString();
                    int n = Integer.parseInt(numString);
                    Operations operations = (num)->{ long integer = 1;
                        for(;num>0;num--){integer*=num;}
                        return integer;
                    };
                    textView.setText(""+operations.factorial(n));
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        });

        sqrtOfExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 try{
                     String numString = textView.getText().toString();
                     int num = Integer.parseInt(numString);
                     textView.setText(""+Math.sqrt(num));
                 }catch(Exception e){
                     Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                 }
            }
        });

        clearScr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
            }
        });
        evalExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 try{
                     ExpressionEvaluation EvalExp = new ExpressionEvaluation();
                     String expr = textView.getText().toString();
                     int val = EvalExp.expressionValue(expr);
                     textView.setText("" + val);
                     Toast.makeText(MainActivity.this, ""+val, Toast.LENGTH_SHORT).show();
                 }catch(Exception e){
                     Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                 }
            }
        });
        popElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Stack<Character> expressionArray = new Stack<>();
                String expr = textView.getText().toString();
                try{
                    for(int i=0;i<expr.length();i++){expressionArray.push(expr.charAt(i));}
                    expressionArray.pop();
                    StringBuilder newExpression = new StringBuilder();
                    for(char charExp: expressionArray){
                        newExpression.append(charExp);}
                    textView.setText(newExpression.toString());
                }catch(Exception e){
                    Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }
                
            }
        });

    }
}