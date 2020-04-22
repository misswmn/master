package com.roncoo.master.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author wangmn
 * @version 1.0
 * @description
 * @date 2020/3/13 21:52
 */
public class BIO {
    private static final int port = 4343;

    private static void startServer() {
        Thread sThread = new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                while (true) {
                    Socket socket = serverSocket.accept();
                    Thread sHandlerThread = new Thread(() -> {
                        try (PrintWriter printWriter = new PrintWriter(socket.getOutputStream())) {
                            printWriter.write("hello world!");
                            printWriter.flush();
                            TimeUnit.SECONDS.sleep(2);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    sHandlerThread.start();
                    System.out.println("启动了一个线程......................");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        sThread.start();
    }


    private static void startClient(String clientName) {
        try (Socket socket = new Socket(InetAddress.getLocalHost(), port)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedReader.lines().forEach(s -> System.out.println("客户端" + clientName + "收到:" + s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        startServer();
        startClient("s1");
        startClient("s2");
        startClient("s3");
        startClient("s4");
        startClient("s5");
        startClient("s6");
        startClient("s7");
    }
}
