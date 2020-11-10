package jill.common.model.socket;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * ��������
 *
 * @author JIll Wang
 * @date 2020-07-16 18:24
 **/
public class MainServer {

    public static void start(int port) {
        //�޶������̳߳�
        ScheduledThreadPoolExecutor executorService = new ScheduledThreadPoolExecutor(10,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        //����������socket
        try {
            //������ǰ��9000�˿�
            Socket socket;
            try (ServerSocket serverSocket = new ServerSocket(port)) {
                System.out.println("�˿ںţ�" + port);
                //һֱ�����źŽ����ж�
//            while (true) {
                //�˿ڽ��յ����׽���
                socket = serverSocket.accept();
            }
            //�½��߳�
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
