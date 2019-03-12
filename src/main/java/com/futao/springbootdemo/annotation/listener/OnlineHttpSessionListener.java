package com.futao.springbootdemo.annotation.listener;

import com.futao.springbootdemo.model.system.SystemConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    @Resource
    private SystemConfig systemConfig;

    /**
     * 在线人数量
     */
    private AtomicInteger onlinePeopleQuantity = new AtomicInteger();

    /**
     * Notification that a session was created.
     *
     * @param se the notification event
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        onlinePeopleQuantity.addAndGet(systemConfig.getOnlinePeopleQuantityScale());
    }

    /**
     * Notification that a session is about to be invalidated.
     *
     * @param se the notification event
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        onlinePeopleQuantity.addAndGet(-systemConfig.getOnlinePeopleQuantityScale());
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
