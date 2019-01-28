package com.dongzj.utils;


import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: dongzj
 * Mail: dongzj@shinemo.com
 * Date: 2019/1/28
 * Time: 14:08
 */
public class WebSocketUtil {

    /**
     * 简单使用map进行存储在线的session
     */
    private static final Map<String, Session> ONLINE_SESSION = new ConcurrentHashMap<>();

    public static void addSession(String userNick, Session session) {
        ONLINE_SESSION.put(userNick, session);
    }

    public static void removeSession(String userNick) {
        ONLINE_SESSION.remove(userNick);
    }

    public static void sendMessage(Session session, String message) {
        if (session == null) {
            return;
        }
        //getAsyncRemote和getBasicRemote 异步和同步
        RemoteEndpoint.Async async = session.getAsyncRemote();
        //发送消息
        async.sendText(message);
    }

    public static void sendMessageForAll(String message) {
        ONLINE_SESSION.forEach((sessionId, session) -> sendMessage(session, message));
    }
}
