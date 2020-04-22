package com.roncoo.master.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangmn
 * @version 1.0
 * @description
 * @date 2020/3/13 23:47
 */
public class NIO {
    private static final int corePoolSize = 4;
    private static final int maxPoolSize = 4;
    private static final long keepAliveTime = 60L;
    private static final int port = 4343;

    private static void startServer() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                maxPoolSize, keepAliveTime, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        threadPoolExecutor.execute(() -> {
            try (Selector selector = Selector.open()) {
                ServerSocketChannel socketChannel = ServerSocketChannel.open();
                socketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_ACCEPT);
                while (true) {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        try (SocketChannel channel = ((ServerSocketChannel) key.channel()).accept()) {
                            channel.write(Charset.defaultCharset().encode("你好，世界"));
                        }
                        iterator.remove();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private static void startClient(String clientName) {
        // Socket 客户端（接收信息并打印）
        try (Socket cSocket = new Socket(InetAddress.getLocalHost(), port)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));
            bufferedReader.lines().forEach(s -> System.out.println("NIO 客户端" + clientName + "：" + s));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        startServer();
        startClient("s1");
        startClient("s2");
    }
}
