import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DoublyLinkedList {
    Node head,tail;       //classımizda kullanacagimiz degiskenler

    DoublyLinkedList(){     //baslangicta ikisi de null olmali.
        head= null;
        tail = null;
    }

 // parametre olarak Student tipinde bir nesne aliyor.
    public void addObject(Student info) {      
        Node yeniNode = new Node(info);     //eklenecek yeni node bos bir sekilde olusturuldu.
        boolean flag = true;            //flag listeye ilk eleman eklendigi zaman tekrardan while dongusune girilmesin diye olusturuldu.
        //basa ekleme. eger head null ise listemiz bos demektir.
        if (head == null) {
            head = yeniNode;
            tail = yeniNode;
            flag = false;


        }
        Node cursor = head;     //eleman eklemesi yaparken node tipinde bir cursor tutacagiz.
        
        int ogrenciNumarasiYeniNode = yeniNode.data.getogrenciNumarasi();

        while(cursor != null && flag ==true){
           //dongu icerisinde eger iflerin icine girilebildiyse break ile donguden cıkılıyor. bunu sebebi zaten ifin icine girebildiysek node yerlestirilmis demektir. donguyu fazladan dondurmeye gerek yok.

            //cursorun numarasını cekiyorum.
            int ogrenciNumarasiCursor = cursor.data.getogrenciNumarasi();
            

            //yeniNode en basa gecmesi gerekiyorsa bu blok calisir.
            if (ogrenciNumarasiYeniNode<ogrenciNumarasiCursor){
                head.prev = yeniNode;
                yeniNode.next = head;
                yeniNode.prev = null;
                head= yeniNode;
                break;

            }

            //taili degistirmek icin if blogu.
            if (((ogrenciNumarasiYeniNode>ogrenciNumarasiCursor) && (cursor==tail))){
                tail.next = yeniNode;
                yeniNode.prev = tail;
                yeniNode.next=null;
                tail = yeniNode;
                break;
            }
            
            


           //asagidaki if blogu icin cursordan sonraki elemanin numarası bir degiskene atiliyor.
            int ogrenciNumarasiNextCursor = cursor.next.data.getogrenciNumarasi();
            

            //cursor ortalarda ise bu blok calisir.
            if ((ogrenciNumarasiYeniNode>ogrenciNumarasiCursor) && ogrenciNumarasiYeniNode<ogrenciNumarasiNextCursor) {
                yeniNode.next = cursor.next;
                yeniNode.prev = cursor;
                cursor.next.prev = yeniNode;
                cursor.next = yeniNode;

                break;
            }
            cursor=cursor.next;     //dongunun devamliligini sagliyorum.
        }


    }
        //silme islemini gerceklestiren method.
    public void delete(DoublyLinkedList d){
        System.out.println("Lutfen silmek istediginiz kisinin numarasini giriniz: ");
        Scanner input = new Scanner(System.in);
        int data = input.nextInt();
        Node cursor = d.head;       //cursor ile listeyi döneceğiz.

        boolean flag = false;       // bir boolean ifade tanımlayarak kullanıcının bulunup bulunmamasına göre ekrana yazı yazdırıyorum.
        //while döngüsü ile linked listi geziniyorum.
        while(cursor != null){
            //cursor ve datanın eşitliğini kontrol ediyorum. 
            //cursori listeden silme islemlerini if icinde yapacagim.
            if (cursor.data.getogrenciNumarasi() == data){
               //silinmek istenen node head ise bu blok çalisir
                if (cursor==d.head){
                    cursor=cursor.next;
                    cursor.prev =null;
                    head.next=null;
                    head=cursor;
                }
                //silinmek istenen node tail ise bu blok çalisir
                else if (cursor==d.tail){
                    cursor=cursor.prev;
                    cursor.next=null;
                    tail.prev=null;
                    tail=cursor;
                }
                //aradaki silme islemleri icin bu blok calisir.
                else{
                    Node cursorOncesi = cursor.prev;
                    Node cursorSonrasi = cursor.next;

                    cursorOncesi.next=cursorSonrasi;
                    cursorSonrasi.prev =cursorOncesi;
                }

                flag = true;        //flagi true yaparak aranan numaranın bulundugunu ifade ediyorum.
                break;              
            }
            cursor=cursor.next;
        }
        //flagin durumuna gore kullanıcı bulunamazsa bir uyarı ekrana bastıran blok.
        if (flag == false){
            System.out.println("Aradiginiz isimde bir kullanici bulunamadi.");
        }
    }


    //elemanlari ekrana bastirmayi saglayan metod.
    public void elemanBastir(){
        Node cursor = head;     //cursor nesnesini oluşturuyorum.
        while(cursor!=null){
            System.out.println(cursor.data);
            cursor = cursor.next;       //cursoru ilerletiyorum.

        }

    }

    //elemanlari ekrana tersten bastirmayi saglayan metod.
    public void elemanBastirTersten(){
        Node cursor = tail;     //cursor nesnesini oluşturuyorum.
        while(cursor!=null){
            System.out.println(cursor.data);
            cursor = cursor.prev;       //cursoru ilerletiyorum.

        }

    }


//kullanicinin istegine gore islem yapan method.
public void menu (int secim,DoublyLinkedList d){

        if(secim == 1){
            Scanner fileIn=null;       // dosyayı okuyabilmek için fileIn isimli bir nesne oluşturdum ve null atadım.
            //try and catch bloklarında dosya okuma işlemini yapmaya çalıştık. eğer try bloğunda dosya okunamazsa catch bloğu çalışacak ve hata bastıralacak.
            try{
                fileIn = new Scanner(new FileInputStream("ogrenciler.txt"));
            }
            catch (FileNotFoundException e) {
                System.out.println("File not found.");
                System.exit(0);
            }

            while(fileIn.hasNext()){
                String sira =fileIn.nextLine();
                String[] siraArray = sira.split(",");

                //arrayliste geçmemin sebebi arraydeki bilgileri değişkenlere atayıp sonrasında aynı bilgiyi remove ediyorum.
                
                ArrayList<String> numaralar = new ArrayList<>(Arrays.asList(siraArray));

                //bilgileri arrayden değişkenlere atıyorum ve remove ediyorum.
                int ogrenciNumarasi = Integer.parseInt(numaralar.get(0));
                numaralar.remove(0);
                String adSoyad = numaralar.get(0);
                numaralar.remove(0);

                Student c = new Student(adSoyad,ogrenciNumarasi,numaralar);     //nesne oluşturuldu
                d.addObject(c);         //çift yönlü bağlı listeye aktarıldı.

            }
        }

        else if (secim ==2){
            System.out.println("Lutfen kullanicinin bilgilerini yaziniz:(bilgileri virgul ile ayiriniz) ");
            Scanner input = new Scanner(System.in);
            String data = input.nextLine();
            String[] dataList = data.split(",");

            //arrayliste geçmemin sebebi arraydeki bilgileri değişkenlere atayıp sonrasında aynı bilgiyi remove ediyorum. 
    
            ArrayList<String> numaralar = new ArrayList<>(Arrays.asList(dataList));

             int ogrenciNumarasi = Integer.parseInt(numaralar.get(0));
                numaralar.remove(0);
                String adSoyad = numaralar.get(0);
                numaralar.remove(0);

                Student c = new Student(adSoyad,ogrenciNumarasi,numaralar);     //nesne oluşturuldu
                d.addObject(c);         //çift yönlü bağlı listeye aktarıldı.

        }

        else if (secim ==3){
            //kullanıcıdan isim alıyoruz.
            System.out.println("Lutfen arama yapmak istediginiz kisinin ismini giriniz :");
            Scanner input = new Scanner(System.in);
            String data = input.nextLine();
            Node cursor = d.head;       //cursor ile listeyi döneceğiz.

            boolean flag = false;       // bir boolean ifade tanımlayarak kullanıcının bulunup bulunmamasına göre ekrana yazı yazdırıyorum.
           //while döngüsü ile linked listi geziniyorum.
            while(cursor != null){
                //cursor ve datanın eşitliğini kontrol ediyorum. 
                if (cursor.data.getAdSoyad().toLowerCase().equals(data.toLowerCase())){
                    System.out.print("Aradiginiz kullanicinin bilgileri: ");
                    System.out.println(cursor.data);
                    flag = true;        
                    

                }
                cursor=cursor.next;
            }
            //flagin durumuna gore kullanıcı bulunamazsa bir uyarı ekrana bastıran blok.
            if (flag == false){
                System.out.println("Aradiginiz isimde bir kullanici bulunamadi.");
            }
        }
        else if(secim ==4){
           delete(d);       //aldigimiz datayi delete methoduna gonderiyoruz.
        }

        else if (secim == 5){

            d.elemanBastir();       //aldigimiz datayi elemanBastir methoduna gonderiyoruz.

        }


        else if (secim == 6){
            d.elemanBastirTersten();            //aldigimiz datayi elemanBastirTersten methoduna gonderiyoruz.
                }

        else if(secim ==7){
            System.out.println("Uygulamadan cikiliyor.");

        }
}


    public static void main(String[] args) {

        //DoublyLinkedListi olusturuyorum. asagida secime gore islemler yapilacak.
        DoublyLinkedList d = new DoublyLinkedList();
    //while dongusunu kullanicinin girdisine gore devam ettiriyorum.
    int secenek=0;
    while(secenek !=7){
        Scanner input = new Scanner(System.in);
        System.out.println("Yapmak istediginiz islemi seciniz: ");
         int data = input.nextInt();
        d.menu(data,d);     //yukaridaki islemler sonucu kullanicinin ne yapmak istedigi anlasildi ve menu methoduna data ve DoublyLinkedList nesnesi gonderildi.
        secenek = data;     //while dongusunu kontrol etmek icin secenek ve data degiskenini esitliyorum.

    }
        }

}
