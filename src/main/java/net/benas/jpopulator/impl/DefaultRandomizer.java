/*
 * The MIT License
 *
 *   Copyright (c) 2013, benas (md.benhassine@gmail.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */

package net.benas.jpopulator.impl;

import org.apache.commons.lang.RandomStringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class is used to generate random value for java built-in types.
 *
 * @author benas (md.benhassine@gmail.com)
 */
final class DefaultRandomizer {

    private static final Random random = new Random();

    /**
     * Generate a random value for the given type.
     *
     * @param type the type for which a random value will be generated
     * @return a random value for the given type or null if the type is not supported
     */
    public static Object getRandomValue(Class type) {

        /*
         * String and Character types
         */
        if (type.equals(String.class)) {
            return RandomStringUtils.randomAlphabetic(10);
        }
        if (type.equals(Character.TYPE) || type.equals(Character.class)) {
            return RandomStringUtils.randomAlphabetic(1).charAt(0);
        }

        /*
         * Boolean type
         */
        if (type.equals(Boolean.TYPE) || type.equals(Boolean.class)) {
            return random.nextBoolean();
        }

        /*
         * Numeric types
         */
        if (type.equals(Byte.TYPE) || type.equals(Byte.class)) {
            return (byte) (random.nextInt());
        }
        if (type.equals(Short.TYPE) || type.equals(Short.class)) {
            return (short) (random.nextInt());
        }
        if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
            return random.nextInt();
        }
        if (type.equals(Long.TYPE) || type.equals(Long.class)) {
            return random.nextLong();
        }
        if (type.equals(Double.TYPE) || type.equals(Double.class)) {
            return random.nextDouble();
        }
        if (type.equals(Float.TYPE) || type.equals(Float.class)) {
            return random.nextFloat();
        }
        if (type.equals(BigInteger.class)) {
            return new BigInteger(Math.abs(random.nextInt()), random);
        }
        if (type.equals(BigDecimal.class)) {
            return new BigDecimal(random.nextDouble());
        }
        if (type.equals(AtomicLong.class)) {
            return new AtomicLong(random.nextLong());
        }
        if (type.equals(AtomicInteger.class)) {
            return new AtomicInteger(random.nextInt());
        }

        /*
         * Date and time types
         */
        if (type.equals(java.util.Date.class)) {
            return new java.util.Date(random.nextLong());
        }
        if (type.equals(java.sql.Date.class)) {
            return new java.sql.Date(random.nextLong());
        }
        if (type.equals(java.sql.Time.class)) {
            return new java.sql.Time(random.nextLong());
        }
        if (type.equals(java.sql.Timestamp.class)) {
            return new java.sql.Timestamp(random.nextLong());
        }
        if (type.equals(Calendar.class)) {
            return Calendar.getInstance();
        }

        /*
         * Enum type
         */
        if (type.isEnum() && type.getEnumConstants().length > 0) {
            Object[] enumConstants = type.getEnumConstants();
            return enumConstants[random.nextInt(enumConstants.length)];
        }

        /*
         * Return null for any unsupported type
         */
        return null;

    }

}
