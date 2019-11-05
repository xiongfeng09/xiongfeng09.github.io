package annotation;


@Description(desc = "this is user in db", author = "xiongfeng")
@Table("user")
public class User {
    @Column("user_name")
    private String name;
    @Column("user_age")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
