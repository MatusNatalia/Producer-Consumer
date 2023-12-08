package ru.cft.focus.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Consumer implements Runnable {

    private static final String CONSUMER_INFO_MSG = "Consumer №%d consumed resource ";
    private static final Marker CONSUMER_MARKER = MarkerManager.getMarker("CONSUMER");
    private static final Logger log = LogManager.getLogger(Consumer.class);

    private final int id;
    private final int consumerTime;
    private final Storage storage;

    public Consumer(int id, int consumerTime, Storage storage) {
        this.id = id;
        this.consumerTime = consumerTime;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(consumerTime);
                Resource resource = storage.getResource();
                log.info(CONSUMER_MARKER, String.format(CONSUMER_INFO_MSG, id) + resource.id());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
