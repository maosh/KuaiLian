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


public class selectVPNThread implements Runnable {
	static ArrayList<Map<String, Object>> mDataSucc = new ArrayList<Map<String, Object>>();
	static Map<String,Object> oneData = new HashMap<String,Object>();
	static boolean isStop = false;
	String[] VPN = new String[200];
	    public void run() {
	        //从消息池中取出一个message
	        if(isStop  == false){
	        	isStop = true;
	        }else{
	        	isStop = false;
	        }
	        //Bundle是message中的数据
	        test();
	       

	    }

		
		public void test(){
			Log.v("testStart","--->");
        	String IP = new String();
        	mDataSucc.clear();
        	for(int i = 0 ; i < MainActivity.getmData().size(); i++){
        		if(isStop == true){
        			break;
        		}
        		Message msg = MainActivity.getMainHandler().obtainMessage();
        	IP = (String)MainActivity.getmData().get(i).get("IP");
        	if(true == executeCommand(IP)){
        		Log.v("IP",IP+"测试成功");
        		mDataSucc.add(MainActivity.getmData().get(i));
        		
        		VPN[mDataSucc.size()] = (String)MainActivity.getmData().get(i).get("HostName");
        		
    	        Bundle b = new Bundle();
    	        b.putInt("TYPE", 2);
    	        b.putStringArray("VPN", VPN);
    	
    	        msg.setData(b);
    	        //传递数据
    	        MainActivity.getMainHandler().sendMessage(msg); // 向Handler发送消息,更新UI
        		
        		
        	}else{
        		Log.v("IP",IP+"测试失败");
        	//	deleteProfile(MainActivity.mData.get(i));
        		VPN[mDataSucc.size()] = (String)MainActivity.getmData().get(i).get("HostName");
        		oneData = MainActivity.getmData().get(i);
    	        Bundle b = new Bundle();
    	        b.putInt("TYPE", 3);
    	        b.putStringArray("VPN", VPN);
    	
    	        msg.setData(b);
    	        //传递数据
    	        MainActivity.getMainHandler().sendMessage(msg); // 向Handler发送消息,更新UI
        		
        	}
        	}
        	Log.v("testEnd","<---");
		}

		public void deleteProfile(Map<String, Object> p ){
			MainActivity.getmData().remove(p);
			WriteFileToSD.deleteFile("sdCard/KuaiLian"+ p.get("HostName"));
			
		
			
		}
	
		
		private boolean executeCommand(String IP){
	        System.out.println("executeCommand");
	        Runtime runtime = Runtime.getRuntime();
	        try
	        {
	            Process  mIpAddrProcess = runtime.exec("/system/bin/ping -c 1 " + IP);
	            int mExitValue = mIpAddrProcess.waitFor();
	            System.out.println(" mExitValue "+mExitValue);
	            if(mExitValue==0){
	                return true;
	            }else{
	                return false;
	            }
	        }
	        catch (InterruptedException ignore)
	        {
	            ignore.printStackTrace();
	            System.out.println(" Exception:"+ignore);
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	            System.out.println(" Exception:"+e);
	        }
	        return false;
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


