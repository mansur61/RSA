package com.company;

import java.awt.desktop.SystemSleepEvent;
import java.util.Scanner;

public class Main {

    public static AsciKodaDonustur asciKodaDonustur=new AsciKodaDonustur();
    public static rsaAlgorithm rsaAlgorithm=new rsaAlgorithm();
    private static  AralarindaAsalSayiBul aralarindaAsalSayiBul=new AralarindaAsalSayiBul();
    private static String metin="";

    public static void main(String[] args) {

        Scanner s=new Scanner(System.in);
       // 3 basamaklı sayıları şifreleyelim sonra metin şifrelemesini yaparız..
       /* System.out.println("Girilen herhangi bir sayı içerisindeki aralarında asal sayıları bulmak için sayı giriniz");
        int sayiAl=s.nextInt();
        aralarindaAsalSayiBul.aralarındaAsalSayi(sayiAl);
        */
       System.out.println("2 tane asal sayı giriniz");

        System.out.println("1. asal sayı ");
        int sayiAl=s.nextInt();
        if(true == rsaAlgorithm.asalSayiDurumu(sayiAl)){rsaAlgorithm.asal1=sayiAl;}
        else{System.out.println("Asal sayi değil");}

        System.out.println("2. asal sayı ");
        int sayi2Al=s.nextInt();
        if(true == rsaAlgorithm.asalSayiDurumu(sayi2Al)){ rsaAlgorithm.asal2=sayi2Al;}
        else{System.out.println("Asal sayi değil");}


        rsaAlgorithm.Goster();

        System.out.println("nTabanGenelKey (Genel Anahtar):" +rsaAlgorithm.nTabaniniGoster());
        System.out.println("TotientFunction :" +rsaAlgorithm.TotientFunction());

        rsaAlgorithm.publicKey(rsaAlgorithm.TotientFunction(),1);
        rsaAlgorithm.aralarindaAsalSayileriGoster();
        rsaAlgorithm.privateKey(rsaAlgorithm.TotientFunction());
        rsaAlgorithm.privateKeyleriGoster();
        System.out.println();


        String sifrelimetin=rsaAlgorithm.sifreleme(asciKodaDonustur.Donustur("ilyas ısık mansur emin kaya",rsaAlgorithm.nTabaniniGoster()));

        try {
          rsaAlgorithm.desifreleme(sifrelimetin,asciKodaDonustur.asciVer(),asciKodaDonustur.asciCodKarakteri());
        }catch (Exception e) {
            System.out.println(" Main den gelen  HATA :) "+e.getMessage());
        }


    }
}
