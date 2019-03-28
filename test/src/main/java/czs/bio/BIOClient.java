package czs.bio;

import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName BIOClient
 * @Description BIO模式的客户端
 * @Author czs
 * @Date 2019/3/28 14:52
 * @Version V1.0
 **/
public class BIOClient {
    public void init(String host, int port){
        BufferedReader reader = null;
        BufferedWriter writer = null;
        Socket client = null;
        String inputContent;
        int count = 0;
        try {
            reader = new BufferedReader(new InputStreamReader(System.in));
            client = new Socket(host, port);
            writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            System.out.println(getCurrentTime()+": ID为"+client.hashCode()+"的客户端已启动");
            while ((inputContent = reader.readLine()) != null){
                count++;
                inputContent = getCurrentTime() + ": 第"+count+"条消息："+inputContent+"\n";
                writer.write(inputContent);
                writer.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                reader.close();
                writer.close();
                client.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public String getCurrentTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd : mm:HH:ss");
        return  format.format(new Date());
    }

    public static void main(String[] args) {
        BIOClient client = new BIOClient();
        client.init("127.0.0.1", 8888);
    }
}
