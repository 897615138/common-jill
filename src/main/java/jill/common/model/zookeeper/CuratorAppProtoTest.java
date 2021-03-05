package jill.common.model.zookeeper;

import org.apache.curator.test.TestingServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * @author Jill W
 * @date 2020/12/25
 */
public class CuratorAppProtoTest {
    private TestingServer server;

    @BeforeClass
    public void setUp() throws Exception {
        server = new TestingServer();
        server.start();
    }
    @AfterClass
    public void tearDown() throws IOException {
        server.stop();
    }

    @Test
    public void testSetAndGetData() {
//        CuratorApp app = new CuratorApp();
//        String payload = System.currentTimeMillis() + "";
//        String result = app.setAndGetData(server.getConnectString(), payload);
//        assertEquals(result, payload);
    }

    @Test
    public void testWatch() {
//        CuratorApp app = new CuratorApp();
//        app.watch(server.getConnectString());
    }
}
