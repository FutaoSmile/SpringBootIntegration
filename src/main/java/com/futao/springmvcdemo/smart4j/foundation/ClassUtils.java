package com.futao.springmvcdemo.smart4j.foundation;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类操作工具类
 *
 * @author futao
 * Created on 2019-01-05.
 */
public class ClassUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(ClassUtils.class);

    /**
     * java字节码文件后缀
     */
    private final static String CLASS_SUFFIX = ".class";
    private final static String POINT = ".";
    private final static String SLASH = "/";

    /**
     * 文件协议
     */
    static class Protocol {
        private final static String FILE = "file";
        private final static String JAR = "jar";
    }

    /**
     * 获取类加载器
     * 直接从当前线程获取
     *
     * @return
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 加载类
     *
     * @param className     类名
     * @param isInitialized 是否初始化-是指是否执行类的静态代码块
     * @return
     */
    public static Class<?> loadClass(String className, boolean isInitialized) {
        Class<?> aClass = null;
        try {
            //加载类
            aClass = Class.forName(className, isInitialized, getClassLoader());
        } catch (ClassNotFoundException e) {
            LOGGER.error("加载类名【{0}】失败:" + e.getMessage(), className, e);
        }
        return aClass;
    }


    /**
     * 获取指定包下的所有类
     *
     * @param packageName
     * @return
     */
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<>();
        try {
            Enumeration<URL> resources = getClassLoader().getResources(packageName.replace(".", "/"));
            while (resources.hasMoreElements()) {
                URL element = resources.nextElement();
                if (element != null) {
                    String protocol = element.getProtocol();
                    if (Protocol.FILE.equals(protocol)) {
                        //URL会将" "(空格)解析为%20，所以这里需要将%20替换为" "(空格);或者使用element.toURI().getPath();或者URLDecoder.decode(url, "utf-8")
                        String packagePath = element.getPath().replaceAll("%20", StringUtils.SPACE);
                        addClass(classSet, packagePath, packageName);
                    } else if (Protocol.JAR.equals(protocol)) {
                        JarURLConnection jarURLConnection = (JarURLConnection) element.openConnection();
                        if (jarURLConnection != null) {
                            JarFile jarFile = jarURLConnection.getJarFile();
                            if (jarFile != null) {
                                Enumeration<JarEntry> entries = jarFile.entries();
                                while (entries.hasMoreElements()) {
                                    JarEntry jarEntry = entries.nextElement();
                                    String jarEntryName = jarEntry.getName();
                                    if (jarEntryName.endsWith(CLASS_SUFFIX)) {
                                        String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(POINT)).replace(SLASH, POINT);
                                        doAddClass(classSet, className);
                                    }
                                }
                            }
                        }

                    }
                }

            }
        } catch (IOException e) {
            LOGGER.error("容器初始化异常:" + e.getMessage(), e);
        }
        return classSet;
    }

    private static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {
        File[] files = new File(packagePath).listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                //只需要后缀为.class的且是文件夹的
                return (file.isFile() && file.getName().endsWith(CLASS_SUFFIX)) || file.isDirectory();
            }
        });
        for (File file : files) {
            String fileName = file.getName();
            //如果是文件
            if (file.isFile()) {
                //根据文件名截取类名
                String className = fileName.substring(0, fileName.lastIndexOf(POINT));
                //加载类
                doAddClass(classSet, packageName.isEmpty() ? className : packageName + POINT + className);
            } else
                //如果是文件夹
                if (file.isDirectory()) {
                    //子文件夹名
                    String subPackagePath = fileName;
                    //子包路径
                    subPackagePath = subPackagePath.isEmpty() ? subPackagePath : (packagePath + SLASH + subPackagePath);
                    String subPackageName = fileName;
                    //子包名
                    subPackageName = packageName.isEmpty() ? subPackageName : (packageName + POINT + subPackageName);
                    addClass(classSet, subPackagePath, subPackageName);
                }
        }
    }

    /**
     * 加载类并且添加到Set容器中
     *
     * @param classSet  容器
     * @param className 类名
     */
    private static void doAddClass(Set<Class<?>> classSet, String className) {
        classSet.add(loadClass(className, false));
    }

}
