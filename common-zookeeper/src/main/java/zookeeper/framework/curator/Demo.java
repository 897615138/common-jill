package zookeeper.framework.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * @author JillW
 * @date 2020/10/20
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        String zookeeperConnectionString = "null";
        CuratorFramework client = CuratorFrameworkFactory.newClient(zookeeperConnectionString, retryPolicy);
//client必须调用start方法。 （不再使用时调用close方法）
        client.start();
        byte[] myData={'n','u','l','l'};
        client.create().forPath("/my/path", myData);
        String lockPath="lockPath";
        InterProcessMutex lock = new InterProcessMutex(client, lockPath);
        long maxWait=2000;
        TimeUnit waitUnit=TimeUnit.MILLISECONDS;
        if ( lock.acquire(maxWait, waitUnit) )
        {
            try
            {
                // do some work inside of the critical section here
            }
            finally
            {
                lock.release();
            }
        }

        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter()
        {
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception
            {
                // this callback will get called when you are the leader
                // do whatever leader work you need to and only exit
                // this method when you want to relinquish leadership
            }
        };
        String path="path";
        LeaderSelector selector = new LeaderSelector(client, path, listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();
    }
}