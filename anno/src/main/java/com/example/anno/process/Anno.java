package com.example.anno.process;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.anno.action.BaseAction;
import com.example.anno.action.JIntentAction;
import com.example.anno.wpattern.WPatternField;
import com.example.anno.wpattern.exception.InjectionException;
import com.example.anno.wpattern.message.ErrorMessages;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;

/**
 * Processor
 */

public class Anno {

    private static final String TAG = "Anno";
    /**
     * field 处理器
     */
    private static ArrayList<BaseAction> actionListField = new ArrayList<>();


    static {
        actionListField.add(new JIntentAction());
    }

    /**
     * onCreate里面初始化，在setContentView之后
     *
     * @param activity
     */
    public static void bind(@NonNull Activity activity) {
        injectField(activity);
    }

    /**
     * 从Bundle里面获取数据放入container
     *
     * @param container
     * @param extras
     */
    public static void bind(@NonNull Object container, @Nullable Bundle extras) {
        if (extras == null) {
            return;
        }
        Class<?> clazz = container.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        if (declaredFields == null || declaredFields.length <= 0) {
            return;
        }
        for (Field field : declaredFields) {
            JIntentAction action = new JIntentAction();
            try {
                action.run(container, field, extras);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /******************* Private Part ***********************/

    private static void injectField(Activity activity) {
        Class<? extends Activity> aClass = activity.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        if (declaredFields == null || declaredFields.length <= 0) {
            return;
        }
        for (Field field : declaredFields) {
            for (BaseAction baseAction : actionListField) {
                baseAction.run(activity, field);
            }
        }

    }


    //=====================================================================================
    // PUBLIC METHODS
    //=====================================================================================

    public static void validateFields(Object objectInstance) throws InjectionException {
        Class<?> objectClass = objectInstance.getClass();

        Field[] fields = objectClass.getDeclaredFields();

        for (Field field : fields) {
            WPatternField annotationField = field.getAnnotation(WPatternField.class);

            if (annotationField != null) {
                if (annotationField.required()) {
                    if (!isValidRequeridField(field)) {
                        throw new InjectionException(String.format(ErrorMessages.FIELD_WITH_INVALID_TYPE,
                                annotationField.name(), field.getType()));
                    }
                } else if (!isValidNotRequeridField(field)) {
                    throw new InjectionException(String.format(ErrorMessages.FIELD_WITH_INVALID_TYPE,
                            annotationField.name(), field.getType()));
                }
            }
        }
    }

    //=====================================================================================
    // PRIVATE METHODS
    //=====================================================================================

    private static boolean isValidRequeridField(Field field) {
        return !((field.getType() != int.class) && (field.getType() != long.class) && (field.getType() != boolean.class) &&
                (field.getType() != char.class) && (field.getType() != double.class) && (field.getType() != float.class) &&
                (field.getType() != Date.class));
    }

    private static boolean isValidNotRequeridField(Field field) {
        return !((field.getType() != int.class) && (field.getType() != Integer.class) &&
                (field.getType() != long.class) && (field.getType() != Long.class) &&
                (field.getType() != boolean.class) && (field.getType() != Boolean.class) &&
                (field.getType() != char.class) && (field.getType() != Character.class) &&
                (field.getType() != double.class) && (field.getType() != Double.class) &&
                (field.getType() != float.class) && (field.getType() != Float.class) &&
                (field.getType() != Date.class));
    }
}
