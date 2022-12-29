

public class Node {
// Gerekli değişkenleri oluştururuz.
    Node prev;
    Node next;
    Student data;

    // Constructor metot kullanarak değişkenleri eşitleriz.
    public Node(Student data){
    this.data = data;
    prev = null;
    next = null;
}

}
