package mapper;

public class UserMapper {
    protected String username;
    protected String password;
    protected char gender;

    public UserMapper(String username, String password, char gender) {
        this.username = username;
        this.password = password;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserMapper{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                '}';
    }
}
