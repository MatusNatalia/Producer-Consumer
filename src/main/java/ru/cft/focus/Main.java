package ru.cft.focus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.cft.focus.factory.Factory;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try{
            Factory factory = new Factory();
            factory.start();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}