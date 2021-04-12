package com.wxuy.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@ServerEndpoint("/websocket/{sid}")
@Slf4j
public class WebSocketServer {

	/**
	 * 静态变量，用来记录当前在线连接数。
	 */
	private static AtomicInteger onlineCount = new AtomicInteger(0);
	/**
	 * concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	 */
	private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();
	/**
	 * 与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	private Session session;
	/**
	 * 接收sid
	 */
	private String sid="";
	/**
	 * 连接建立成功调用的方法
	 **/
	@OnOpen
	public void onOpen(Session session, @PathParam("sid") String sid) {
		this.session = session;
		//加入set中
		if(!webSocketSet.contains(this)){
			webSocketSet.add(this);
			//在线数加1
			addOnlineCount();
			log.info("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
			this.sid=sid;
			try {
				sendMessage("连接成功");
			} catch (IOException e) {
				log.error("websocket IO异常");
			}
		}else{
			log.error("session 重复连接");
		}
	}
	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		//从set中删除
		webSocketSet.remove(this);
		//在线数减1
		subOnlineCount();
		log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
	}
	/**
	 * 收到客户端消息后调用的方法
	 * @param message 客户端发送过来的消息
	 **/
	@OnMessage
	public void onMessage(String message, Session session) {
		log.info("收到来自窗口"+sid+"的信息:"+message);
		//群发消息
		for (WebSocketServer item : webSocketSet) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		log.error("发生错误");
		error.printStackTrace();
	}
	/**
	 * 实现服务器主动推送
	 */
	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
	}
	/**
	 * 群发自定义消息
	 * */
	public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
		if(sid ==null){
			log.info("sid 不可为null");
		}
		log.info("推送消息到窗口"+sid+"，推送内容:"+message);
		for (WebSocketServer item : webSocketSet) {
			try {
				//设定只推送给这个sid的
				if(item.sid.equals(sid)) {
					item.sendMessage(message);
				}
			} catch (IOException e) {
				continue;
			}
		}
	}
	public static synchronized AtomicInteger getOnlineCount() {
		return onlineCount;
	}
	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount.getAndIncrement();
	}
	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount.getAndDecrement();
	}
}
