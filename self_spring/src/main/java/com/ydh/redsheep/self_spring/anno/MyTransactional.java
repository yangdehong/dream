package com.ydh.redsheep.self_spring.anno;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyTransactional {
}
