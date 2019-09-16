package com.example.myannotation;

import com.example.myannotation.annotation.TrackName;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/**
 * Created by chengwen on 2019-07-02
 */
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedAnnotationTypes("com.example.myannotation.annotation.TrackName")
public class MyProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (set != null && !set.isEmpty()) {
            generateJavaClassFile(set, roundEnvironment);
        }
        return false;
    }
    // 生成Java源文件
    private void generateJavaClassFile(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // set of track
        Map<String, String> trackMap = new HashMap<>();
        // print on gradle console
        Messager messager = processingEnv.getMessager();

        for (TypeElement te : annotations) {
            for (Element e : roundEnv.getElementsAnnotatedWith(te)) {
                // 打印
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + e.toString());
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + e.getSimpleName());
                messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + e.getEnclosingElement().toString());
                // 获取注解
                TrackName annotation = e.getAnnotation(TrackName.class);
                // 获取名称
                String name = "".equals(annotation.name()) ? e.getSimpleName().toString() : annotation.name();
                // 保存映射信息
                trackMap.put(e.getSimpleName().toString(), name);
            }
        }

        try {
            // 生成的包名
            String genaratePackageName = "com.example.testapp";
            // 生成的类名
            String genarateClassName = "TrackInfo";

            // 创建Java文件
            JavaFileObject f = processingEnv.getFiler().createSourceFile(genarateClassName);
            // 在控制台输出文件路径
            messager.printMessage(Diagnostic.Kind.NOTE, "Printing: " + f.toUri());
            Writer w = f.openWriter();
            try {
                PrintWriter pw = new PrintWriter(w);
                pw.println("package " + genaratePackageName + ";\n");
                pw.println("import java.util.HashMap;");
                pw.println("import java.util.Map;");
                pw.println("import com.example.myannotation.TrackInfoProvide;\n");
                pw.println("/**");
                pw.println("* @author chengwen");
                pw.println("* this file is auto-create by compiler,please don`t edit it");
                pw.println("* 页面别名映射关系表");
                pw.println("*/");
                pw.println("public class " + genarateClassName + " implements TrackInfoProvide {");

                // 缓存别名信息的列表
                pw.println("\n    private Map<String, String> trackNameMap;\n");
                // 构造方法,将别名信息注入
                pw.println("    public " + genarateClassName + "() {");
                pw.println("        trackNameMap = new HashMap<String,String>(); ");
                Iterator<String> keys = trackMap.keySet().iterator();
                while (keys.hasNext()) {
                    String key = keys.next();
                    String value = trackMap.get(key);
                    pw.println("        trackNameMap.put(\"" + key + "\",\"" + value + "\");");
                }
                pw.println("    }");
                // 实现查找信息接口
                pw.println("\n    @Override");
                pw.println("    public String getTrackNameByClass(String className) {");
                pw.println("        String output = className;");
                pw.println("        if(trackNameMap != null && !trackNameMap.isEmpty()) {");
                pw.println("            String value = trackNameMap.get(className);");
                pw.println("            output = (value == null?output:value);");
                pw.println("        }");
                pw.println("        return output;");
                pw.println("    }");
                pw.println("}");
                pw.flush();
            } finally {
                w.close();
            }
        } catch (IOException x) {
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, x.toString());
        }
    }
}
