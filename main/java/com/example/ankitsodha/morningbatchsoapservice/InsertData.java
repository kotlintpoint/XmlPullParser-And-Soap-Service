package com.example.ankitsodha.morningbatchsoapservice;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertData extends AppCompatActivity {

    EditText fnameEditText,lnameEditText,addressEditText;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        fnameEditText=(EditText)findViewById(R.id.fnameEditText);
        lnameEditText=(EditText)findViewById(R.id.lnameEditText);
        addressEditText=(EditText)findViewById(R.id.addressEditText);
    }

    public void onCallService(View view) {
        String fname = fnameEditText.getText().toString();
        String lname = lnameEditText.getText().toString();
        String address = addressEditText.getText().toString();
        (new InsertDataInService()).execute(fname, lname, address);
    }
    class InsertDataInService extends AsyncTask<String, Void, String>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            pd=new ProgressDialog(InsertData.this);
            pd.setTitle("Loading");
            pd.setMessage("Wait");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            result=MyService.InsertMethod(params[0], params[1], params[2]);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            //mytextview.setText("Insert Method Response : "+String.valueOf(result));
            if(result==1)
                Toast.makeText(InsertData.this,"Insertion Success !!!",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(InsertData.this,"Insertion Fail !!!",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_insert_data, menu);
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
