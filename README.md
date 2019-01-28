WebSocket

    H5开始提供的一种在单个TCP连接上进行全双工通讯的协议
    
1、WebSocket属性

    Socket.readyState 
      表示连接状态
        0 - 表示连接尚未建立
        1 - 表示连接已经建立，可以进行通信
        2 - 表示连接正在进行关闭
        3 - 表示连接已经关闭或者连接不能打开
        
    Socket.bufferedAmount 
       表示已被send()放入正在队列中等待传输，但是还没有发出的UTF-8文本字节数
       
2、WebSocket事件

    事件              事件处理程序              描述
    
    open             Socket.onopen            连接建立时触发
        
    message          Socket.onmessage         客户端接收服务器端数据时触发
    
    error            Socket.onerror           通信发生错误时触发
    
    close            Socket.onclose           连接关闭时触发
     
3、WebSocket方法

    方法                      描述
        
    Socket.send()            使用连接发送数据
    Socket.close()           关闭连接
    
4、实现方式

    1）、注解方式
    
        @WebSocketEndpoint      类层次的注解，将目前的类定义成一个websocket服务器端
        
        @onOpen                 打开一个新的连接，即有新连接时，会调用被此注解的方法
        
        @onClose                关闭连接时调用
        
        @onMessage              当服务端接收到客户端发送的消息时所调用的方法
        
        @PathParam              接收uri参数的
        
    2）、继承javax.websocket.Endpoint类

