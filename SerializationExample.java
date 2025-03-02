import java.io.*;

class Student implements Serializable {
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + ", GPA=" + gpa + "]";
    }
}

public class SerializationExample {
    public static void main(String[] args) {
        // Serialize the Student object
        Student student = new Student(101, "John Doe", 3.75);
        
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("student.dat"))) {
            out.writeObject(student);
            System.out.println("Student object serialized successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        }
        
        // Deserialize the Student object
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("student.dat"))) {
            Student deserializedStudent = (Student) in.readObject();
            System.out.println("Student object deserialized successfully!");
            System.out.println(deserializedStudent);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
    }
}
