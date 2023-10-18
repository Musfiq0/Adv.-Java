import java.util.Date;

public class Employee {
    private String id;
    private String name;
    private Date dateOfBirth;
    private String email;
    private Date joiningDate;

    public Employee(String id, String name, Date dateOfBirth, String email, Date joiningDate) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.joiningDate = joiningDate;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }
}

class Officer extends Employee {
    public Officer(String id, String name, Date dateOfBirth, String email, Date joiningDate) {
        super(id, name, dateOfBirth, email, joiningDate);
    }
}

class Staff extends Employee {
    public Staff(String id, String name, Date dateOfBirth, String email, Date joiningDate) {
        super(id, name, dateOfBirth, email, joiningDate);
    }
}