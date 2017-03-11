package com.example.ankitsodha.morningbatchsoapservice;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ShowData extends AppCompatActivity {

    ListView mylist;
    String resultString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        mylist=(ListView)findViewById(R.id.mylist);
        (new GetData()).execute();
    }

    class GetData extends AsyncTask<String, Void, String>
    {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(ShowData.this);
            pd.setTitle("Loading");
            pd.setMessage("Wait");
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {

            resultString=MyService.GetData();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pd.dismiss();
            //mytextview.setText("DATA : "+resultString);
            //XMLDOM Parsing
            try {

                XMLPullParserHandler handler=new XMLPullParserHandler();
                List<User> UserList=handler.parse(resultString);
                ArrayAdapter<User> adapter=new ArrayAdapter<User>(getApplicationContext(),android.R.layout.simple_list_item_1,UserList);
                mylist.setAdapter(adapter);


            }catch (Exception ex)
            {
                Log.i("Error1",ex.toString());
            }

        }
    }


    private String getValue(String tag,Element ele)
    {
        NodeList nodelist=ele.getElementsByTagName(tag).item(0).getChildNodes();
        Node node=(Node)nodelist.item(0);
        return node.getNodeValue();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_data, menu);
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
