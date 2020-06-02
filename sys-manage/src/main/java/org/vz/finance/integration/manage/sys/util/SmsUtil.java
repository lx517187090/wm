package org.vz.finance.integration.manage.sys.util;


import cn.hutool.core.io.IoUtil;
import com.alibaba.fastjson.JSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: lixi
 * @Despriction: 短信工具类
 * @Date: Created in : 2019/12/12 0012
 */
public class SmsUtil {

    protected static final Logger log = LogManager.getLogger(SmsUtil.class);
    /**
     * 发送短信
     */
    public static Map<String, Object> send(String host, Integer port, String content) throws IOException {
        Socket socket = new Socket();
        socket.setSoTimeout(3000);
        socket.connect(new InetSocketAddress(Inet4Address.getByName(host), port), 3000);
        Map<String, Object> retMap = new HashMap<>();
        try {
            String retStr = todo(socket, content);
            log.info(retStr);
            retMap = JSON.parseObject(retStr);
        } catch (Exception e) {
            log.error("异常关闭");
            retMap.put("xym", "1");
            retMap.put("xyxx", "发送短信失败");
        }
        socket.close();
        log.info("客户端已退出");
        return retMap;
    }

    /**
     * 发送短信
     *
     * @param socket  socket
     * @param reqData 数据
     */
    private static String todo(Socket socket, String reqData) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader = null;
        try {
            outputStream = socket.getOutputStream();
            outputStream.write(reqData.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            //socket.shutdownOutput();
            log.info("发送数据到socket服务器成功");
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);//包装成字符流，提高效率
            bufferedReader = new BufferedReader(inputStreamReader);//缓冲区
            StringBuilder info = new StringBuilder();
            String temp;//临时变量
            while ((temp = bufferedReader.readLine()) != null) {
                info.append(temp);
            }
            log.info("接收数据:{}", info.toString());
            return info.toString();
        } catch (Exception e) {
            log.info("请求socket服务异常", e);
            throw new RuntimeException("请求socket服务异常");
        } finally {
            IoUtil.close(outputStream);
            IoUtil.close(bufferedReader);
            IoUtil.close(inputStream);
            IoUtil.close(socket);
        }
    }
}
