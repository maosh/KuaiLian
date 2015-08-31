package com.maosh.kuailian;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Base64;
import android.util.Log;

public class JsonUtils {

	 /**
     * @param citiesString    从服务器端得到的JSON字符串数据
     * @return    解析JSON字符串数据，放入List当中
     */
    public static List<GateBeans> parseGate(String gate)
    {
    	
        List<GateBeans> data = new ArrayList<GateBeans>();
        
        try
        {
        	      	
            JSONObject jsonObject = new JSONObject(gate);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            Log.v("jsonArraysize", Integer.toString(jsonArray.length()));

            for(int i = 0; i < jsonArray.length(); i++)
            {
               GateBeans gb = new GateBeans();
               JSONObject jb = (JSONObject) jsonArray.opt(i);
               gb.setHostName(new String(Base64.decode(jb.getString("HostName"), Base64.DEFAULT)));
               gb.setCountryLong(new String(Base64.decode(jb.getString("CountryLong"), Base64.DEFAULT)));
               gb.setCountryShort(new String(Base64.decode(jb.getString("CountryShort"), Base64.DEFAULT)));
               gb.setIP(new String(Base64.decode(jb.getString("IP"), Base64.DEFAULT)));
               gb.setOpenVPN_ConfigData_Base64(new String(Base64.decode(jb.getString("OpenVPN_ConfigData_Base64"), Base64.DEFAULT)));
            	
            
            	gb.setLogType(new String(Base64.decode(jb.getString("LogType"), Base64.DEFAULT)));
            	gb.setMessage(new String(Base64.decode(jb.getString("Message"), Base64.DEFAULT)));
            	gb.setNumVpnSessions(new String(Base64.decode(jb.getString("NumVpnSessions"), Base64.DEFAULT)));
            	gb.setOperator(new String(Base64.decode(jb.getString("Operator"), Base64.DEFAULT)));
            	gb.setPing(new String(Base64.decode(jb.getString("Ping"), Base64.DEFAULT)));
            	gb.setScore(new String(Base64.decode(jb.getString("Score"), Base64.DEFAULT)));
            	
            	gb.setSpeed(new String(Base64.decode(jb.getString("Speed"), Base64.DEFAULT)));
            	gb.setTotalTraffic(new String(Base64.decode(jb.getString("TotalTraffic"), Base64.DEFAULT)));
            	gb.setTotalUsers(new String(Base64.decode(jb.getString("TotalUsers"), Base64.DEFAULT)));
            	gb.setUptime(new String(Base64.decode(jb.getString("Uptime"), Base64.DEFAULT)));
                data.add(gb);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        return data;
    }
}
