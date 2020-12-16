package com.ydh.redsheep.self_spring.anno;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyRepository {
    @AliasFor(
            annotation = MyComponent.class
    )
    String value() default "";
}
