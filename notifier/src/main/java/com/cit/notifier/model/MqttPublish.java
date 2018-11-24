package com.cit.notifier.model;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;


public class MqttPublish implements IMqttPublish {

    /**
     * Member Vars
     */
    private String topic;
    private static final String ENCODING = "UTF-8";
    private String name;
    private String clientId;
    private MqttAsyncClient client;
    private MemoryPersistence memoryPersistence;
    private IMqttToken connectToken;
    private String userContext = "default";
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    /**
     * Getters/setters
     */
    public String getUserContext() {
        return userContext;
    }

    public void setUserContext(String userContext) {
        this.userContext = userContext;
    }

    /**
     * Constructors
     */
    public MqttPublish() { }
    public MqttPublish(String name) { this.name = name; }

    /**
     * Factory method - create an instance of a publisher.
     *
     * @return - new instance.
     */
    public static MqttPublish createInstance()
    {
        return new MqttPublish();
    }

    /**
     * Initialize a connection with the given MQTT Broker
     */
    @Override
    public void connect(String broker)
    {
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            memoryPersistence = new MemoryPersistence();
            clientId = MqttAsyncClient.generateClientId();
            client = new MqttAsyncClient(broker, clientId, memoryPersistence);
            client.setCallback(this);
            connectToken = client.connect(options, null, this);
            connectToken.waitForCompletion();
        } catch (MqttException e) {
            log.error("Threw an Exception in MqttPublish::connect, full stack trace follows:", e);
        }
    }

    @Override
    public boolean isConnected() {
        return (client != null) && (client.isConnected());
    }

    @Override
    public void connectionLost(Throwable cause) {
        // The MQTT client lost the connection
        log.error("Threw an Exception in MqttPublish::connectionLost, full stack trace follows:",cause);
    }

    @Override
    public void onSuccess(IMqttToken asyncActionToken) {
        if (asyncActionToken.equals(connectToken)) {
            log.info("Connection made");
        }
    }

    /**
     * Publish a message on the given topic to the MQTT Broker
     *
     * @param strMqttTopic MQTT Topic to publish to
     * @param strMessage Message to publish
     */
   @Override
    public MessageActionListener publish(final String strMqttTopic, final String strMessage)
    {
        byte[] bytesStrMessage;
        this.topic = strMqttTopic;

        try {
            bytesStrMessage = strMessage.getBytes(ENCODING);
            MqttMessage message;
            message = new MqttMessage(bytesStrMessage);
            MessageActionListener actionListener = new MessageActionListener(strMqttTopic, strMessage, userContext);
            client.publish(strMqttTopic, message, userContext,	actionListener);
            return actionListener;
        } catch (UnsupportedEncodingException e) {
            log.error("Threw an UnsupportedEncodingException in MqttPublish::publish, full stack trace follows:",e);
            return null;
        } catch (MqttException e) {
            log.error("Threw an MqttException in MqttPublish::publish, full stack trace follows:",e);
            return null;
        }
    }

    /**
     * Terminate the MQTT client connection.
     */
    @Override
    public void terminate()
    {
        try {
            this.client.disconnect();
        }
        catch (MqttException e) {
            log.error("Threw an MqttException in MqttPublish::terminate, full stack trace follows:",e);
        }
    }

    @Override
    public void onFailure(IMqttToken asyncActionToken, Throwable exception)
    {
        log.error("Threw an Exception in MqttPublish::onFailure, full stack trace follows:",exception);
    }

    /**
     * MessageArrived handle broker communicaions
     *
     * @param topic the topic for the MQTT message
     * @param message the message that will be sent
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception
    {
        if (!topic.equals(this.topic)) {
            return;
        }
        String messageText = new String(message.getPayload(), ENCODING);
        log.info("%s received %s: %s", name, topic, messageText);
        String[] keyValue = messageText.split(":");
        if (keyValue.length != 3) {
            return;
        }
    }

    /**
     * Called if delivery is verified
     *
     * @param token deliverytoken, verification from broker
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        log.info("delivery complete");
    }

}
