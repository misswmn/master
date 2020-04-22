package com.roncoo.master.io;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author wangmn
 * @version 1.0
 * @description
 * @date 2020/3/13 23:59
 */
public class AIO {
    private static final int port = 4343;

    private static void startServer() {
        Thread sThread = new Thread(() -> {
            AsynchronousChannelGroup group;
            try {
                group = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(4));
                AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group)
                        .bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
                server.accept(null, new CompletionHandler<AsynchronousSocketChannel, AsynchronousSocketChannel>() {
                    @Override
                    public void completed(AsynchronousSocketChannel result, AsynchronousSocketChannel attachment) {
                        server.accept(null, this);
                        try {
                            Future<Integer> future = result.write(Charset.defaultCharset().encode("你好，世界"));
                            future.get();
                            System.out.println("服务端发送时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                            result.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {

                    }
                });
                group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        sThread.start();
    }

    private static void startClient(String clientName) throws Exception {
        // Socket 客户端
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        Future<Void> future = client.connect(new InetSocketAddress(InetAddress.getLocalHost(), port));
        future.get();
        ByteBuffer buffer = ByteBuffer.allocate(100);
        client.read(buffer, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {
                System.out.println("客户端【" + clientName + "】打印：" + new String(buffer.array()));
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(10 * 1000);

    }


    public static void main(String[] args) throws Exception {
        startServer();
        startClient("s1");
        startClient("s2");
        startClient("s3");
    }
}
