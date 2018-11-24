package com.cit.notifier.service;

import com.cit.notifier.model.MqttPublish;
import com.cit.notifier.model.IMqttPublish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:application.properties")
public class NotifierService {

    @Autowired
    public NotifierService(){
        this.publisher = MqttPublish.createInstance();
    }

    @Value("${mqtt.broker.host}")
    public static String mqttBroker = "tcp://iot.eclipse.org";

    @Value("${mqtt.topic.name}")
    public static String mqttTopic = "validation.alerts";

    @Value("${message.publish.content}")
    public static String strMessage = "Message from our MQTT Publisher";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private IMqttPublish publisher;

    @Autowired
    public void publish(){
        publisher.connect(mqttBroker);
        publisher.publish(mqttTopic,strMessage);
        if (publisher.isConnected()) {
            publisher.terminate();
        }
    }
}