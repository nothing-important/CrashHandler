package com.example.administrator.androidcrashhandler;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {

    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity){
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity){
        if (activityList.contains(activity)){
            activityList.remove(activity);
        }
    }

    public static void finishAll(){
        for (Activity activity : activityList) {
            if (activityList.contains(activity)){
                activityList.remove(activity);
            }
            activity.finish();
        }
    }

}
