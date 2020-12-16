package com.ydh.redsheep.self_spring.anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyService {
    @AliasFor(
            annotation = MyComponent.class
    )
    String value() default "";
}
