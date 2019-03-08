package com.xumou.test.jvm;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 */
public class ParseClass {

    public static void main(String[] args) throws IOException {
        byte[] bytes = readFileToBytes("D:\\\\h2\\\\TestClass.class");
        String[] hexs = byteToHexArr(bytes);
        printHexs(hexs);
    }

    private static void printHexs(String[] hexs) {
        for (int i = 0; i < hexs.length; i++) {
            System.out.print(hexs[i] + " ");
            if( (i+1) % 16 == 0){
                System.out.println();
            }else if((i+1) % 8 == 0){
                System.out.print("  ");
            }
        }
    }

    private static String[] byteToHexArr(byte[] bytes) {
        String[] hexs = new String[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            String str = Integer.toHexString(Byte.toUnsignedInt(bytes[i]));
            for (int j = str.length(); j < 2; j++) {
                str = "0" + str;
            }
            hexs[i] = str.toUpperCase();
        }
        return hexs;
    }


    private static byte[] readFileToBytes(String path) {
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(path);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while(true){
                int i = fis.read();
                if(i == -1){
                    break;
                }
                bos.write(i);
            }
            return bos.toByteArray();
        }catch (Exception e){
            return new byte[0];
        }finally {
            if(fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

}