package com.lyloou.common.util;

import java.io.UnsupportedEncodingException;

/**
 * https://github.com/alibaba/sca-best-practice/blob/master/codeless-framework/codeless-core/src/main/java/com/alibaba/codeless/framework/core/utils/StringUtils.java
 */
public interface StringUtil {

    static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

    static boolean isEmpty(String[] s) {
        return s == null || s.length == 0;
    }

    static byte[] toBytes(String s) {
        try {
            return s.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    static String fromBytes(byte[] bytes) {
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    static boolean isNumeric(final CharSequence cs) {
        if (isEmpty(cs)) {
            return false;
        }
        final int sz = cs.length();
        for (int i = 0; i < sz; i++) {
            //noinspection PointlessBooleanExpression
            if (Character.isDigit(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    static Object toPrimitives(Class<?> clazz, String s) {
        if (clazz == String.class) {
            return s;
        } else if (clazz == Integer.class) {
            return Integer.valueOf(s);
        } else if (clazz == Long.class) {
            return Long.valueOf(s);
        } else if (clazz == Float.class) {
            return Float.valueOf(s);
        } else if (clazz == Short.class) {
            return Short.valueOf(s);
        } else if (clazz == Double.class) {
            return Double.valueOf(s);
        } else {
            throw new IllegalArgumentException("Unsupport primitive type " + clazz.getName());
        }

    }

}