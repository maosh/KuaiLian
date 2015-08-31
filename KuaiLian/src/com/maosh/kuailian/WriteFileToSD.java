package com.maosh.kuailian;

import java.io.File;
import java.io.FileOutputStream;

import android.os.Environment;
import android.util.Log;

public class WriteFileToSD {
	static int i = 0;
	public  static void writeFile(GateBeans s) { 
		
	    String sdStatus = Environment.getExternalStorageState();  
	    if(!sdStatus.equals(Environment.MEDIA_MOUNTED)) {  
	        Log.d("TestFile", "SD card is not avaiable/writeable right now.");  
	        return;  
	    }  
	    try {  
	        String pathName="/sdcard/kuailian/";  
	        String fileName=s.getIP()+".ovpn";
	       // String fileName = s.getHostName()+".ovpn";
	        File path = new File(pathName);  
	        File file = new File(pathName + fileName);  
	        if( !path.exists()) {  
	            Log.d("TestFile", "Create the path:" + pathName);  
	            path.mkdir();  
	        }  
	        if( !file.exists()) {  
	            Log.d("TestFile", "Create the file:" + fileName);  
	            file.createNewFile();  
	        }  
	        FileOutputStream stream = new FileOutputStream(file);   
	        byte[] buf = s.getOpenVPN_ConfigData_Base64().getBytes();
	        stream.write(buf);            
	        stream.close();  
	          
	    } catch(Exception e) {  
	        Log.e("TestFile", "Error on writeFilToSD.");  
	        e.printStackTrace();  
	    }  
	} 
	public static boolean deleteFile(String fileName){
		File file = new File("fileName");
		boolean b=false;
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
			file.delete(); // delete()方法 你应该知道 是删除的意思;
			Log.i("delete", "deleted file"+ fileName);
			b = true;
			} else {
				Log.i("NoFile",  fileName + "is not a file");
			}

			} else {
				Log.i("NoExist",  fileName + "does not Exist");
			}
		return b;
		
	}
	
}
