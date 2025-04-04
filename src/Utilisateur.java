import java.util.Scanner;

public class Utilisateur {
    private Contact contact;
    private String password ;


    public Utilisateur(Contact contact, String password) {
        this.contact = contact;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "contact=" + contact +
                ", password='" + password + '\'' +
                '}';
    }
    public  void seConnecter(){
        System.out.print("Entrez votre mot de passe pour vous connecter : ");
        String  password;
        Scanner sc = new Scanner(System.in);
        do {
            password = sc.nextLine();
            if (!password.equals(this.getPassword())){
                System.out.println("Erreur ! Entrez le bon mot de passe");
            }
            else {
                System.out.println("Bienvenu " + this.getContact().getNom());
            }
        }while (!password.equals(this.getPassword()));
    }
}
