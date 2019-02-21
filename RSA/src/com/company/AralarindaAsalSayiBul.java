package com.company;

import java.util.ArrayList;
import java.util.List;

public class AralarindaAsalSayiBul {

    // Aralarında asal demek , iki ya da daha fazla sayının 1 den başka ortak böleni olmadığı anlamına gelir .
    private int sayac=0,ciftsayi=0;
    private List<Integer> ilkSayilist=new ArrayList<Integer>();
    private List<Integer> ikinciSayilist=new ArrayList<Integer>();

    // Girilen herhangi bir sayı içerisindeki aralarında ki asal sayı
    public void aralarındaAsalSayi(int sayi){
        for(int i=2;i<sayi;i++){ // herhangi bir sayı girilebilir .Ama kısıt küçük sayı burda olmalı
            sayac=0;
            for(int j=sayi;j>i;j--){// Büyük sayı burda olmalı

                // Neden i ye kadar azalacak ,(2,3) aralarında asal sayı ,(3,2) de aralarında asal sayı
                //sonuçta ikiside aynı ifade oluyor ,aynı ifadeyi iki kez yazmamak için i ye kadar azaltılıyor.;

                if(j>i){
                        //System.out.println("i = "+i+" ,j = " + j);

                    if((( i % j)  == 0 ) &&  (( sayi % j ) == 0)){
                        sayac++;
                        ArrayaKLE(i,j);
                    }
                }
                else if(i==j){
                    System.out.println("Asal sayılar birbirine eşit olamazlar.");
                    break;
                }

                else
                {
                    break;
                }


            }

        }

    }

    private void ArrayaKLE(int i,int j) {
       // System.out.println("geldi ," +"i = "+i+" ,j = "+j+", (list.size() = "+(list.size()));
        ilkSayilist.add(i);
        ikinciSayilist.add(j);
        goster();

    }
    private int syc=0;
    private void goster() {

        //System.out.println((ilkSayilist.size()));
        for(int i=syc;i<ilkSayilist.size();i=syc){
            System.out.println(ilkSayilist.get(i)+","+ikinciSayilist.get(i));
            syc++;
        }

        //System.out.println("syc :"+syc);
    }
}
