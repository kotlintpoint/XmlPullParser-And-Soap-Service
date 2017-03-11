package com.example.ankitsodha.morningbatchsoapservice;

import android.util.Log;

import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class XMLPullParserHandler {
	
	private List<User> users=new ArrayList<User>();
	User user;
	private String text;
	
	public List<User> getusers()
	{
		return users;
	}
	
	public List<User> parse(String is)
	{
		try
		{
			XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			
			XmlPullParser parser=factory.newPullParser();
			parser.setInput(new StringReader(is));
			
			int eventType=parser.getEventType();
			while (eventType!=XmlPullParser.END_DOCUMENT) {
				String tagname=parser.getName();
				switch(eventType)
				{
				case XmlPullParser.START_TAG:
					if(tagname.equalsIgnoreCase("Table"))
					{
						user=new User();
					}
					break;
				case XmlPullParser.TEXT:
					text=parser.getText();
					break;
				case XmlPullParser.END_TAG:
					if(tagname.equalsIgnoreCase("Table"))
					{
						users.add(user);
					}else if(tagname.equalsIgnoreCase("fname"))
					{
						user.set_fname(text);
					}else if(tagname.equalsIgnoreCase("lname"))
					{
						user.set_lname(text);
					}else if(tagname.equalsIgnoreCase("address"))
					{
						user.set_address(text);
					}
					break;
				}
				eventType=parser.next();
			}
		}catch(Exception ex)
		{
			Log.i("Error3",ex.toString());
		}
		return users;
	}
}
