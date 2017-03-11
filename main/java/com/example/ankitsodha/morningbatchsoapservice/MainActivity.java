package com.example.ankitsodha.morningbatchsoapservice;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText no1EditText,no2EditText;

    TextView mytextview;
    int result;
    String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mytextview=(TextView)findViewById(R.id.mytextview);
        no1EditText=(EditText)findViewById(R.id.no1EditText);
        no2EditText=(EditText)findViewById(R.id.no2EditText);

    }

    public void onCallService(View view)
    {
        switch (view.getId()) {
            case R.id.btnCall:
                String n1=no1EditText.getText().toString();
                String n2=no2EditText.getText().toString();
                (new GetAddition()).execute(n1,n2);
                break;
            case R.id.btnInsert:
                startActivity(new Intent(getApplicationContext(),InsertData.class));
                break;
            case R.id.btnGetData:
                startActivity(new Intent(getApplicationContext(),ShowData.class));
                break;
        }
    }

    class GetAddition extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            int n1=Integer.parseInt(params[0]);
            int n2=Integer.parseInt(params[1]);
            result=MyService.AddMethod(n1, n2);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mytextview.setText("Addition Result : "+String.valueOf(result));
        }
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
}
