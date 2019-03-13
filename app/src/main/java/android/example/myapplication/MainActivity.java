package android.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        //setContentView(R.layout.layout_login);
        //setContentView(R.layout.activity_main);

    }
    public void  btn_Register(View view){

       new Register().execute();



    }
    public void toLogin(View view){
        setContentView(R.layout.layout_login);
    }
    public void Login(View view){
       new Login().execute();
    }
    public void btnLogout(View view){
        setContentView(R.layout.layout_login);
    }
    public void toRegister(View view){

        setContentView(R.layout.layout_register);}
    class Login extends AsyncTask<String,Void,String>{
        protected String doInBackground(String... strings){
            String info;
            EditText email= (EditText)findViewById(R.id.login_email);
            EditText pwd=(EditText)findViewById(R.id.login_password);
            String inputEmail=email.getText().toString();
            String inputPwd=pwd.getText().toString();
            if(inputEmail.isEmpty()||inputPwd.isEmpty()){
               info="F";
                return info;
            }
            Log.d("TAG","the below is info");
            SharedPreferences sharedPreferences=getSharedPreferences("user",MODE_PRIVATE);

            String myemail=sharedPreferences.getString("email","");
            String mypwd=sharedPreferences.getString("password","");

            Log.d("TAG",myemail);
            Log.d("TAG",mypwd);
            if(inputEmail.equals(myemail)&&inputPwd.equals(mypwd)){
               info="R";
            }
            else{
                info="F";
            }
            return info;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equals("R")){
                setContentView(R.layout.activity_main);
            }
            if(s.equals("F"))
                Toast.makeText(getApplicationContext(),"the mail fail to match password",Toast.LENGTH_LONG);
        }
    }
    class Register extends  AsyncTask<String,Void,String> {
        protected String doInBackground(String... strings) {
            String info="";
            EditText name= (EditText)findViewById(R.id.name);
            EditText pwd=(EditText)findViewById(R.id.password);
            EditText email=(EditText)findViewById(R.id.email);
            String myname= name.getText().toString();
            String myeamil=email.getText().toString();
            String mypwd=pwd.getText().toString();
            Log.d("TAG","the below is judge statement");
            if(myname.isEmpty()||mypwd.isEmpty()||!(myeamil.contains("@"))){

                info="fail";
                return info ;
            }
            else {
                Log.d("TAG","the below is storing statement");
                SharedPreferences userShared = getSharedPreferences("user", MODE_PRIVATE);
                SharedPreferences.Editor editor = userShared.edit();
                editor.putString("name", myname);
                editor.putString("email", myeamil);
                editor.putString("password", mypwd);
                editor.commit();

                info="success";
                return info ;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Button btn=(Button) findViewById(R.id.btnRegister);
            btn.setEnabled(false);

            if(s.equals("success"))
            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
            if(s.equals("fail"))
                Toast.makeText(getApplicationContext(), "Illegal String", Toast.LENGTH_SHORT).show();
        }
    }
    }

