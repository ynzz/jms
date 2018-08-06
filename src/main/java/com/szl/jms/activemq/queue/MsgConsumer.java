package com.szl.jms.activemq.queue;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author szl
 * @data 2018��8��5�� ����10:11:41
 *
 * ��Ϣ������
 */
public class MsgConsumer {

	private static final String url ="tcp://127.0.0.1:61616";
	private static final String queueName ="activemq-queue";
	
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
		Destination destination = session.createQueue(queueName);
		//6������������
		MessageConsumer consumer = session.createConsumer(destination);
		//7������������
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println("������Ϣ��" + textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		//8���ر�����
//		connection.close();
	}
}
