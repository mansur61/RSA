package com.company;

import java.awt.desktop.SystemSleepEvent;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class rsaAlgorithm {

    public int sayac=0,nTabanGenelKey=1,asal1,asal2,totientF=0,e=0;
    public boolean  asalSayiDurumu(int sayi){

        for(int i=2 ; i<sayi;i++){
            if((sayi%i)==0){
                sayac++;
            }
        }
        if(sayac==0){
            nTabanGenelKey=nTabanGenelKey*sayi;// iki asal sayının çarpımıdır.
            return true;
           // System.out.println("asal sayı");
        }else{return false;
        //System.out.println("asal sayı değil");
        }
    }
    public  void Goster(){
        System.out.println("asal1 :"+asal1+", asall2 :"+asal2);
    }
    public  int nTabaniniGoster(){
     return nTabanGenelKey;
    }
    public int TotientFunction(){
        totientF = (asal1-1)*(asal2-1);
       return totientF;
    }
    private int asal = 0;
    private List<Integer> listPubKey=new ArrayList<Integer>();
    private List<Integer> listPriKey=new ArrayList<Integer>();
    private  int carp=1,prKey=0,pubKey=0;
    private String sifreliMetin="";
    private int Sif_usaL=1,dSif_usal=1;
    private String asciKoduTut="",tut="",sifreliHali="",desifreliHali="",donusecekMetin="";
    private char[] metinTut,deSifreliMetinTut;
    private char [] deSifreliHaliTut;
    private  String sifreCoz="";
    private int indexilerle=0,sifrele1=1,block=3;
    private  BigInteger usAL,modALSifre;
    private BigInteger nTaban,usalAta;



    public void publicKey(int totientF,int enKucuk) {
        for(int i=2;i<totientF;i++){
            asal=0;
            for(int j=2;j<totientF;j++){
                //System.out.println(i+"%"+j+ ":"+i%j + "   "+ totientF +"%"+j+ ":" + (totientF % j) );
                if((( i % j)  == 0 ) &&  (( totientF % j ) == 0)){
                    asal++;
                }
            }
            if(asal==0){/*System.out.println("i : "+i);*/listPubKey.add(i);}
            else{/*System.out.println(i+ " için "+" asal :"+asal);*/}
        }

    }


    public void aralarindaAsalSayileriGoster(){
        System.out.println(totientF +" ile aralarında asal olan sayılar.");
        for (int i=0;i<listPubKey.size();i++){System.out.print(listPubKey.get(i)+" , ");}
    }

    /** BU kısımda genel anahtar seçimi yaptıktan sonra özel anahtar seçimi yapılıyor. **/
    public void privateKey(int totientF){ //1 < e <  totientF( Q(n) ) , e ve d değerini bulma , burda 1 < e <  totientF( Q(n) ) arasında
        // herhangi bir  d değerine karşılık e değeri seçiliyor .Kural sağlandı mı punKey ve priKey değerleri bulunuyor.
        sayac=0;
        System.out.println();
        for(int i=2;i<totientF;i++){
            for(int j=0;j<listPubKey.size();j++){
                //System.out.println("list.get(j) :" +list.get(j));
                carp=listPubKey.get(0)*i; // e nin ilk elemanı alındı . d yi bulmak için
                int modAl=carp % totientF;
                //System.out.println("modAl :" +modAl);
                if(modAl == 1){sayac++;prKey=i;
                pubKey=listPubKey.get(j);System.out.println("i :" +i+" ,prKey :"+prKey +" ,pubKey :"+pubKey+" ,carp :"+carp);
                    listPriKey.add(i);break;
                }
            }
        }


    }

    public void privateKeyleriGoster(){
        System.out.println("private key ler .");
        for (int i=0;i<listPriKey.size();i++){System.out.print(listPriKey.get(i)+"  ");}
    }





    public String sifreleme(String metin){/** asciKodaDonusterden gelen metin bu parametre
     değeri dir .**/
        donusecekMetin="";
        System.out.println("sifrelemeye gelen metin :"+metin);
        int Metinuzunlugu=metin.length();
        metinTut = new char[metin.length()];
        for(int i=0;i<Metinuzunlugu;i++){
            char karakter=metin.charAt(i);
            metinTut[i]=karakter;
        }
        /** Bu kısımda parametre olarak gelen metnin iki asal sayının çarpımından
        elde edilen uzunluğunu kadar  blocklara ayırma işlemi  **/
        String nTabanGenelKeyString=Integer.toString(nTabanGenelKey);
        //System.out.println("nTabanGenelKeyString :"+nTabanGenelKeyString.length());
        int lClear = nTabanGenelKeyString.length()-1;
        int lCipher = nTabanGenelKeyString.length();
        System.out.println("lCipher :"+lCipher+" ,lClear :"+lClear);
        for(int m = 0; m<Metinuzunlugu;m++){
            donusecekMetin += metinTut[m];
            //System.out.println("donusecekMetin.length() :"+donusecekMetin.length());
            if(lClear == donusecekMetin.length()){
                try {

                    usAL = new BigInteger(donusecekMetin).pow(pubKey);
                    usalAta = usAL;
                    nTaban = new BigInteger(nTabanGenelKeyString);
                    modALSifre = usalAta.mod(nTaban);
                    String sitringeDonustur = modALSifre.toString();
                    //System.out.println("usAL :" + (usAL)+" ,mod :"+modALSifre+" ,sitringeDonustur uzunluk :"+sitringeDonustur.length()+ " ,donusecekMetin :"+donusecekMetin);


                    if(sitringeDonustur.length() == lCipher ){
                        sifreliHali += sitringeDonustur;
                    }else{
                        /* istenilen blocklara ayırmada block uzunluğuna tamamlama ksımı*/
                        int nekadarEklenecek= lCipher - sitringeDonustur.length();//donusecekMetin.length();
                        //System.out.println("nekadarEklenecek :"+nekadarEklenecek);
                        for(int n=0;n<nekadarEklenecek;n++){
                            sitringeDonustur =  "0" + sitringeDonustur;
                        }
                        sifreliHali += sitringeDonustur;
                    }
                    donusecekMetin="";
                }catch(Exception e){System.out.println("Şifreleme metodundan gelen  HATA :"
                        +e.getMessage());}

            }
        }

        //System.out.println("nTaban :"+nTabanGenelKey + " ,pubKey :"+pubKey+ " ,priKey :"+prKey
          //      +" ,sifreliHali :"+ sifreliHali );
        return sifreliHali;
    }

    public void desifreleme(String sifrelimetin,int asciAl[],char asciCodKarakteri[]){

        donusecekMetin="";
        int Metinuzunlugu=sifrelimetin.length();
            /* sifrelimetin , şifreleme() metodundan gelen metindir.*/
       // System.out.println("deşiefrele metoduna gelen ,sifrelimetin :"+sifrelimetin+" ,Metinuzunlugu :"+Metinuzunlugu);
        deSifreliMetinTut = new char[Metinuzunlugu];
        for(int i=0;i<Metinuzunlugu;i++){
            char karakter=sifrelimetin.charAt(i);
            deSifreliMetinTut[i]=karakter;
        }

        /** Şifrelemeden elde edilen metni istenilen iki asal sayının çarpımından elde edilen
        * sayının uzunuluğunu 1 eksiği kadar blocklayarak tekrar metin halline getirdik
        * şifreli metni açmak için  **/
        String nTabanGenelKeyString= Integer.toString(nTabanGenelKey);
        int lCipher = nTabanGenelKeyString.length();
        for(int m = 0; m<Metinuzunlugu;m++){
            donusecekMetin += deSifreliMetinTut[m];
           // System.out.println("donusecekMetin :"+donusecekMetin+" ,donusecekMetin uzunluk :"+donusecekMetin.length());
            if(lCipher == donusecekMetin.length()) {

                usAL = new BigInteger(donusecekMetin).pow(prKey);
                usalAta = usAL;
                nTaban = new BigInteger(nTabanGenelKeyString);
                modALSifre = usalAta.mod(nTaban);
                String sitringeDonustur = modALSifre.toString();
                //System.out.println("usAL :" + (usAL)+" ,mod :"+modALSifre+" ,sitringeDonustur uzunluk :"+sitringeDonustur.length()+ " ,donusecekMetin :"+donusecekMetin);

                /** deşifreleme sonrasında oluşan lCipher uzunluklarında olan blockları
                * lClear uzunluğunda blocklara ayırıyoruz.. **/
                int lClear = nTabanGenelKeyString.length()-1;
                if(sitringeDonustur.length() == lClear ){
                    desifreliHali += sitringeDonustur;
                }else{
                    /** istenilen blocklara ayırmada block uzunluğuna tamamlama ksımı **/
                    int nekadarEklenecek= lClear-sitringeDonustur.length();
                   // System.out.println("nekadarEklenecek :"+nekadarEklenecek);
                    for(int n=0;n<nekadarEklenecek;n++){
                        sitringeDonustur = "0"+ sitringeDonustur;
                    }
                    desifreliHali += sitringeDonustur;
                }
                sifrele1=1;
                donusecekMetin="";
            }
        }
        System.out.println("nTaban :"+nTabanGenelKey + " ,pubKey :"+pubKey+ " ,priKey :"+prKey
                +"  ,sifrelimetin :"+sifrelimetin+ " ,desifreliHali :"+ desifreliHali);

        /** Bu kısım şifreli mesajı açma kısımı ,AsciKodaDonustur sınıfında alınan parametreler
         ile kontroller yapılıyor  **/
        donusecekMetin="";
        deSifreliHaliTut=new char[desifreliHali.length()];
        System.out.println("deSifreli uzunuluk :"+deSifreliHaliTut.length);
        for(int i=0;i<deSifreliHaliTut.length;i++){
            char karakter=desifreliHali.charAt(i);
            deSifreliHaliTut[i]=karakter;
            //System.out.println("deSifreliHaliTut["+i+"] :"+deSifreliHaliTut[i]);
        }

        for(int b=0;b<deSifreliHaliTut.length;b++){
            donusecekMetin += deSifreliHaliTut[b];
            /*if(b<asciAl.length){
                System.out.println("asciAl[indexilerle] :"+asciAl[b]+" ,asciCodKarakteri[indexilerle] :"+asciCodKarakteri[b]);
            }*/
            if(block == donusecekMetin.length()) {
                //System.out.println("donusecekMetin :"+donusecekMetin);
                int acikHali = Integer.parseInt(donusecekMetin);
                //System.out.println("acikHali :"+acikHali+" ,asciAl["+indexilerle+"] :"+asciAl[indexilerle]);
                if(acikHali == asciAl[indexilerle]){
                    sifreCoz += asciCodKarakteri[indexilerle];
                }
                donusecekMetin="";
                indexilerle++;
            }

        }
        /** Şifrenin çözülmüş hali **/

        System.out.print("Şifre Açıldı :"+sifreCoz);


    }
}
