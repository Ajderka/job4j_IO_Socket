package io.logger;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        BasicConfigurator.configure();
        short nameLength = 5;
        int age = 34;
        byte bloodType = 1;
        long salary = 23888499L;
        char shift = 'A';
        float weight = 84.3F;
        double growth = 180.5;
        boolean man = true;

        LOG.debug("User info name length : {}, age : {}, bloodType : {}, salary : {}, shift : {}, weight : {}, growth = {}, man = {}", nameLength, age, bloodType, salary, shift, weight, growth, man);
    }
}
