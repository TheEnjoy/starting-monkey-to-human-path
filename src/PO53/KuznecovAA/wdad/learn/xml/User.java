package PO53.KuznecovAA.wdad.learn.xml;

public class User {
    private int right;
    private String mail;
    private String name;
    //public User(){}
    public User(String login, int right){this.mail=login;this.right = right;}
    public User(String mail, String name){this.mail=mail;this.name=name;}
   // public User(String newLogin, int newRightn){login=newLogin;right=newRightn;}
    public User(String name){this.name = name;}
    public int getRight() {
        return right;
    }
    public String getMail() {
        return mail;
    }
    public String getName() {
        return name;
    }
    public void setRight(int right) {
        this.right = right;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
}
