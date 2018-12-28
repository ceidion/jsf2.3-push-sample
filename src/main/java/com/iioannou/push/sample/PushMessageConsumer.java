package com.iioannou.push.sample;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * @author iioannou
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "TestQueue"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")})
public class PushMessageConsumer implements MessageListener {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Inject
    @Push(channel = "globalNotifications")
    PushContext globalNot;

    @Inject
    @Push(channel = "userNotifications")
    PushContext userNot;


    @Override
    public void onMessage(Message message) {

        try {
            PushMessage msg = message.getBody(PushMessage.class);
            logger.info("Received message " + msg.getPayload() + " for user " + msg.getUser());

            Map<String, String> payload = new HashMap<>();
            payload.put("not", msg.getPayload());

            if (msg.getUser().length() > 0) {
                userNot.send(payload, msg.getUser());
            } else {
                globalNot.send(payload);
            }

        } catch (Exception e) {
            logger.info("Error" + e);
        }
    }

}
