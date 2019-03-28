package czs.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName BIOServer
 * @Description  一个BIO模式的Socket服务器
 * @Author czs
 * @Date 2019/3/28 14:31
 * @Version V1.0
 **/
public class BIOServer {
    public void init(int port){
        ServerSocket server = null;
        Socket client = null;
        BufferedReader reader = null;
        String inputContent;
        int count = 0;
        try {
            server = new ServerSocket(port);
            System.out.println(getCurrentTime()+": 服务器已启动，等待连接");
            while (true){
                client = server.accept();
                System.out.println(getCurrentTime()+": ID为 "+client.hashCode()+"的客户端已连接");
                reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                while ((inputContent = reader.readLine()) != null){
                    System.out.println(getCurrentTime()+": 收到ID为 "+client.hashCode()+"的客户端发送的内容： " +inputContent);
                    count ++;
                }
                System.out.println(getCurrentTime()+": 累计收到ID为 "+client.hashCode()+"的客户端发送的内容 " +count+" 条，发送完毕，连接断开");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getCurrentTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd : mm:HH:ss");
        return  format.format(new Date());
    }

    public static void main(String[] args) {
        BIOServer server = new BIOServer();
        server.init(8888);

    }
}
