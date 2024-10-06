package project.core.util;

public class Logger {

    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";

    public static void log(String message) {
        System.out.println(GREEN + "[HelpDesk] [" + java.time.LocalTime.now() + "] " + message + RESET);
    }

    public static void warn(String message) {
        System.out.println(YELLOW + "[HelpDesk] [" + java.time.LocalTime.now() + "] [WARN] " + message + RESET);
    }

    public static void error(String message) {
        System.out.println(RED + "[HelpDesk] [" + java.time.LocalTime.now() + "] [ERROR] " + message + RESET);
    }
}