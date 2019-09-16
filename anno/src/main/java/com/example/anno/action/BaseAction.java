package com.example.anno.action;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.v4.app.Fragment;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * 通用Action，注解有各自处理类
 */

public class BaseAction {
    protected static final String TAG = "BaseAction";
//    protected Class<? extends Activity> activityClass;
//
//    public BaseAction(Class<? extends Activity> activityClass) {
//        this.activityClass = activityClass;
//    }

    @CallSuper
    public void run(Activity activity) throws Exception {

    }

//    @CallSuper
//    public void run(Activity activity, Class<? extends Activity> activityClass) throws Exception {
//
//    }

    @CallSuper
    public void run(Activity activity, Method method) throws Exception {

    }

    @CallSuper
    public void run(Activity activity, Field field){

    }


    /**
     * Fragment调用
     *
     * @param fragment
     * @param field
     * @param view
     * @throws Exception
     */
    @CallSuper
    public void run(Fragment fragment, Field field, View view) throws Exception {

    }

    /**
     * ViewHolder调用
     *
     * @param container
     * @param field
     * @param view
     * @throws Exception
     */
    @CallSuper
    public void run(Object container, Field field, View view) throws Exception {

    }

    /**
     * 基本类型返回值
     */
    protected static void addValuePrimitive(ArrayList param, Class clazz) {
        if (clazz.isAssignableFrom(int.class)) {
            param.add(0);
        } else if (clazz.isAssignableFrom(byte.class)) {
            param.add((byte) 0);
        } else if (clazz.isAssignableFrom(short.class)) {
            param.add((short) 0);
        } else if (clazz.isAssignableFrom(long.class)) {
            param.add(0L);
        } else if (clazz.isAssignableFrom(float.class)) {
            param.add(0.0f);
        } else if (clazz.isAssignableFrom(double.class)) {
            param.add(0.0d);
        } else if (clazz.isAssignableFrom(char.class)) {
            param.add('\u0000');
        } else if (clazz.isAssignableFrom(boolean.class)) {
            param.add(false);
        }
    }


    public static ArrayList getMethodDefault(Method method) {
        Class<?>[] types = method.getParameterTypes();
        ArrayList param = new ArrayList();
        for (Class<?> type : types) {
            if (type.isPrimitive()) {
                addValuePrimitive(param, type);
            } else {
                //new Object();
                param.add(null);
            }
        }
        return param;
    }
}
