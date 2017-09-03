package ex01.pyrmont;

import org.junit.Test;
import org.omg.CORBA.Any;
import org.omg.CORBA.Object;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.portable.InputStream;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by jack_ on 2017/8/30.
 */
public class RequestTest {
    @Test
    public void test_userdir() {
        System.out.println(System.getProperty("user.dir") + File.separator + "webroot");

    }

    @Test
    public void testParseUri() throws Exception {
        String a = "GET /index.jsp HTTP/1.1\n" +
                "Host: localhost:8080\n" +
                "Connection: Close\n" +
                "\n";

        System.out.println(parseUri(a));

    }

    private String parseUri(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1)
                return requestString.substring(index1 + 1, index2);
        }
        return null;
    }
}