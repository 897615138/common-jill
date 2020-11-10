package jill.common.model.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * �ͻ���
 *
 * @author JIll Wang
 * @date 2020-07-16 18:41
 **/
public class ClientDemo {
    public static void start(int port) {
        //�����ͻ��˶˿�
        try {
            OutputStream outputStream;
            try (Socket socket = new Socket("localhost", port)) {
                //�����ͻ��˵������
                outputStream = socket.getOutputStream();
            }
            //����Scanner
            Scanner scanner = new Scanner(System.in);
            //��������
            System.out.println("����������");
            String name = scanner.nextLine();
            //һֱ�ж��Ƿ����µ���������
//            while (true) {
            System.out.println("�����ı�");
            String line = name + ":" + scanner.nextLine();
            //�����ֽ�ͨ��socket�����������
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
