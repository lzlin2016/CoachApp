package com.cn.coachs.ui.patient.others.myaccount;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.cn.coachs.http.NetTool;
import com.cn.coachs.my.ActivityMyAccountCenter;
import com.cn.coachs.ui.basic.ActivityBasic;
import com.cn.coachs.util.AbsParam;
import com.cn.coachs.util.Constant;
import com.cn.coachs.util.UtilsSharedData;

public class AsyGetPatientNum extends AsyncTask<Integer, Integer, String> {

    String result = "";
    private Activity act;
    private static String myFans = "/performance/detail";
    private long userId;

    public AsyGetPatientNum(Activity act) {
        this.act = act;
        UtilsSharedData.initDataShare(act);// ////////
        userId = UtilsSharedData.getLong(Constant.USER_ID, 1);
    }

    @Override
    protected String doInBackground(Integer... params) {
        HashMap<String, String> param = new HashMap<String, String>();
        param.put("expertId", userId + "");
//		param.put("userType", 1+"");		
        try {
            String url = AbsParam.getBaseUrl() + myFans;
            result = NetTool.sendPostRequest(url, param, "utf-8");
            Log.i("result", result);
        } catch (Exception e) {
            ((ActivityBasic) act).hideProgressBar();
            e.printStackTrace();
        }
        try {
            JSONObject json = new JSONObject(result);
            ActivityMyAccountCenter.fans = Integer.parseInt(json
                    .getString("mymembernum"));
//			Purse.balance = ActivityMyAccountCenter.fans;
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
//		((ActivityBasic)act).hideProgressBar();

    }


}
