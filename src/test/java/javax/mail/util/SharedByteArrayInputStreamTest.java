/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package javax.mail.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.internet.SharedInputStream;

import junit.framework.TestCase;

/**
 * @version $Rev: 467553 $ $Date: 2006-10-25 06:01:51 +0200 (Mi, 25. Okt 2006) $
 */
public class SharedByteArrayInputStreamTest extends TestCase {
    private String testString = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private byte[] testData = testString.getBytes();



    public SharedByteArrayInputStreamTest(String arg0) {
        super(arg0);
    }

    public void testInput() throws Exception {
        SharedByteArrayInputStream in = new SharedByteArrayInputStream(testData);

        assertEquals(in.read(), '0');

        assertEquals(in.getPosition(), 1);

        byte[] bytes = new byte[10];

        assertEquals(in.read(bytes), 10);
        assertEquals(new String(bytes), "123456789a");
        assertEquals(in.getPosition(), 11);

        assertEquals(in.read(bytes, 5, 5), 5);
        assertEquals(new String(bytes), "12345bcdef");
        assertEquals(in.getPosition(), 16);

        assertEquals(in.skip(5), 5);
        assertEquals(in.getPosition(), 21);
        assertEquals(in.read(), 'l');

        while (in.read() != 'Z') {
        }

        assertEquals(in.read(), -1);
    }


    public void testNewStream() throws Exception {
        SharedByteArrayInputStream in = new SharedByteArrayInputStream(testData);

        SharedByteArrayInputStream sub = (SharedByteArrayInputStream)in.newStream(10, 10 + 26);

        assertEquals(sub.getPosition(), 0);

        assertEquals(in.read(), '0');
        assertEquals(sub.read(), 'a');

        sub.skip(1);
        assertEquals(sub.getPosition(), 2);

        while (sub.read() != 'z') {
        }

        assertEquals(sub.read(), -1);

        SharedByteArrayInputStream sub2 = (SharedByteArrayInputStream)sub.newStream(5, 10);

        assertEquals(sub2.getPosition(), 0);
        assertEquals(sub2.read(), 'f');
    }
}
