package org.mypackage;

import java.text.ParseException;

public class RegisterHostReset {

    public static void main(final String[] args) throws ParseException {
        MyCollection c = new MyCollection();
        c.dropBeCareful();
        c.createCollection();
    }

}
