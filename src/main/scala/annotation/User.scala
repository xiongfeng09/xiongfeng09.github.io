package annotation

@Table("scalaUser")
class ScalaUser {
  @Column("user_name")
  var name: String = ""
  @Column("user_age")
  var age: Int = 0
}
