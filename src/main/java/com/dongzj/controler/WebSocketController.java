package com.dongzj.controler;

import com.dongzj.utils.WebSocketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * @ServerEndpoint与@RestController作用一样 User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/1/28
 * Time: 14:00
 */
@Component
@ServerEndpoint(value = "/my-chat/{usernick}")
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    /**
     * 连接事件
     *
     * @param userNick
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "usernick") String userNick, Session session) {
        String message = "有新游客[" + userNick + "]加入聊天室！";
        logger.info(message);
        WebSocketUtil.addSession(userNick, session);
        //向所有的在线通知 xxx登录了聊天室
        WebSocketUtil.sendMessageForAll(message);
    }

    @OnClose
    public void onClose(@PathParam(value = "usernick") String userNick, Session session) {
        String message = "游客[" + userNick + "]退出聊天室!";
        logger.info(message);
        WebSocketUtil.removeSession(userNick);
        WebSocketUtil.sendMessageForAll(message);
    }

    @OnMessage
    public void onMessage(@PathParam(value = "usernick") String userNick, String message) {
        String info = "游客[" + userNick + "]：" + message;
        logger.info(info);
        WebSocketUtil.sendMessageForAll(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error("异常：" + throwable);
        try {
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }
}
