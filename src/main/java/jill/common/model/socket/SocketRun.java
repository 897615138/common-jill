package jill.common.model.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * �׽��ֵ�Runnable
 *
 * @author JIll Wang
 * @date 2020-07-16 18:33
 **/
public class SocketRun implements Runnable {
    /**
     * ˽�л��׽���
     */
    private final Socket socket;

    /**
     * �������캯��
     *
     * @param socket �׽���
     */
    public SocketRun(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        //������������Ϣ
        //����socket����������� try resources
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            //���ϵض�����Ϣ
//            while (true) {
            //���������һ��
            String line = bufferedReader.readLine();
            System.out.println(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
