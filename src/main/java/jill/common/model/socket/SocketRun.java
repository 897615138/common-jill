package jill.common.model.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 套接字的Runnable
 *
 * @author JIll Wang
 * @date 2020-07-16 18:33
 **/
public class SocketRun implements Runnable {
    /**
     * 私有化套接字
     */
    private final Socket socket;

    /**
     * 创建构造函数
     *
     * @param socket 套接字
     */
    public SocketRun(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //服务器接受消息
        //根据socket创建缓冲读流 try resources
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            //不断地读入消息
//            while (true) {
            //缓冲读流读一行
            String line = bufferedReader.readLine();
            System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
