package jill.common.model.socket;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 主服务器
 *
 * @author JIll Wang
 * @date 2020-07-16 18:24
 **/
public class MainServer {

    public static void start(int port) {
        //限定长度线程池
        ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(10,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        //创建服务器socket
        try {
            //监听当前的9000端口
            Socket socket;
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("端口号：" + port);
                //一直进行信号接收判断
//            while (true) {
                //端口接收到的套接字
                socket = serverSocket.accept();
            }
            //新建线程
            executorService.submit(new SocketRun(socket));
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        start(9000);

    }
}
