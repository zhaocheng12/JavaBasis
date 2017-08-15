package TestComplier;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.*;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by admin on 2017/7/31.
 */
public class DefComplier {
    public static final String  code = "package TestComplier;\n" +
            "\n" +
            "/**\n" +
            " * Created by admin on 2017/7/31.\n" +
            " */\n" +
            "class HelloWorld {\n" +
            "    public void say(){\n" +
            "        System.out.println(\"Hello World\");\n" +
            "    }\n" +
            "}";

    public static final String currentPath = System.getProperty("user.dir");
    public static final String fileName = currentPath+"/src/TestComplier/HelloWorld.java";
    public static void main(String[] args) {
        DefComplier dc = new DefComplier();
        dc.writeFile();
        try {
            dc.complier();
            dc.loadClazz();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*把字符串写成java文件*/
    public void writeFile(){
        File file = new File(fileName);
        try{
            FileWriter fw = new FileWriter(file);
            fw.write(code);
            fw.flush();
            fw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /*编译文件*/
    public void complier() throws Exception{
        //获得系统编译器，如果使用jre（运行环境）则无法获得编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        System.out.println(compiler.getClass().getName());
        //获得文件管理器
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null,null,null);
        //可以得到多个可迭代的编译对象
        Iterable Files = fileManager.getJavaFileObjects(fileName);
        //获得编译任务
        CompilationTask t = compiler.getTask(null,fileManager,null,null,null,Files);
        //开始编译，得到class文件
        t.call();
        fileManager.close();
    }

    /*把class文件加载到内存*/
    /*URLClASSloader加载器用于从指向 JAR 文件和目录的 URL 的搜索路径加载类和资源。*/
    public void loadClazz() throws Exception {
        URL[] urls = new URL[]{new URL("file:/" + currentPath + "/src/")};
        ClassLoader loader = new URLClassLoader(urls);
        Class clazz = loader.loadClass("TestComplier.HelloWorld");
        HelloWorld h = (HelloWorld)clazz.newInstance();
        h.say();
    }
}
