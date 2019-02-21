package com.company;

//import static java.lang.Integer.parseInt;

import javax.swing.text.Style;
import java.awt.desktop.SystemSleepEvent;
import java.util.ArrayList;
import java.util.List;

public class AsciKodaDonustur {

    private String donusecekMetin="";
    private String asciKoduTut="",sirelemeyeGonder="";
    private char[] metinTut;
    private int [] asciiCodTut;
    private  char[] asciiCodKarakteri;
    private  String asciiEkle = ""; /** Her oluşan ascii karakteri 3 lü blocklara
     ayırarak , 3 lü bloclalrdan küçük ise baş terafına 0 ekleme durumunda bunları birleştiren
     bir değişken **/
    public String Donustur(String metin,int nTabanGenelKey){

        int Metinuzunlugu=metin.length();
        //System.out.println("metin uzunluğu :"+Metinuzunlugu);
        asciiCodTut =new int[metin.length()];
        asciiCodKarakteri =new char[metin.length()];
        for(int i=0;i<Metinuzunlugu;i++){

            char karakter=metin.charAt(i);
            asciiCodKarakteri[i]=karakter;/** Metnin her bir karakterleri **/
            //System.out.println("karakter :"+karakter);
            int asci=(int) karakter;
            //System.out.println("ascii :"+asci);
            asciiCodTut[i]=asci;/** Metnin her bir karakterlerinin ascii kodu **/
            asciKoduTut+=asci;


        }
        //System.out.println("asciKoduTut :" +asciKoduTut);
        String nTabanGenelKeyString= Integer.toString(nTabanGenelKey);

        /** Bu kısımda her bir kelimenin asci kodu rakam ise
         * "00" iki basamaklı sayı ise "0" eklendiği yer . Amaç her bir ascii karakteri 3 basa-
         * maklı hale getirmek .**/
        for (int i=0;i<asciiCodTut.length;i++){
            if(asciiCodTut[i]> 100){
                asciiEkle += asciiCodTut[i];
            }
            if(asciiCodTut[i] < 100){
                String ekle = "0" + asciiCodTut[i];
                asciiEkle += ekle;
            }
            else if(asciiCodTut[i] < 10){
                String ekle = "00" + asciiCodTut[i];
                asciiEkle += ekle;
            }
        }
        System.out.println("asciKoduTut :" +asciKoduTut+" ,asciiEkle uzunluk:"+asciiEkle.length()+" ,asciiEkle :"+asciiEkle);

        metinTut = new char[asciiEkle.length()];
        //System.out.println("metinTutDizi uzunluk :"+metinTut.length);
        for(int i=0;i<asciiEkle.length();i++){
            char karakter=asciiEkle.charAt(i);
            metinTut[i]=karakter;
            //System.out.println("metinTut["+i+"] :"+metinTut[i]);
        }


        /** Şifreleme yapmak içim ascii kodlarına ayrılmış olan metnin ,iki asal sayının
         * çarpımından elde edilen uzunluğunun  eksiği kadar bloclara ayırıp ,tekrardan
         * birleştirip üzerinde işlemler yapmak için şifrele() metoduna parametre olarak
         * gönderilmesi sağlanıyor.**/

        int lClear = nTabanGenelKeyString.length()-1;
        // System.out.println("lClear :"+lClear + " ,nTabanGenelKeyString uzunluk :"+nTabanGenelKeyString.length());
        for(int m = 0; m<asciiEkle.length();m++){

            //System.out.println("metinTut["+m+"] :" +metinTut[m]);
            donusecekMetin += metinTut[m];
            if(lClear == donusecekMetin.length()){
                // System.out.println("donusecekMetin :"+donusecekMetin+
                //       " ,donusecekMetin.uzun :"+donusecekMetin.length() );
                sirelemeyeGonder += donusecekMetin;
                // System.out.println("tut :"+tut);
                donusecekMetin="";
            }


        }
        //System.out.println("donusecekMetin : "+donusecekMetin);

        /** istenilen blocklara ayırmada block uzunluğuna tamamlama ksımı **/
        if(donusecekMetin.length() == 0){
            System.out.println("Herhangi bir ekleme yapma çünkü herhangi arda kalan bir uzunlu yok.");

        }else {
            int nekadarEklenecek = lClear - donusecekMetin.length();
            //System.out.println("nekadarEklenecek :"+nekadarEklenecek);
            for (int n = 0; n < nekadarEklenecek; n++) {
                donusecekMetin += "0";

            }
            sirelemeyeGonder += donusecekMetin;
            //System.out.println("tut :" + tut);

        }

        return sirelemeyeGonder;
    }

    /** de
     * Deşifrelemede kulllanmak için parametre olarak gönderilen metodlardır aşağıdaki metodalar    **/
    public int[] asciVer(){
        return asciiCodTut;
    }
    public char[] asciCodKarakteri(){
        return asciiCodKarakteri;
    }


}
