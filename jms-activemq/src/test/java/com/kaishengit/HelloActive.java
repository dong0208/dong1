package com.kaishengit;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.junit.Test;

import javax.jms.*;
import java.io.IOException;

public class HelloActive {

    @Test
    public void createMessage() {
        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;
        try {
            //1. 创建连接工厂
            //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
            //设置重试次数
            redeliveryPolicy.setMaximumRedeliveries(3);
            //设置初次重试延迟时间，单位毫秒
            redeliveryPolicy.setInitialRedeliveryDelay(5000);
            //设置每次重试延迟时间，单位毫秒
            redeliveryPolicy.setRedeliveryDelay(5000);
            connectionFactory.setRedeliveryPolicy(redeliveryPolicy);

            //2. 创建连接 并 开启
            connection = connectionFactory.createConnection();
            connection.start();
            //3. 创建Session(是否需要事务，消息的签收模式：AUTO_ACKNOWLEDGE自动签收 CLIENT_ACKNOWLEDGE客户端签收)
            session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
            //4. 创建消息目的地
            Destination destination = session.createQueue("hello-Queue");
            //5. 创建生产者
            producer = session.createProducer(destination);
            // 使用持久模式
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //6. 发送消息
            TextMessage textMessage = session.createTextMessage("Hello,Message-011");
            producer.send(textMessage);
            //提交事务
            session.commit();
        } catch (JMSException ex) {
            ex.printStackTrace();
            try {
                if( session!= null) {
                    session.rollback();
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } finally {
            //7. 释放资源
            close(session,connection,producer);
        }

    }

    @Test
    public void consumerMessage() throws JMSException, IOException {
        //1. 创建连接工厂
        //ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
        //设置重试次数
        redeliveryPolicy.setMaximumRedeliveries(3);
        //设置初次重试延迟时间，单位毫秒
        redeliveryPolicy.setInitialRedeliveryDelay(5000);
        //设置每次重试延迟时间，单位毫秒
        redeliveryPolicy.setRedeliveryDelay(5000);
        connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        //2. 创建连接 并 开启
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3. 创建Session(是否需要事务，消息的签收模式：AUTO_ACKNOWLEDGE自动签收)
        final Session session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
        //4. 创建消息目的地
        Destination destination = session.createQueue("hello-Queue");
        //5. 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //6. 消费消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                //从MQ中取出消息后的业务逻辑
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    if("Hello,Message-011".equals(text)) {
                        throw new RuntimeException("签收消息异常.............");
                    }
                    System.out.println("Message: -----> " + text);
                    //手动签收消息
                    message.acknowledge();
                } catch (Exception e) {
                    e.printStackTrace();

                    try {
                        //触发重试机制
                        session.recover();
                    } catch (JMSException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        System.in.read();

        //7. 释放资源
        consumer.close();
        session.close();
        connection.close();
    }

    @Test
    public void consumerDLQMessage() throws JMSException, IOException {
        //1. 创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
        //2. 创建连接 并 开启
        Connection connection = connectionFactory.createConnection();
        connection.start();
        //3. 创建Session(是否需要事务，消息的签收模式：AUTO_ACKNOWLEDGE自动签收)
        final Session session = connection.createSession(false,Session.CLIENT_ACKNOWLEDGE);
        //4. 创建消息目的地
        Destination destination = session.createQueue("ActiveMQ.DLQ");
        //5. 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        //6. 消费消息
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                //从MQ中取出消息后的业务逻辑
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println("未发出成功的数据：" + text);
                    message.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });

        System.in.read();

        //7. 释放资源
        consumer.close();
        session.close();
        connection.close();
    }


    private void close(Session session,Connection connection,MessageProducer producer) {
        if(producer != null) {
            try {
                producer.close();
            } catch (JMSException e) {
                e.printStackTrace();
            } finally {
                if(session != null) {
                    try {
                        session.close();
                    } catch (JMSException e) {
                        e.printStackTrace();
                    } finally {
                        if(connection != null) {
                            try {
                                connection.close();
                            } catch (JMSException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

    }
}