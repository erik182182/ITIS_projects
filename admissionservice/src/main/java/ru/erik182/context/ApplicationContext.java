package ru.erik182.context;

import lombok.SneakyThrows;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.*;

public class ApplicationContext {
     private static final String CLASS_ENDING = ".class";
    private Set<Class> classes = new HashSet<>();

    public ApplicationContext(Properties context) throws ClassNotFoundException {
        for(String name:context.stringPropertyNames()){
            classes.addAll(find(context.getProperty(name)));
        }
    }


    private static List<Class<?>> find(String scannedPackage) {
        String scannedPath = scannedPackage.replace(".", "/");
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
        if (scannedUrl == null) {
            throw new IllegalArgumentException();
        }
        File scannedDir = new File(scannedUrl.getFile());
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (File file : scannedDir.listFiles()) {
            classes.addAll(find(file, scannedPackage));
        }
        return classes;
    }
    private static List<Class<?>> find(File file, String scannedPackage) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        String resource = scannedPackage + "." + file.getName();
        if (resource.endsWith(CLASS_ENDING)) {
            int endIndex = resource.length() - CLASS_ENDING.length();
            String className = resource.substring(0, endIndex);
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
    }

    @SneakyThrows
    public Object getBean(Class bean){
        for(Class result: classes){
            if(bean.isAssignableFrom(result)){
                Constructor constructor = result.getConstructors()[0];
                List<Object> objects = new ArrayList<>();
                for(Class param:constructor.getParameterTypes()){
                    objects.add(getBean(param));
                }
                return constructor.newInstance(objects.toArray());
            }
        }
        return null;
    }

}
