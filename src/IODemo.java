import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import sun.nio.ch.IOStatus;

import java.io.*;
import java.net.Socket;

public class IODemo {
    public static String readFromByteStream (InputStream io) throws IOException {
/*
        byte[] buffer = new byte[1024];
        int len = io.read(buffer);
        String message = new String(buffer,0,len,"UTF-8");
        return message;
*/

/*
        Reader reader = new InputStreamReader(io,"UTF-8");
        char[]buffer = new char[1024];
        int len = reader.read(buffer);
        String message = new String(buffer,0,len);
        return message;


        StringBuffer sb = new StringBuffer();
        Reader reader = new InputStreamReader(io,"UTF-8");
        char[] buffer = new char[2];
        int len ;
        while((len = reader.read(buffer)) != -1){
            sb.append(buffer,0,len);
        }
        String message = sb.toString();
        return message;


     Reader reader = new InputStreamReader(io,"UTF-8");
        StringBuffer sb = new StringBuffer();
        char[]buffer = new char[10];
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line ;
        while((line = bufferedReader.readLine())!=null)
          //  sb.append(line );
           sb.append(line+"\r\n");
        return sb.toString();
*/
        InputStream inputStream;
        byte[] bytes = "第一行\r\n第二行\r\n".getBytes("UTF-8");
        inputStream = new ByteArrayInputStream(bytes);
        return inputStream.toString()   ;

    }

    public static InputStream inputs () throws IOException {
        InputStream inputStream;
        Socket socket = new Socket("www.baidu.com",80);
        OutputStream os = socket.getOutputStream();
        Writer writer = new OutputStreamWriter(os,"UTF-8");
        PrintWriter printWriter = new PrintWriter(writer,false);
        printWriter.printf("GET/HTTP/1.0\r\n\r\n");
        printWriter.flush();
         inputStream = socket.getInputStream();
        return inputStream;
    }
    public static void main(String[] args) throws IOException {

        InputStream inputStream = new FileInputStream("本地文件.txt");
        System.out.println(IODemo.readFromByteStream(inputStream));
        System.out.println(IODemo.inputs());
    }
}
