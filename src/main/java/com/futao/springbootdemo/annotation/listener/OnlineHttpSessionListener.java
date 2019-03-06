package com.futao.springbootdemo.annotation.listener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * HttpSession监听器，统计在线人数
 *
 * @author futao
 * Created on 2018/10/11.
 */
@Component
@WebListener("onlinePeopleQuantity")
public class OnlineHttpSessionListener implements HttpSessionListener {
    /**
     * 在线人数量
     */
    private AtomicInteger onlinePeopleQuantity = new AtomicInteger();
    /**
     * 人数缩放比例
     */
    @Value("${onlinePeopleQuantityScale}")
    private int scale;

    /**
     * Notification that a session was created.
     *
     * @param se the notification event
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        onlinePeopleQuantity.addAndGet(scale);
    }

    /**
     * Notification that a session is about to be invalidated.
     *
     * @param se the notification event
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        onlinePeopleQuantity.addAndGet(-scale);
    }

    /**
     * 获取在线人数
     *
     * @return 在线人数
     */
    public AtomicInteger getOnlinePeopleQuantity() {
        return onlinePeopleQuantity;
    }
}
