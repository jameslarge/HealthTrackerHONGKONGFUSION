package Model;

public class Member extends User{
    
    String forename;
    String surname;
    //Goal goals;
    //Health health;
    

    public Member(String username, String password, String email, String forename, String surname) {
        super(username, password, email);
        this.forename = forename;
        this.surname = surname;
    }
    
    
    
}
