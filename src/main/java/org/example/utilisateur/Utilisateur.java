package org.example.utilisateur;

import java.util.Scanner;

public class Utilisateur {
    private Contact contact;
    private String password ;
    protected String id;
    protected String username ;


    public Utilisateur(Contact contact, String password, String id, String username) {
        this.contact = contact;
        this.password = password;
        this.id = id;
        this.username = username;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
