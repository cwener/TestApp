package com.example.myannotation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by chengwen on 2019-07-02
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface TrackName {
    String name() default "";
}
