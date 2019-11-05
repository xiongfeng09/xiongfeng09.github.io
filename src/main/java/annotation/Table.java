package annotation;

import java.lang.annotation.*;

/**
 * Created by xiongfeng on 15/9/22.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@interface Table {
    String value();
}
