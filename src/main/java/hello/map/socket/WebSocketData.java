package hello.map.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
@ServerEndpoint(value = "/receive")
public class WebSocketData {
    private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session s) {
        log.info(s.getId() + ": open session : {}", s.toString());
        if(!clients.contains(s)) {
            clients.add(s);
            log.info("session open : {}", s);
        }else {
            log.info("already connected session");
        }
    }

    @OnMessage
    public void onMessage(String data, Session session) throws Exception{
        log.info(session.getId());
        log.info("receive data : {}", data);
        for(Session s : clients) {
            log.info("send data : {}", data);
            if (!Objects.equals(s, session)) {
                s.getBasicRemote().sendText(data);
            }
        }
    }

    @OnClose
    public void onClose(Session s) {
        log.info("session close : {}", s);
        clients.remove(s);
    }
}