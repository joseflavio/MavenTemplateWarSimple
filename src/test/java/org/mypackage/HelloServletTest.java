package org.mypackage;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class HelloServletTest {

    private HelloServlet helloServlet = null;

    @Before
    public void prepare() {
        helloServlet = new HelloServlet();
    }

    @Test
    public void testDecodingHttpCredentials() {
        assertEquals(helloServlet.decodeHttpCredentials("Basic YW55dXNlcm5hbWU6Y2V6YW1v"), "anyusername:cezamo");
        System.out.println("@Test - testEmptyCollection");
    }

}
