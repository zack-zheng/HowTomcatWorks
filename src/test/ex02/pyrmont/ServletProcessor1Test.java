package ex02.pyrmont;

import org.junit.Test;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import static org.junit.Assert.*;

/**
 * Created by jack_ on 2017/9/2.
 */
public class ServletProcessor1Test {
    @Test
    public void test_fileOperation() {
        System.out.println(System.getProperty("user.dir"));

        try {
            System.out.println("-----默认相对路径：取得路径不同------");
            File file1 = new File("..\\src\\test1.txt");
            System.out.println(file1.getPath());
            System.out.println(file1.getAbsolutePath());
            System.out.println(file1.getCanonicalPath());
            System.out.println("-----默认相对路径：取得路径不同------");
            File file = new File(".\\test1.txt");
            System.out.println(file.getPath());
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getCanonicalPath());

            System.out.println("-----默认绝对路径:取得路径相同------");
            File file2 = new File("D:\\workspace\\test\\test1.txt");
            System.out.println(file2.getPath());
            System.out.println(file2.getAbsolutePath());
            System.out.println(file2.getCanonicalPath());
        } catch (IOException e) {
            // TODOAuto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void test_url() throws IOException {
        File classPath = new File(Constants.WEB_ROOT);
        String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
        System.out.println(repository);

    }

    @Test
    public void test_urlClassLoader() throws Exception {
        /**
         * 类 URL 代表一个统一资源定位符，它是指向互联网“资源”的指针。资源可以是简单的文件或目录，也可以是对更为复杂的对象的引用，例如对数据库或搜索引擎的查询。有关 URL 的类型和格式的更多信息，可从以下位置找到：
         * http://www.socs.uts.edu.au/MosaicDocs-old/url-primer.html
         */
        URL[] urls = new URL[1];//urls是一个java.net.URL的对象数组，这些对象指向了加载类时候查找的位置
        URLStreamHandler streamHandler = null;
        urls[0] = new URL(null, "file:D:\\cloud\\IdeaProjects\\HowTomcatWorks\\webroot\\", streamHandler);//任何以/结尾的URL都假设是一个目录。否则，URL会假定是一个将被下载并在需要的时候打开的JAR文件。
        URLClassLoader loader = new URLClassLoader(urls);
        Class myClass = loader.loadClass("PrimitiveServlet");
        Servlet servlet = (Servlet) myClass.newInstance();
        Request request = new Request(null);
        Response response = new Response(null);
        servlet.service((ServletRequest) request, (ServletResponse) response);
        //System.out.println("from service");就足够说明了

    }

    @Test
    public void test_construction() {
        TestConstruction testConstruction = new TestConstruction(null, null);
    }

    @Test
    public void test_facadePattern(){
        //在这第二个应用程序中，我们增加了两个façade类: RequestFacade和ResponseFacade。RequestFacade实现了ServletRequest接口并通过在构造方法中传递一个引用了ServletRequest对象的Request实例作为参数来实例化。ServletRequest接口中每个方法的实现都调用了Request对象的相应方法。然而ServletRequest对象本身是私有的，并不能在类的外部访问。我们构造了一个RequestFacade对象并把它传递给service方法，而不是向下转换Request对象为ServletRequest对象并传递给service方法。Servlet程序员仍然可以向下转换ServletRequest实例为RequestFacade，不过它们只可以访问ServletRequest接口里边的公共方法。现在parseUri方法就是安全的了。

    }
}

class TestConstruction {
    TestConstruction(String a, Integer b) {

    }

//    TestConstruction(String a, String b) {
//
//    }
}