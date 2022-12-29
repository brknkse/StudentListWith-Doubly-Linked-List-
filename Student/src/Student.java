
import java.util.ArrayList;

public class Student {
    // Gerekli değişkenleri ve arrayleri oluştururuz.
    int ogrenciNumarasi;
    String adSoyad;
    ArrayList<String> numaralar;

    // Constructor metot kullanarak değişkenleri eşitleriz.
    public Student(String adSoyad,int ogrenciNumarasi,ArrayList<String> numaralar){
        this.adSoyad = adSoyad;
        this.ogrenciNumarasi = ogrenciNumarasi;
        this.numaralar = numaralar;
    }

    // Getter ve Setter metotları oluştururuz.
    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public int getogrenciNumarasi() {
        return ogrenciNumarasi;
    }

    public void setogrenciNumarasi(int ogrenciNumarasi) {
        this.ogrenciNumarasi = ogrenciNumarasi;
    }

    public ArrayList<String> getNumaralar() {
        return numaralar;
    }

    public void setNumaralar(ArrayList<String> numaralar) {
        this.numaralar = numaralar;
    }

    // toString methodu kullanırız.
    @Override
    public String toString() {
        return ogrenciNumarasi +" "+ adSoyad  +" "+numaralar;
    }
}
