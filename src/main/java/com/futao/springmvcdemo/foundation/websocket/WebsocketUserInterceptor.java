//package com.futao.springmvcdemo.foundation.websocket;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.messaging.simp.stomp.StompCommand;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
//import org.springframework.messaging.simp.user.SimpUserRegistry;
//import org.springframework.messaging.support.ChannelInterceptor;
//import org.springframework.messaging.support.MessageHeaderAccessor;
//
//import java.util.LinkedList;
//import java.util.Map;
//
///**
// * @author futao
// * Created on 2019-02-26.
// */
//public class WebsocketUserInterceptor implements ChannelInterceptor {
//
//    @Autowired
//    private WebSocketServ webSocketServ;
//
//    @Autowired
//    private SimpUserRegistry userRegistry;
//
//    @Override
//    public Message<?> preSend(Message<?> message, MessageChannel channel) {
//        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//        if (StompCommand.CONNECT.equals(accessor.getCommand())){
//            System.out.println("连接success");
//            Object raw = message.getHeaders().get(SimpMessageHeaderAccessor.NATIVE_HEADERS);
//            if (raw instanceof Map) {
//                Object name = ((Map) raw).get("name");
//                if (name instanceof LinkedList) {
//                    String id = ((LinkedList) name).get(0).toString();
//                    //设置当前访问器的认证用户
//                    accessor.setUser(new WebsocketUserVO(id));
//                    webSocketServ.pushOnlineUser(id);
//                }
//            }
//
//        }
//        return null;
//    }
//
//    @Override
//    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
//
//    }
//
//    @Override
//    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
//
//    }
//
//    @Override
//    public boolean preReceive(MessageChannel channel) {
//        return false;
//    }
//
//    @Override
//    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
//        return null;
//    }
//
//    @Override
//    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
//
//    }
//}
