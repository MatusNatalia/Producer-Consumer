package ru.cft.focus.factory;

import ru.cft.focus.factory.util.FactoryConfigParser;
import ru.cft.focus.factory.util.FactoryConfigParserException;

import java.util.ArrayList;
import java.util.List;

public class Factory {

    private static final String PRODUCER_NAME = "producer-";
    private static final String CONSUMER_NAME = "consumer-";

    private final List<Thread> producers;
    private final List<Thread> consumers;

    public Factory() throws FactoryConfigParserException {
        FactoryConfigParser parser = new FactoryConfigParser();
        Storage storage = new Storage(parser.getStorageSize());
        producers = new ArrayList<>(parser.getProducerCount());
        int idCounter = 1;
        for (int i = 0; i < parser.getProducerCount(); i++) {
            Thread thread = new Thread(new Producer(idCounter, parser.getProducerTime(), storage));
            thread.setName(PRODUCER_NAME + idCounter++);
            producers.add(thread);
        }
        idCounter = 1;
        consumers = new ArrayList<>(parser.getConsumerCount());
        for (int i = 0; i < parser.getConsumerCount(); i++) {
            Thread thread = new Thread(new Consumer(idCounter, parser.getConsumerTime(), storage));
            thread.setName(CONSUMER_NAME + idCounter++);
            consumers.add(thread);
        }
    }

    public void start() {
        producers.forEach(Thread::start);
        consumers.forEach(Thread::start);
    }

}
