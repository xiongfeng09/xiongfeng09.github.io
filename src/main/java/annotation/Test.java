package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by xiongfeng on 15/9/22.
 */
public class Test {
  public static void main(String[] args) {
    User user = new User();
    user.setAge(25);
    user.setName("xiongfeng");

    try {
      Class userClass = Class.forName("annotation.User");

      if (userClass.isAnnotationPresent(Table.class)) {
        Table t = (Table)userClass.getAnnotation(Table.class);
        String tableName = t.value();

        for (Field f: userClass.getDeclaredFields()) {
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
