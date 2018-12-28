package com.iioannou.push.sample;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * @author iioannou
 */

@Named
@ViewScoped
public class Controller implements Serializable {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private String notification;
    private String notifiedUser;

    @Inject
    HttpServletRequest servletRequest;

    @Inject
    private transient PushMessageProducer messageProducer;


    public Controller() {
        super();
    }

    public void sendGlobalMessage() {
        logger.info("Sending global message");
        messageProducer.sendMessage();
    }

    public void sendPrivateMessage() {
        logger.info("Sending private message to user " + notifiedUser);
        messageProducer.sendUserMessage(notifiedUser);
    }

    public void receiveNotification() {
        logger.info("Client side notification fired.");
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();

        notification = req.getParameter("not");
        logger.info("Notification payload" + notification);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Notification", notification));
    }

    public String getRemoteUser() {
        return servletRequest.getRemoteUser();
    }


    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getNotifiedUser() {
        return notifiedUser;
    }

    public void setNotifiedUser(String notifiedUser) {
        this.notifiedUser = notifiedUser;
    }
}
