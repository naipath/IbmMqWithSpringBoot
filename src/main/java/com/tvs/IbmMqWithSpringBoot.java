package com.tvs;

import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;

@SpringBootApplication
public class IbmMqWithSpringBoot implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(IbmMqWithSpringBoot.class, args);
    }

    @Value("${ibm.mq.queueManagerName}")
    private String queueManagerName;

    @Value("${ibm.mq.queueName}")
    private String queueName;

    private final JmsTemplate jmsTemplate;

    public IbmMqWithSpringBoot(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    /**
     * Setting up the JmsTemplate with the configured MqQueueConnectionFactory
     */
    @Bean
    public JmsTemplate jmsTemplate() throws JMSException {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(ibmMQConnectionFactory());
        return jmsTemplate;
    }

    /**
     * Setting up the MQQueueConnectionFactory
     */
    @Bean
    public MQQueueConnectionFactory ibmMQConnectionFactory() throws JMSException {
        MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
        mqQueueConnectionFactory.setQueueManager(queueManagerName);
        mqQueueConnectionFactory.setTransportType(0);
        return mqQueueConnectionFactory;
    }


    /**
     * Put a message on the queue to ensure that it works
     */
    @Override
    public void run(String... strings) throws Exception {
        MQQueue queue = new MQQueue(queueName);
        jmsTemplate.send(queue, session -> session.createTextMessage("A message you want to send"));
    }
}
