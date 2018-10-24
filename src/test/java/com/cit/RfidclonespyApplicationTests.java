package com.cit;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.apache.cassandra.exceptions.ConfigurationException;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RfidclonespyApplicationTests {

	@BeforeClass
	public static void startCassandraEmbedded() {
		try {
			EmbeddedCassandraServerHelper.startEmbeddedCassandra();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		Cluster cluster = Cluster.builder()
				.addContactPoints("127.0.0.1").withPort(9142).build();
		Session session = cluster.connect();
	}

	@AfterClass
	public static void stopCassandraEmbedded() {
		EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
	}

	@Test
	public void contextLoads() {
	}

}
