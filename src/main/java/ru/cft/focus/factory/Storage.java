package ru.cft.focus.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.LinkedList;

public class Storage {

    private static final String STORAGE_INFO_MSG = "Amount of resources in storage: ";
    private static final String THREAD_WAIT_MSG = " is waiting";
    private static final String THREAD_WOKE_UP_MSG = " resumes work";
    private static final Marker STORAGE_INFO_MARKER = MarkerManager.getMarker("STORAGE_INFO");
    private static final Marker THREAD_INFO_MARKER = MarkerManager.getMarker("THREAD_INFO");
    private static final Logger log = LogManager.getLogger(Storage.class);

    private final int storageSize;
    private final LinkedList<Resource> resources = new LinkedList<>();

    public Storage(int storageSize) {
        this.storageSize = storageSize;
    }

    public Resource getResource() throws InterruptedException {
        synchronized (resources) {
            while (resources.isEmpty()) {
                log.info(THREAD_INFO_MARKER, Thread.currentThread().getName() + THREAD_WAIT_MSG);
                resources.wait();
                log.info(THREAD_INFO_MARKER, Thread.currentThread().getName() + THREAD_WOKE_UP_MSG);
            }
            log.info(STORAGE_INFO_MARKER, STORAGE_INFO_MSG + (resources.size() - 1));

            resources.notifyAll();
            return resources.remove();
        }
    }

    public void putResource(Resource resource) throws InterruptedException {
        synchronized (resources) {
            while (resources.size() == storageSize) {
                log.info(THREAD_INFO_MARKER, Thread.currentThread().getName() + THREAD_WAIT_MSG);
                resources.wait();
                log.info(THREAD_INFO_MARKER, Thread.currentThread().getName() + THREAD_WOKE_UP_MSG);
            }

            resources.add(resource);
            log.info(STORAGE_INFO_MARKER, STORAGE_INFO_MSG + resources.size());

            resources.notifyAll();
        }
    }

}
