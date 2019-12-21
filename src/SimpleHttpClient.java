import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SimpleHttpClient {
    public static void main(String[] args) throws IOException {
        String request = "GET / HTTP/1.0\r\nHost:www.baidu.com\r\n\r\n";
        Socket socket = new Socket("www.baidu.com",80);
        socket.getOutputStream().write(request.getBytes("UTF-8"));
        byte[]bytes = new byte[4096];
        int len = socket.getInputStream().read(bytes);

       // String repsonds = new String(bytes,0,len,"UTF-8");
     //   System.out.println(repsonds);

        int index = -1;
        for (int i = 0; i < len - 3; i++) {
            if (bytes[i] == '\r' && bytes[i+1] == '\n' && bytes[i+2] == '\r' && bytes[i+3] == '\n') {
                index = i;
                break;
            }
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes, 0, index + 4);
        Scanner scanner = new Scanner(byteArrayInputStream, "UTF-8");
        String statusLine = scanner.nextLine();
        String [] qq = statusLine.split(" ");
        System.out.println(qq[0]);
        System.out.println(qq[1]);
        System.out.println(qq[2]);
        System.out.println("状态行: " + statusLine);
        String line;
        //正文长度
        int curlen = 0;
        while (!(line = scanner.nextLine()).isEmpty()) {
            String[] kv = line.split(":");
            String key = kv[0].trim();
            String value = kv[1].trim();
            System.out.println("响应头: " + key + " = " + value);
            if(key.equalsIgnoreCase("Content-Length")){
                curlen = Integer.valueOf(value);
            }
        }
        System.out.println(curlen);
        int 已经读了 = len - index -4;
        int 还应该读  = curlen - 已经读了;
        byte[] body = new byte[curlen];
        System.arraycopy(bytes,index+4,body,0,已经读了);
        int 实际  = socket.getInputStream().read(body,已经读了,还应该读);
        System.out.println(已经读了);
        System.out.println(实际);
        System.out.println(还应该读);

        FileOutputStream fileOutputStream = new FileOutputStream("百度.html");
        fileOutputStream.write(body);
    }
}
