package com.iioannou.push.sample;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * @author iioannou
 */
public class PushMessageProducer {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Resource(mappedName = "java:/ConnectionFactory")
    ConnectionFactory factory;

    @Resource(mappedName = "java:/jms/queue/TestQueue")
    Queue testQueue;

    public void sendMessage() {
        Connection connection = null;
        Session session = null;

        try {
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer producer = session.createProducer(testQueue);
            String id = UUID.randomUUID().toString();
            Message message = session.createObjectMessage(new PushMessage(id, ""));
            logger.info("Sending message with id" + id);
            producer.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    logger.info("Error" + e);
                }
            }
        }
    }

    public void sendUserMessage(String user) {
        Connection connection = null;
        Session session = null;

        try {
            connection = factory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer producer = session.createProducer(testQueue);
            String id = UUID.randomUUID().toString();
            Message message = session.createObjectMessage(new PushMessage(id, user));
            logger.info("Sending message with id" + id + " to user" + user);
            producer.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    logger.info("Error" + e);
                }
            }
        }
    }
}
