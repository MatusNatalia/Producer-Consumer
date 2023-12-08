package ru.cft.focus.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Producer implements Runnable {

    private static final String PRODUCER_INFO_MSG = "Producer №%d produced resource ";
    private static final Marker PRODUCER_MARKER = MarkerManager.getMarker("PRODUCER");
    private static final Logger log = LogManager.getLogger(Producer.class);

    private final int id;
    private final int producerTime;
    private final Storage storage;
    private int resourceCounter = 0;

    public Producer(int id, int producerTime, Storage storage) {
        this.id = id;
        this.producerTime = producerTime;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(producerTime);

                Resource resource = new Resource(Thread.currentThread().threadId() + "-" + resourceCounter++);
                log.info(PRODUCER_MARKER, String.format(PRODUCER_INFO_MSG, id) + resource.id());

                storage.putResource(resource);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
