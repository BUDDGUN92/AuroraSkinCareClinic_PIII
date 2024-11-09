package classes.users;

public class Patient extends Person {
    private String nic;

    public Patient(int userId, String name, String email, String phone, String nic) {
        super(userId, name, email, phone);
        this.nic = nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
    
    public String getNic() {
        return nic;
    }

    public void printPatient() {
        super.printPerson();
        System.out.println("NIC: " + nic);
    }
}
