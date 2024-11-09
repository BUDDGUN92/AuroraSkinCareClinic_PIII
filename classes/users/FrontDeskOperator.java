package classes.users;

public class FrontDeskOperator extends Person{
    private boolean isLoggedIn;
    private String password;

    public FrontDeskOperator(int userId,String name, String email, String phone, String password) {
        super(userId, name, email, phone);
        this.password = password;
        this.isLoggedIn = false;
    }

    public boolean login(String email, String password) {
        if (super.email.equals(email) && this.password.equals(password)) {
            isLoggedIn = true;
            return true;
        }
        return false;
    }

    public void logout() {
        isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
