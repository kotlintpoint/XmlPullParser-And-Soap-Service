package com.example.ankitsodha.morningbatchsoapservice;

import android.util.Log;

import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Ankit Sodha on 06-Nov-15.
 */
public class MyService {

    static String SOAP_ACTION;
    static final String SERVICEURL="http://www.morningbatch.somee.com/webservice.asmx";
    static final String NAMESPACE="http://tempuri.org/";
    static final String ADDMETHOD="add";
    static final String INSERTMETHOD="insert";
    static final String GETMETHOD="getdataxml";
    public static int AddMethod(int a, int b)
    {
        try
        {
            SoapObject soapObject=new SoapObject(NAMESPACE,ADDMETHOD);
            PropertyInfo info=new PropertyInfo();
            info.setName("x");
            info.setType(Integer.class);
            info.setValue(a);
            soapObject.addProperty(info);

            info=new PropertyInfo();
            info.setName("y");
            info.setType(Integer.class);
            info.setValue(b);
            soapObject.addProperty(info);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE transport=new HttpTransportSE(SERVICEURL);
            SOAP_ACTION=NAMESPACE+ADDMETHOD;
            transport.call(SOAP_ACTION,envelope);

            Object result=envelope.getResponse();

            return Integer.parseInt(result.toString());
        }catch (Exception ex)
        {
            Log.i("Error",ex.toString());
        }
        return  -1;
    }

    public static int InsertMethod(String fname, String lname, String address)
    {
        try
        {
            SoapObject soapObject=new SoapObject(NAMESPACE,INSERTMETHOD);
            PropertyInfo info=new PropertyInfo();
            info.setName("fname");
            info.setValue(fname);
            info.setType(String.class);
            soapObject.addProperty(info);

            info=new PropertyInfo();
            info.setName("lname");
            info.setValue(lname);
            info.setType(String.class);
            soapObject.addProperty(info);

            info=new PropertyInfo();
            info.setName("address");
            info.setValue(address);
            info.setType(String.class);
            soapObject.addProperty(info);

            SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;

            HttpTransportSE transportSE=new HttpTransportSE(SERVICEURL);
            SOAP_ACTION=NAMESPACE+INSERTMETHOD;
            transportSE.call(SOAP_ACTION,envelope);

            Object obj=envelope.getResponse();

            return Integer.parseInt(obj.toString());

        }catch (Exception ex)
        {
            Log.i("Error",ex.toString());
        }
        return -1;
    }

    public static String GetData()
    {
        try
        {
            SoapObject soapObject=new SoapObject(NAMESPACE,GETMETHOD);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=true;

            HttpTransportSE transportSE=new HttpTransportSE(SERVICEURL);
            SOAP_ACTION=NAMESPACE+GETMETHOD;
            transportSE.call(SOAP_ACTION,envelope);

            Object obj=envelope.getResponse();
            /*JSONObject jsonObject=new JSONObject(obj.toString());
            String result=obj.*/
            return obj.toString();

        }catch(Exception ex)
        {
            Log.i("Error",ex.toString());
        }
        return "";
    }


}
