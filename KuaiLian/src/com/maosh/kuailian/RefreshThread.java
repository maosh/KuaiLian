package com.maosh.kuailian;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RefreshThread implements Runnable {
    public void run() {
        //����Ϣ����ȡ��һ��message
        Message msg = MainActivity.getMainHandler().obtainMessage();
        //Bundle��message�е�����
        Bundle b = new Bundle();
        b.putInt("TYPE", 1);
        resultFromInternet rr = new resultFromInternet();
        try {
			rr = (resultFromInternet)refresh().clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        b.putBoolean("is", rr.isB());
        b.putString("content", rr.getContent());
        msg.setData(b);
        //��������
        MainActivity.getMainHandler().sendMessage(msg); // ��Handler������Ϣ,����UI
    }
	public RefreshThread() {
		// TODO Auto-generated constructor stub
	}
	
	public resultFromInternet refresh(){
		Log.v("refresh","refresh");
		resultFromInternet rfi = new resultFromInternet();
		rfi.setB(false);
		String url="http://gateflask.herokuapp.com/gatejson";
		HttpGet getMethod = new HttpGet(url);
		
		HttpClient httpClient = new DefaultHttpClient();
		Log.v("refresh","response");
		try{
			HttpResponse response = httpClient.execute(getMethod);
			int resCode = response.getStatusLine().getStatusCode();
			Log.v( "resCode = " ,Integer.toString(resCode)); //��ȡ��Ӧ��  
		  //  Log.v("result = " , EntityUtils.toString(response.getEntity(), "utf-8"));//��ȡ��������Ӧ����  
			String content = new String(EntityUtils.toString(response.getEntity(), "utf-8"));
			
			if(200 == resCode){
				
				rfi.setB(true);
				rfi.setContent(content);
			}
			
		}catch (ClientProtocolException e) {  
		    // TODO Auto-generated catch block  
		    e.printStackTrace();  
		} catch (IOException e) {  
		    // TODO Auto-generated catch block  
		    e.printStackTrace();  
		}
		return rfi;
	}

	
	
	class resultFromInternet implements Cloneable{
		boolean b;
		String content;
	public boolean isB() {
			return b;
		}
		public void setB(boolean b) {
			this.b = b;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		
		   @Override
		    public Object clone() throws CloneNotSupportedException {
		        return super.clone();
		    }

	}

	

}


