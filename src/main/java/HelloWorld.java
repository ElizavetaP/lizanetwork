import org.apache.log4j.Logger;

import java.io.*;

/**
 * Created by Elizaveta on 6/20/16.
 */
public class HelloWorld {

    private static final Logger log = Logger.getLogger(HelloWorld.class);

    public static void main(String[] args) {

        log.debug("Hello world!");

        String hello = "Hello world!";
        File file = new File("/home/test/git/hellow/hello.txt");
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(hello);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        String searchname = "    uui ";
        String[] names = searchname.split(" ");
        for (String name : names) {
            System.out.println(name);
        }
    }
}
