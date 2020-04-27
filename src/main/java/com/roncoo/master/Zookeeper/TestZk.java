package com.roncoo.master.Zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangmn
 * @version 1.0
 * @description
 * @date 2020/4/24 14:32
 */
public class TestZk {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestZk.class);
    private static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);
    private static ZooKeeper ZOO_KEEPER;

    public static void main(String[] args) {
        try {
            String connectString = "192.168.199.171:2181,192.168.199.167:2181,192.168.199.199:2181";
            ZOO_KEEPER = new ZooKeeper(connectString, 1000, new ConnectWatcher());
            long start = System.currentTimeMillis();
            COUNT_DOWN_LATCH.await();
            long end = System.currentTimeMillis();
            System.out.println("用时:" + (end-start));
            LOGGER.info(">>>>>>>>>>>>>> 连接上了zookeeper");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOGGER.info("finally ...");
        }
    }

    public static class ConnectWatcher implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            Event.KeeperState keeperState = event.getState();
            switch (keeperState) {
                case Disconnected:
                    LOGGER.info("Disconnected......");
                    break;
                case SyncConnected:
                    LOGGER.info("SyncConnected......");
                    COUNT_DOWN_LATCH.countDown();
                    break;
                case AuthFailed:
                    LOGGER.info("AuthFailed......");
                    break;
                case ConnectedReadOnly:
                    LOGGER.info("ConnectedReadOnly......");
                    break;
                case SaslAuthenticated:
                    LOGGER.info("SaslAuthenticated......");
                    break;
                case Expired:
                    LOGGER.info("Expired......");
                    break;
                case Closed:
                    LOGGER.info("Closed......");
                    break;
            }
        }
    }

    public static ZooKeeper getInstance() {
        return ZOO_KEEPER;
    }
}
