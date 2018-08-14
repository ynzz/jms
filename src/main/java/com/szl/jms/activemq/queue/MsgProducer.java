package com.szl.jms.activemq.queue;

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
 * @data 2018年8月5日 下午9:50:06
 *
 * 消息生产者
 */
public class MsgProducer {

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
		//6、创建生产者
		MessageProducer producer = session.createProducer(destination);
		for (int i = 0; i < 100; i++) {
			//7、创建消息
			TextMessage textMessage = session.createTextMessage("test " + i);
			//8、发布消息
			producer.send(textMessage);
			System.out.println("发送消息：" + textMessage.getText());
		}
		//9、关闭连接
		connection.close();
	}
}





