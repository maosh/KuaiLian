package com.maosh.kuailian;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EncodingUtils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ViewFunction extends ActionBarActivity {
	String[] VPN = new String[10];
	public ViewFunction() {
		// TODO Auto-generated constructor stub
	}

		
	 public List<PackageInfo> getAllApps() {     
         List<PackageInfo> apps = new ArrayList<PackageInfo>();     
         PackageManager packageManager = this.getPackageManager();     
         //获取手机内所有应用     
         List<PackageInfo> paklist = packageManager.getInstalledPackages(0);     
         for (int i = 0; i < paklist.size(); i++) {     
             PackageInfo pak = (PackageInfo) paklist.get(i);     
             //判断是否为非系统预装的应用  (大于0为系统预装应用，小于等于0为非系统应用)   
             if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0) {     
                 apps.add(pak);     
             }     
         }     
         return apps;     
 }   
	 
	    @SuppressLint("SdCardPath")
		public void launchApp() {   
            PackageManager packageManager = this.getPackageManager();   
            List<PackageInfo> packages = getAllApps();   
            PackageInfo pa = null;   
            for(int i=0;i<packages.size();i++){   
                pa = packages.get(i);   
                //获得应用名   
                String appLabel = packageManager.getApplicationLabel(pa.applicationInfo).toString();   
                //获得包名   
                String appPackage = pa.packageName;   
           //     Log.d(""+i, appLabel+"  "+appPackage);   
            }   
           // Intent intent = packageManager.getLaunchIntentForPackage("com.maosh.androidtest");//"jp.co.johospace.jorte"就是我们获得要启动应用的包名   

            //CallOV.openFile(this.getApplicationContext() , "/sdcard/kuailian/0.ovpn");
        }
	
	

	public void onItemClick(AdapterView<?> parent, View view, int position,	long id) {

		Log.v("click",VPN[position]);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        String fileName = VPN[position];
        File file = new File("/sdcard/kuailian/"+fileName+".ovpn");
       // intent.setData(Uri.fromFile(file));
        intent.setDataAndType(Uri.fromFile(file), "application/x-openvpn-profile");
        startActivity(intent);
	}

	
	
	
}
