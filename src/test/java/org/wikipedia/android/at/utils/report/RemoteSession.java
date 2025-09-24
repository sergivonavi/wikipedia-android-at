package org.wikipedia.android.at.utils.report;

public class RemoteSession {
    private static final ThreadLocal<String> sessionName = new ThreadLocal<>();

    public static void set(String name) {
        sessionName.set(name);
    }

    public static String get() {
        return sessionName.get();
    }

    public static void clear() {
        sessionName.remove();
    }
}
