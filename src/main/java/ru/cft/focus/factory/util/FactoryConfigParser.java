package ru.cft.focus.factory.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfigParser {
    private static final String CONFIG_PATH = "task5\\src\\main\\resources\\factory_config.properties";
    private static final String STORAGE_SIZE_PROPERTY = "storage_size";
    private static final String PRODUCER_COUNT_PROPERTY = "producer_count";
    private static final String PRODUCER_TIME_PROPERTY = "producer_time";
    private static final String CONSUMER_COUNT_PROPERTY = "consumer_count";
    private static final String CONSUMER_TIME_PROPERTY = "consumer_time";

    private static final String DEFAULT_STORAGE_SIZE = "10";
    private static final String DEFAULT_PRODUCER_COUNT = "1";
    private static final String DEFAULT_PRODUCER_TIME = "1000";
    private static final String DEFAULT_CONSUMER_COUNT = "1";
    private static final String DEFAULT_CONSUMER_TIME = "1000";

    private Properties properties = new Properties();

    public FactoryConfigParser() throws FactoryConfigParserException {
        try {
            properties.load(new FileInputStream(CONFIG_PATH));
        } catch (IOException e) {
            throw new FactoryConfigParserException(e.getMessage());
        }
    }

    public int getStorageSize() throws FactoryConfigParserException {
        String storageSizeString = properties.getProperty(STORAGE_SIZE_PROPERTY, DEFAULT_STORAGE_SIZE);
        try {
            return Integer.parseInt(storageSizeString);
        } catch (NumberFormatException e) {
            throw new FactoryConfigParserException(e.getMessage());
        }
    }

    public int getProducerCount() throws FactoryConfigParserException {
        String producerCountString = properties.getProperty(PRODUCER_COUNT_PROPERTY, DEFAULT_PRODUCER_COUNT);
        try {
            return Integer.parseInt(producerCountString);
        } catch (NumberFormatException e) {
            throw new FactoryConfigParserException(e.getMessage());
        }
    }

    public int getProducerTime() throws FactoryConfigParserException {
        String producerTimeString = properties.getProperty(PRODUCER_TIME_PROPERTY, DEFAULT_PRODUCER_TIME);
        try {
            return Integer.parseInt(producerTimeString);
        } catch (NumberFormatException e) {
            throw new FactoryConfigParserException(e.getMessage());
        }
    }

    public int getConsumerCount() throws FactoryConfigParserException {
        String consumerCountString = properties.getProperty(CONSUMER_COUNT_PROPERTY, DEFAULT_CONSUMER_COUNT);
        try {
            return Integer.parseInt(consumerCountString);
        } catch (NumberFormatException e) {
            throw new FactoryConfigParserException(e.getMessage());
        }
    }

    public int getConsumerTime() throws FactoryConfigParserException {
        String consumerTimeString = properties.getProperty(CONSUMER_TIME_PROPERTY, DEFAULT_CONSUMER_TIME);
        try {
            return Integer.parseInt(consumerTimeString);
        } catch (NumberFormatException e) {
            throw new FactoryConfigParserException(e.getMessage());
        }
    }

}
