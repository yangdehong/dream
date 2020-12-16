package com.ydh.redsheep.self_spring.anno;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface MyComponent {

    String value() default "";

}
