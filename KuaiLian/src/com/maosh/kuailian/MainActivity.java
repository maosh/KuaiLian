package com.maosh.kuailian;

import android.support.v7.app.ActionBarActivity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.util.Base64;
import android.util.Log;
import android.view.*;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;









import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.widget.AdapterView.OnItemClickListener;  


public class MainActivity extends ActionBarActivity  {



	private SimpleAdapter adapter = null ;
	private static MainHandler mainHandler ;
	private static ArrayList<Map<String,Object>> mData= new ArrayList<Map<String,Object>>();
	private ListView myListView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		setContentView(R.layout.activity_main);
		
		mainHandler  = new MainHandler(this.getMainLooper());
		ListView lv = (ListView) findViewById(R.id.listView1);// 得到ListView对象的引用
																// /*为ListView设置Adapter来绑定数据*/

		adapter = new SimpleAdapter(this, mData,
				R.layout.serverlist, new String[] { "image", "country", "IP" },
				new int[] { R.id.image, R.id.country, R.id.IP });
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListenerImpl());
		lv.setOnItemLongClickListener( new OnItemLongClickListenerImpl());

		loadLastData();

	}
	
	/**/
	private class OnItemClickListenerImpl implements OnItemClickListener {


		@SuppressLint("SdCardPath")
		@SuppressWarnings("unchecked")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {

		//	Log.v("click",VPN[position]);
			Log.v("click",Integer.toString(position));
	        Intent intent = new Intent("android.intent.action.VIEW");
	        intent.addCategory("android.intent.category.DEFAULT");
	      //  String fileName = VPN[position];
	      //  String fileName = (String)mData.get(position).get("hostName");
	        String fileName = (String)mData.get(position).get("IP");
	        Log.v("mDataSize",Integer.toString(mData.size()));
	        Log.v("fileName", fileName);
	        File file = new File("/sdcard/kuailian/"+fileName+".ovpn");
	       // intent.setData(Uri.fromFile(file));
	        intent.setDataAndType(Uri.fromFile(file), "application/x-openvpn-profile");
	        startActivity(intent);
		}
		}
	
	private class OnItemLongClickListenerImpl implements  OnItemLongClickListener{

		@SuppressWarnings("unused")
		public boolean onItemLongClick(AdapterView<?> arg0, View view, final int location, long arg3) {
			Log.v("Longclick", Integer.toString(location));
			String IP = (String) mData.get(location).get("IP");
			Log.v("IP", IP);
			if (true == MaoshUtils.testIP(IP)) {
				Log.v("IP: succ", IP);
				Map<String, Object> temp = new HashMap<String, Object>();
				temp.put("image", R.drawable.cn);
				temp.put("country", mData.get(location).get("country") + "   P");
				temp.put("IP", mData.get(location).get("IP"));
				temp.put("hostName", mData.get(location).get("hostName"));
				mData.set(location, temp);
				Log.v("temp.size", Integer.toString(temp.size()));
				Log.v("mData.size", Integer.toString(mData.size()));

			} else {
				// mData.remove(mData.get(location));
				mData.remove(location);
				if(Looper.myLooper() == Looper.getMainLooper()){
					Log.v("Itrue", IP);
				}else
				{
					Log.v("false main thread", IP);
				}
				
				Log.v("IP: failed", IP);
			}
			adapter.notifyDataSetChanged();
			Log.v("mDataSize", Integer.toString(mData.size()));

			return true;
		};

	}


	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		Log.v("KuaiLian","mm");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

	//read from sdcard
	public String readLastRefreshFile(String fileName) throws IOException{   
		  String res="";   
		  try{ 
			     File file = new File(fileName);
		         FileInputStream fin = new FileInputStream(file);   
		         int length = fin.available();   
		         byte [] buffer = new byte[length];   
		         fin.read(buffer);       
		         res = EncodingUtils.getString(buffer, "UTF-8");   
		         fin.close();      
		     }   
		     catch(Exception e){   
		         e.printStackTrace();   
		     }   
		     return res;   
		  
		}     
	
	public void refreshT(View view){
		RefreshThread rt = new RefreshThread();
		Thread t = new Thread(rt);
		t.start();
	}
	public void testT(View view){
		selectVPNThread sVPNt = new selectVPNThread();
		Thread t = new Thread(sVPNt);
		t.start();
	}
	
	public void testD(View view){
		Log.v("test","testDD");
		mData.remove(0);
		
		adapter.notifyDataSetChanged();
		
	}
	
	
		@SuppressLint("SdCardPath")
		public  void getNetToFile(String s) { 
			
		    String sdStatus = Environment.getExternalStorageState();  
		    if(!sdStatus.equals(Environment.MEDIA_MOUNTED)) {  
		        Log.d("TestFile", "SD card is not avaiable/writeable right now.");  
		        return;  
		    }  
		    try {  
		        String pathName="/sdcard/kuailian/";  
		       // String fileName=s.getIP()+".ovpn";
		        String fileName = "profile.vpn";
		        File path = new File(pathName);  
		        File file = new File(pathName + fileName);  
		        if( !path.exists()) {  
		            Log.d("TestFile", "Create the path:" + pathName);  
		            path.mkdir();  
		        }  
		        if( !file.exists()) {  
		            Log.d("TestFile", "Create the file:" + fileName);  
		            file.createNewFile();  
		        } else{
		        	 Log.d("TestFile", "OverWrite the file:" + fileName); 
		        }
		        FileOutputStream stream = new FileOutputStream(file);   
		        byte[] buf = s.getBytes();
		        stream.write(buf);            
		        stream.close();  
		          
		    } catch(Exception e) {  
		        Log.e("TestFile", "Error on writeFilToSD.");  
		        e.printStackTrace();  
		    }  
		}  

		@SuppressLint("SdCardPath")
		private boolean loadLastData(){
			try{
				mData.clear();
  
			  //  Log.v("result = " , EntityUtils.toString(response.getEntity(), "utf-8"));//获取服务器响应内容  
				File file = new File("/sdcard/kuailian/profile.vpn");  
				if( !file.exists()) {  
		            Log.d("TestFile", "profile.vpn doesn't exist!!!!" );  
		             return false;
		        } else{
		        	 Log.d("TestFile", "found pfofile.vpn:"); 
		        }
				
				String content = readLastRefreshFile("/sdcard/kuailian/profile.vpn");
				
				if(null != content){
					  try {
						  
						  List<GateBeans> data = new ArrayList<GateBeans>();
						  data = JsonUtils.parseGate(content);
						  
					       for(int i =0; i < data.size(); i++) { 
					    	   GateBeans gb = data.get(i);
					    //	   WriteFileToSD.writeFile(data.get(i));
							Log.v("i", Integer.toString(i));
							Log.v("CountryLong", gb.getCountryLong());
							Log.v("hostname", gb.getHostName());
							Log.v("IP", gb.getIP());
							Log.v("Uptime", gb.getUptime());
							
				    	   
					            Map<String,Object> item = new HashMap<String,Object>();  
					            item.put("image", R.drawable.cn);  
					            item.put("country", gb.getCountryLong());  
					            item.put("IP", gb.getIP()); 
					            item.put("hostName", gb.getHostName());
					            mData.add(item);   
					        }
					       ListView lv = (ListView) findViewById(R.id.listView1);//得到ListView对象的引用 /*为ListView设置Adapter来绑定数据*/
					        SimpleAdapter adapter = new SimpleAdapter(this,mData,R.layout.serverlist,  
					                new String[]{"image","country","IP"},new int[]{R.id.image,R.id.country,R.id.IP}); 
					        lv.setAdapter(adapter);
					     
						  } catch (Exception e) {
							  e.printStackTrace();
						  }
					  /////////////////////////
			
										
					
				}
				
			}catch (ClientProtocolException e) { 
			    // TODO Auto-generated catch block  
			    e.printStackTrace();  
			} catch (IOException e) {  
			    // TODO Auto-generated catch block  
			    e.printStackTrace();  
			}
			   return true;
		}

		public void deleteProfile(Map<String, Object> p ){
			mData.remove(p);
			WriteFileToSD.deleteFile("sdCard/KuaiLian"+ p.get("HostName"));
			
		
			
		}
		
		class MainHandler extends Handler {
	          public MainHandler() {
	          }
	  
	          public MainHandler(Looper L) {
	              super(L);
	          }
	  
	          // 必须重写这个方法，用于处理message
	          @Override
	          public void handleMessage(Message msg) {
	              // 这里用于更新UI
	              Bundle b = msg.getData();
	              int type = b.getInt("TYPE");
	              
	             // switch(msg.what){  
	              switch(type){
	              case 1:  
	            	  try{
	            	  List<GateBeans> data = new ArrayList<GateBeans>();
					  data = JsonUtils.parseGate(b.getString("content"));
					  Log.v( "outOfJson = " ,Integer.toString(data.size())); //获取响应码  
				       for(int i =0; i < data.size(); i++) {  
				    	   GateBeans gb = data.get(i);
				    	   WriteFileToSD.writeFile(data.get(i));
						Log.v("i", Integer.toString(i));
						Log.v("CountryLong", gb.getCountryLong());
						Log.v("hostname", gb.getHostName());
						Log.v("IP", gb.getIP());
						Log.v("Uptime", gb.getUptime());
				    	   
				            Map<String,Object> item = new HashMap<String,Object>();  
				            item.put("image", R.drawable.cn);  
				            item.put("country", gb.getCountryLong());  
				            item.put("IP", gb.getIP());
				            item.put("hostName", gb.getHostName());
				            mData.add(item);   
				        }
				       ListView lv = (ListView) findViewById(R.id.listView1);//得到ListView对象的引用 /*为ListView设置Adapter来绑定数据*/
     			        lv.setAdapter(adapter);
				        getNetToFile(b.getString("content"));
					  } catch (Exception e) {
						  e.printStackTrace();
					  } 
	                  break;  
	        

	              case 3:
	            	  MainActivity.mData.remove(selectVPNThread.oneData);
				       ListView lv = (ListView) findViewById(R.id.listView1);//得到ListView对象的引用 /*为ListView设置Adapter来绑定数据*/
				       SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,mData,R.layout.serverlist,  
				                new String[]{"image","country","IP"},new int[]{R.id.image,R.id.country,R.id.IP}); 
				        lv.setAdapter(adapter);
	                  break;  
	              case 12:  
	            	//   VPN = b.getStringArray("VPN");
				        lv = (ListView) findViewById(R.id.listView1);//得到ListView对象的引用 /*为ListView设置Adapter来绑定数据*/
				        adapter = new SimpleAdapter(MainActivity.this,mData,R.layout.serverlist,  
				                new String[]{"image","country","IP"},new int[]{R.id.image,R.id.country,R.id.IP}); 
				       adapter.notifyDataSetChanged(); 
				     //  lv.setAdapter(adapter);
	                 // mProgressBar.setMessage("Image downloading failure!");  
	                 // mProgressBar.dismiss();
	              }  
	              
	              
	            //  String color = b.getString("color");
	            //  MyHandlerActivity.this.textView.setText(color);
	              //MainActivity.this.textView.setText(color);
	          }
	      }
		
		
		public SimpleAdapter getAdapter() {
			return adapter;
		}

		public void setAdapter(SimpleAdapter adapter) {
			this.adapter = adapter;
		}

		public static MainHandler getMainHandler() {
			return mainHandler;
		}

		public static void setMainHandler(MainHandler mainHandler) {
			MainActivity.mainHandler = mainHandler;
		}

		public static ArrayList<Map<String, Object>> getmData() {
			return mData;
		}

		private static void setmData(ArrayList<Map<String, Object>> mData) {
			MainActivity.mData = mData;
		}

		public ListView getMyListView() {
			return myListView;
		}

		public void setMyListView(ListView myListView) {
			this.myListView = myListView;
		}
	  
			

		
}
