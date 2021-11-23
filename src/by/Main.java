package by;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;




public class Main {
    public Main() throws IOException {
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        File dirWar=new File("c:\\java");
        System.out.println(dirWar.mkdir());
        dirWar=new File("c:\\java\\war");
        System.out.println(dirWar.mkdir());
        // каталоги нужно создавать поочередно :/

        File file = new File("c:\\java\\war\\war.txt");

        String url = "https://vk.com/doc354539244_619263533";
        try {
           downloadUsingStream(url, file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 1  чтение байтовой информации в переменную result  - проблема с кодировкой. нужно использовать ридер.

        File fileInpStream = new File("c:\\java\\war\\war.txt");
        fileInpStream.createNewFile();
        FileInputStream fileInputStream = new FileInputStream("c:\\java\\war\\war.txt");
        System.out.printf("File size: %d bytes \n", fileInputStream.available());

        InputStreamReader isr = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(isr); // буферридер опционально можно и не использовать


        int i = -1;
        StringBuilder result = new StringBuilder();
        while ((i = reader.read()) != -1) {
            result.append((char) i);
        }
        fileInputStream.close();




        // 2  чтения текстовой информации в переменную builder, сразу читает текст, в отличие от потока


        ArrayList<String>  arrtext = new ArrayList<>();  //для полного список слов
        ArrayList<String>  arrYear = new ArrayList<>();  // для списка дат
        FileReader reader2 = new FileReader("c:\\java\\war\\war.txt");
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

        FileReader reader3 = new FileReader("c:\\java\\war\\war.txt");
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

        reader3.close();

        HashMap<String,Integer> mapTxt = new HashMap();
        for (String unit: arrtext) {
            if (mapTxt.containsKey(unit)) {mapTxt.replace(unit,mapTxt.get(unit)+1);} else {mapTxt.put(unit,1);}

        }

        TreeSet<Integer> sortYear = new TreeSet<>();
        for (String unit: arrYear) {
            if (unit.length()==4 ) {
            if (Integer.parseInt(unit)>1200) {sortYear.add(Integer.parseInt(unit));}}

        }

        //вывод в файл

        File aboutWar = new File("c:\\java\\war\\aboutWar.txt");
        aboutWar.createNewFile();
        FileWriter writer = new FileWriter("c:\\java\\war\\aboutWar.txt");
        writer.write(" Something about book War&Peace: \n" );
        writer.write(" Using years:  ");
        sortYear.stream().forEach(s-> {
            try {
                writer.write(s+" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.write("\n");
        writer.write("most usual word: ");

        String kMin="";
        String kMax="";
        int vMin=100;
        int vMax=0;
        String wLong=""; // длинное слово

        for (String unit: mapTxt.keySet()) {
            if (mapTxt.get(unit)>=vMax) {vMax=mapTxt.get(unit); kMax=unit;}
            if (mapTxt.get(unit)<=vMin) {vMin=mapTxt.get(unit); kMin=unit;}
            if (unit.length()>=wLong.length()) {wLong=unit;}
        }
        writer.write(kMax+":" + vMax + " bits \n");
        writer.write("most unusual word: ");
        writer.write(kMin+":" + vMin + " bits \n");
        writer.write("Longest word: " + wLong + " - " + wLong.length() + " chars");
        writer.close();

Heroes pierBezuhov =new Heroes("Pier","Bezuhov", 22,"male",10);
Heroes andrBolkonski =new Heroes("Andrey","Bolkonski", 27,"male",11);
Heroes natRostova = new Heroes("Natasha", "Rostova", 18,"female",19);

        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("c:\\java\\war\\aboutWar-heroes.txt"));
        objectOutputStream.writeObject(pierBezuhov);
        objectOutputStream.writeObject(andrBolkonski);
        objectOutputStream.writeObject(natRostova);

        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("c:\\java\\war\\aboutWar-heroes.txt"));
        Heroes pierResored = (Heroes) objectInputStream.readObject();
        Heroes andrResored = (Heroes) objectInputStream.readObject();
        Heroes natResored = (Heroes) objectInputStream.readObject();

        System.out.println(pierResored);
        System.out.println(andrResored);
        System.out.println(natResored);


    } // psvm

    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];                                 // можно записать посимвольно. опционально
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
} //Main

class Heroes implements Serializable{
    String name;
    String surname;
    int age;
    String sex;
    int page;

    public Heroes(String name, String surname, int age, String sex, int page) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.page = page;
    }

    @Override
    public String toString() {
        return "Heroes{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", page=" + page +
                '}';
    }
}