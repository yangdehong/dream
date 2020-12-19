package com.ydh.redsheep.self_springmvc.framework.annotations;

import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MySecurity {
    String[] value();
}
