import java.util.Scanner;

public class Client {
    private final String name, email, password;
    private final int id;

    public Client(String name, String email, String password, int id){
        this.name = name;
        this.email = email;
        this.password = password;
        this.id = id;
    }

    public Client(){
        this.name = setName();
        this.email = setEmail();
        this.password = setPassword();
        this.id = setId();
    }

    public String setName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input your username: ");
        return sc.nextLine();
    }

    public String getName() {
        return name;
    }

    public String setEmail() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input your email: ");
        return sc.nextLine();
    }

    public String getEmail() {
        return email;
    }

    public String setPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input your password");
        return sc.nextLine();
    }

    public String getPassword() {
        return password;
    }

    public int setId() {
        return (int) (Math.random()*1000);
    }

    @Override
    public String toString() {
        return this.name + ";" + this.email + ";" + this.password + ";" + this.id;
    }
}
