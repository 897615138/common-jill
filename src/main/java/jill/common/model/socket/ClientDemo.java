package jill.common.model.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 *
 * @author JIll Wang
 * @date 2020-07-16 18:41
 **/
public class ClientDemo {
    public static void start(int port) {
        //创建客户端端口
        try {
            OutputStream outputStream;
            try (Socket socket = new Socket("localhost", port)) {
                //创建客户端的输出流
                outputStream = socket.getOutputStream();
            }
            //创建Scanner
            Scanner scanner = new Scanner(System.in);
            //输入名称
            System.out.println("请输入名称");
            String name = scanner.nextLine();
            //一直判断是否有新的内容输入
//            while (true) {
            System.out.println("输入文本");
            String line = name + ":" + scanner.nextLine();
            //按照字节通过socket的输出流传输
            outputStream.write(line.getBytes());
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        start(9000);
    }
}
