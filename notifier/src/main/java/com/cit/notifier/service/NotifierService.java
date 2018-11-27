package com.cit.notifier.service;

import com.cit.notifier.model.MqttPublish;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Base64.Encoder;

@Service
@Async
@Scope(value="prototype", proxyMode= ScopedProxyMode.TARGET_CLASS)
@PropertySource("classpath:application.properties")
public class NotifierService {

    @Autowired
    public NotifierService(){
        this.publisher = MqttPublish.createInstance();
    }

    @Value("${mqtt.broker.host}")
    public static String mqttBroker = "tcp://iot.eclipse.org:1883";//"tcp://ec2-52-91-20-33.compute-1.amazonaws.com:1883";//

    @Value("${mqtt.topic.name}")
    public static String mqttTopic = "validation.alerts.bravo";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private MqttPublish publisher;

    public void publish(String alert){
        generateClientId();
        //this.publisher.connect(mqttBroker);
        this.publisher.process(mqttBroker,mqttTopic,alert);
        if (this.publisher.isConnected()) {
            this.publisher.terminate();
        }
    }

    private void generateClientId(){
        String generatedString = generateSafeToken();
        log.info(String.format("new client ID is: %s",generatedString));
        this.publisher.setClientId(generatedString);
    }

    private String generateSafeToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[15];
        random.nextBytes(bytes);
        Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);
        return token;
    }
}