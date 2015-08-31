package com.maosh.kuailian;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;

public class MaoshUtils {

	public MaoshUtils() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean testIP(String IP){
        System.out.println("executeCommand");
        Runtime runtime = Runtime.getRuntime();
        try
        {
            Process  mIpAddrProcess = runtime.exec("/system/bin/ping -c 1 " + IP);
            int mExitValue = mIpAddrProcess.waitFor();
            Log.v(" mExitValue ",Integer.toString(mExitValue));
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
	
	 public List<PackageInfo> getAllApps(PackageManager packageManager) {     
         List<PackageInfo> apps = new ArrayList<PackageInfo>();     
        // PackageManager packageManager = this.getPackageManager();     
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
	
	
	
	
}
