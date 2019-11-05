package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by xiongfeng on 15/9/22.
 */
public class TestScala {
  public static void main(String[] args) {
    ScalaUser user = new ScalaUser();
    user.age_$eq(24);
    user.name_$eq("xiongfeng");

    try {
      Class userClass = Class.forName("annotation.ScalaUser");

      if (userClass.isAnnotationPresent(Table.class)) {
        Table t = (Table)userClass.getAnnotation(Table.class);
        String tableName = t.value();

        System.out.println(userClass.getDeclaredFields());

        for (Field f: userClass.getDeclaredFields()) {
          System.out.println(f.getName());

          if(!f.isAnnotationPresent(Column.class))
            continue;

          Column c = (Column) f.getAnnotation(Column.class);

          String columnName = c.value();


          String fieldName = f.getName();
          String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());

          System.out.println(columnName);
          System.out.println(fieldName);
          System.out.println(methodName);

          Method m = userClass.getDeclaredMethod(methodName);

          System.out.println(m.invoke(user));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
