package com.szl.jms.activemq.topic;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author szl
 * @data 2018��8��5�� ����10:27:36
 *
 */
public class MsgProducer {

	private static final String url ="tcp://127.0.0.1:61616";
	private static final String topicName ="activemq-topic";
	
	public static void main(String[] args) throws JMSException {
		//1������ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		//2������Connection
		Connection connection = connectionFactory.createConnection();
		//3����������
		connection.start();
		//4�������Ự  false:��ʹ��������Session.AUTO_ACKNOWLEDGE�Զ�Ӧ��
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5������Ŀ��
		Destination destination = session.createTopic(topicName);
		//6������������
		MessageProducer producer = session.createProducer(destination);
		for (int i = 0; i < 100; i++) {
			//7��������Ϣ
			TextMessage textMessage = session.createTextMessage("test " + i);
			//8��������Ϣ
			producer.send(textMessage);
			System.out.println("������Ϣ��" + textMessage.getText());
		}
		//9���ر�����
		connection.close();
	}
}
