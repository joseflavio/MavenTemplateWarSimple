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
    public void testEmptyCollection() {
        assertEquals(helloServlet.getText(), "Hello world! I'm alive!");
        System.out.println("@Test - testEmptyCollection");
    }

}
