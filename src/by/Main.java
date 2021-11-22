package by;

import java.io.*;
import java.net.URL;

public class Main {
    public Main() throws IOException {
    }

    public static void main(String[] args) throws IOException {

        File file = new File("f:\\java\\war\\war.txt");
        String url = "https://vk.com/doc354539244_619259018";
        try {
           downloadUsingStream(url, file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        FileReader fileReader=new FileReader(file);











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
