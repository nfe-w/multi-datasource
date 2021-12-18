package com.nfe.multidatasource.database.annotation;


import com.nfe.multidatasource.database.enums.DataSourceEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 数据源切换注解
 *
 * @author nfe-w
 * @date 2021-12-07 17:02
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DS {
    DataSourceEnum value() default DataSourceEnum.MAIN;
}
