package net.coille.imt.middleware.common.logger;

public class StdLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
