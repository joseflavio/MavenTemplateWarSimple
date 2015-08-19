package org.mypackage;

import static java.util.Arrays.asList;

import java.text.ParseException;
import java.util.Date;

import org.bson.Document;
import org.mypackage.MyCollection.SimpleFunction;

public class RegisterHostUsecase {

    public static void main(final String[] args) throws ParseException {

        Document d = new Document();
        d.append("hostname", "mypc001");
        d.append("username", "joseflavio");
        d.append("insertTime", new Date());
        d.append("hostcode", "ABCDEFABCDEF");
        d.append("properties", asList(new Document("model", "XYZ"), new Document("serial", "123456")));

        // Adding a new document
        MyCollection myCollection = new MyCollection();
        myCollection.insert(d);

        // Printing all documents of the collection
        myCollection.applyAll(new SimpleFunction<Document>() {
            private int i = 0;
            @Override
            public void apply(final Document input) {
                System.out.println(i + " - " + input.toJson());
                ++i;
            }
        });

    }

}
