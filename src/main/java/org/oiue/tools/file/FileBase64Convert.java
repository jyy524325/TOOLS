package org.oiue.tools.file;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings({ "resource", "unused"})
public class FileBase64Convert {

    /**
     * 
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String ioToBase64(String fileName) throws IOException {
        String strBase64 = null;
        InputStream in = new FileInputStream(fileName);
        // in.available()返回文件的字节长度
        byte[] bytes = new byte[in.available()];
        // 将文件中的内容读入到数组中
        in.read(bytes);
        strBase64 = new BASE64Encoder().encode(bytes); // 将字节流数组转换为字符串
        in.close();
        return strBase64;
    }

    /**
     * 
     * @param strBase64
     * @param fileName
     * @throws IOException
     */
    public static void base64ToIo(String strBase64, String fileName) throws IOException {
        String string = strBase64;
        // 解码，然后将字节转换为文件
        byte[] bytes = new BASE64Decoder().decodeBuffer(string); // 将字符串转换为byte数组
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        byte[] buffer = new byte[1024];
        FileOutputStream out = new FileOutputStream(fileName);
        int bytesum = 0;
        int byteread = 0;
        while ((byteread = in.read(buffer)) != -1) {
            bytesum += byteread;
            out.write(buffer, 0, byteread); // 文件写操作
        }
    }
}
