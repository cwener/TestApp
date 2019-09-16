package com.example.anno.action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;


import com.example.anno.annotation.BindIntent;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * 自动读取Intent里面的值
 */
public class JIntentAction extends BaseAction {

    @Override
    public void run(@NonNull Activity activity, @NonNull Field field) {
        super.run(activity, field);
        BindIntent annoIntent = field.getAnnotation(BindIntent.class);
        if (annoIntent != null) {
            String value = annoIntent.value();
            Intent intent = activity.getIntent();
            if (intent == null) {
                return;
            }
            Object result = getValue(field, intent, value);
            field.setAccessible(true);
            try {
                field.set(activity, result);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }


    public void run(@NonNull Object container, @NonNull Field field, @NonNull Bundle extras) {
        BindIntent annotation = field.getAnnotation(BindIntent.class);
        if (annotation != null) {
            String value = annotation.value();
            Object result = getValue(field, extras, value);
            field.setAccessible(true);
            try {
                field.set(container, result);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * judge Field Type
     *
     * @param field
     * @param intent
     * @param key
     * @return
     */
    @NonNull
    public static Object getValue(@NonNull Field field, @NonNull Intent intent, @NonNull String key) {
        Bundle extras = intent.getExtras();
        return getValue(field, extras, key);
    }


    /**
     * 从Bundle 获取数据
     *
     * @param field  属性类型
     * @param bundle 数据源
     * @param key    key
     * @return
     */
    @NonNull
    public static Object getValue(@NonNull Field field, @NonNull Bundle bundle, @NonNull String key) {
        Class<?> type = field.getType();
        if (type == String.class) {
            return bundle.getString(key);
        }
        if (type == Character.class || type == char.class) {
            return bundle.getChar(key, '\0');
        }
        if (type == Byte.class || type == byte.class) {
            return bundle.getByte(key, (byte) 0);
        }
        if (type == Short.class || type == short.class) {
            return bundle.getShort(key, (short) 0);
        }
        if (type == Integer.class || type == int.class) {
            return bundle.getInt(key, 0);
        }
        if (type == Long.class || type == long.class) {
            return bundle.getLong(key, 0);
        }
        if (type == Float.class || type == float.class) {
            return bundle.getFloat(key, 0);
        }
        if (type == Double.class || type == double.class) {
            return bundle.getDouble(key, 0);
        }
        if (type == Boolean.class || type == boolean.class) {
            return bundle.getBoolean(key, false);
        }
        // TODO: 2019-07-06 这里解析Serializable可以看看有没有更好的方法
        if (type.getInterfaces() instanceof Serializable) {
            return bundle.getSerializable(key);
        } else if (type == Bundle.class) {
            return bundle.getBundle(key);
        } else if (type == String[].class) {
            return bundle.getStringArray(key);
        } else {
            return bundle.getString(key);
        }
    }


}
