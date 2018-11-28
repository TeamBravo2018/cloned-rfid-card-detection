package com.cit.notifier;

import com.cit.notifier.model.IMqttPublish;
import com.cit.notifier.model.MqttPublish;
import com.cit.notifier.service.NotifierService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class NotifierApplicationTests {

    @Value("${mqtt.broker.host}")
    public static String mqttBroker = "tcp://iot.eclipse.org:1883";//"tcp://ec2-52-91-20-33.compute-1.amazonaws.com:1883";//

    @Autowired
    public NotifierService notifier;

	@Test
	public void contextLoads() {
	}

	@Test
    public void serviceTest(){
	    notifier.publish("test");
    }

    public void publishTest(Integer num) {
        String context = num.toString();
	    String broker = mqttBroker;
        System.out.println("thread ");
        IMqttPublish test = new MqttPublish("*Testing*");
        test.connect(broker);
        ((MqttPublish) test).setUserContext(context);
        try {
            test.publish("JohnTest","Hello World");
            Thread.sleep(5000);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (test.isConnected()) {
                test.terminate();
            }
        }
    }

    @Test
    public void multiPublishTest(){
        for (int j=0; j<30;j++) {
            serviceTest();
        }
        try {
            Thread.sleep(10000);
        } catch (Exception e){
            System.out.println("Could not sleep main");
        }
    }

    public class MultiThread extends Thread {
        public void run() {
            try {
                serviceTest();
                Thread.sleep(2000);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    @Test
    public void multiPublishTestDelay(){
        try{
            multiPublishTest();
            Thread.sleep(50000);
            multiPublishTest();
        }catch(Exception e){
            System.out.println("Could not sleep");
        }
    }
}
