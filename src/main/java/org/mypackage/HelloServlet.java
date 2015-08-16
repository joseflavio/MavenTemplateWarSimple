package org.mypackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;

public class HelloServlet extends HttpServlet {

    private static final long serialVersionUID = -1296868313209653053L;

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<h1>Hello GET method! I'm alive!</h1>");
    }

    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
            IOException {

        String authorizationBase64 = request.getHeader("Authorization");
        String credentials = decodeHttpCredentials(authorizationBase64);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("Hello POST method! I'm alive! credentials 2=" + credentials);

    }

    String decodeHttpCredentials(String authorization) {
        if (authorization == null) {
            return null;
        }
        if (authorization.startsWith("Basic ") == false) {
            return null;
        }
        String a = authorization.substring("Basic ".length());
        String credentials = new String(Base64.decodeBase64(a), StandardCharsets.UTF_8);
        return credentials;
    }

}
