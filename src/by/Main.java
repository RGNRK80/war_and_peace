package by;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;


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

        // 1  чтение байтовой информации

/*
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

       */

        // 2  чтения текстовой информации, сразу читает текст, в отличие от потока


        ArrayList<String>  arrtext = new ArrayList<>();  // имеем полный список слов
        ArrayList<String>  arrYear = new ArrayList<>();  // имеем полный список дат
        FileReader reader2 = new FileReader("f:\\java\\war\\war.txt");
        StringBuilder builder = new StringBuilder();
        int c;

        int counter=0;
        while ((c = reader2.read()) != -1) {
            char ch= (char) c;
            if (Character.isLetter(ch)) {
                builder.append((char) c);
                counter=0;
            } else {
                if (counter==0) {
                    arrtext.add(builder.toString());
                    builder.delete(0,builder.length());
                    }
                    counter++;
                             }
        }
        reader2.close();

        FileReader reader3 = new FileReader("f:\\java\\war\\war.txt");
        StringBuilder builder3 = new StringBuilder();

        counter=0;
        while ((c = reader3.read()) != -1) {
            char ch= (char) c;
            if (Character.isDigit(ch)) {
                builder3.append((char) c);
                counter=0;
            } else {
                if (counter==0) {
                    arrYear.add(builder3.toString());
                    builder3.delete(0,builder3.length());
                }
                counter++;
            }
        }


        //System.out.println(builder.toString());
        reader3.close();
       // arrtext.sort();                                    прописать компаратор

       // System.out.println(arrtext);

        HashMap<String,Integer> mapTxt = new HashMap();
        for (String unit: arrtext) {
            if (mapTxt.containsKey(unit)) {mapTxt.replace(unit,mapTxt.get(unit)+1);} else {mapTxt.put(unit,1);}

        }
     //   System.out.println(mapTxt);
     //   System.out.println(mapTxt.size());

     //   System.out.println(arrYear);
        TreeSet<Integer> sortYear = new TreeSet<>();
        for (String unit: arrYear) {
            if (unit.length()==4 ) {
            if (Integer.parseInt(unit)>1200) {sortYear.add(Integer.parseInt(unit));}}

        }

        System.out.println(sortYear);

    } // psvm

    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];                                 // записать посимвольно
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
} //Main
