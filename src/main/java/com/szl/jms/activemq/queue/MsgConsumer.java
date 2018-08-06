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
 * @data 2018年8月5日 下午10:11:41
 *
 * 消息消费者
 */
public class MsgConsumer {

	private static final String url ="tcp://127.0.0.1:61616";
	private static final String queueName ="activemq-queue";
	
	public static void main(String[] args) throws JMSException {
		//1、创建ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		//2、创建Connection
		Connection connection = connectionFactory.createConnection();
		//3、启动连接
		connection.start();
		//4、创建会话  false:不使用事务处理，Session.AUTO_ACKNOWLEDGE自动应答
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		//5、创建目标
		Destination destination = session.createQueue(queueName);
		//6、创建消费者
		MessageConsumer consumer = session.createConsumer(destination);
		//7、创建监听器
		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				TextMessage textMessage = (TextMessage) message;
				try {
					System.out.println("接收消息：" + textMessage.getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		//8、关闭连接
//		connection.close();
	}
}
