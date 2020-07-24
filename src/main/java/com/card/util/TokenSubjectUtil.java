package com.card.util;

import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;

public class TokenSubjectUtil {
    private static Map<String, Subject> map = new HashMap<>();

    public static void saveSubject(String key, Subject subject) {
        map.put(key, subject);
    }

    public static Subject getSubject(String key) {
        return map.get(key);
    }
}
