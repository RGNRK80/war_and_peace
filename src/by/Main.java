package by;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;


//предусмотреть создание каталога с файлом

public class Main {
    public Main() throws IOException {
    }

    public static void main(String[] args) throws IOException {

        File file = new File("f:\\java\\war\\war.txt");
        String url = "https://vk.com/doc354539244_619263533";
        try {
           downloadUsingStream(url, file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1


        FileInputStream fileInputStream = new FileInputStream("f:\\java\\war\\war.txt");
        System.out.printf("File size: %d bytes \n", fileInputStream.available());

        InputStreamReader isr = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr); // буферридер опционально


        int i = -1;
        StringBuilder result = new StringBuilder();
        while ((i = reader.read()) != -1) {
            result.append((char) i);
        }
        fileInputStream.close();

        // rezult - переменная с текстом война и мир

        System.out.println(result);
        String[] rezz= result.toString().split(" ");
        System.out.println(rezz[5]);









    } // psvm

    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
} //Main
