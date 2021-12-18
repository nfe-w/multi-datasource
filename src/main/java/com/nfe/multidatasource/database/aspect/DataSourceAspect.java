package com.nfe.multidatasource.database.aspect;

import com.nfe.multidatasource.database.annotation.DS;
import com.nfe.multidatasource.database.config.DynamicDataSourceContextHolder;
import com.nfe.multidatasource.database.enums.DataSourceEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Description: 动态数据源AOP
 *
 * @author nfe-w
 * @date 2021-12-07 17:10
 */

@Aspect
@Order(1)
@Component
public class DataSourceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

    @Pointcut("@annotation(com.nfe.multidatasource.database.annotation.DS)")
    public void annotationDsPointCut() {
    }

    @Pointcut("execution( * com.nfe..dao..*.*(..))")
    public void daoPointCut() {
    }

    @Around("annotationDsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Signature signature = point.getSignature();
        String className = signature.getDeclaringTypeName();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        String methodName = method.getName();
        DS ds = method.getAnnotation(DS.class);
        if (ds == null) {
            DynamicDataSourceContextHolder.setDataSource(DataSourceEnum.MAIN.getValue());
            LOGGER.debug("【数据源】默认: {}，类{}，方法{}", DataSourceEnum.MAIN.getValue(), className, methodName);
        } else {
            DynamicDataSourceContextHolder.setDataSource(ds.value().getValue());
            LOGGER.debug("【数据源】指定: {}，类{}，方法{}", ds.value().getValue(), className, methodName);
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSourceContextHolder.clearDataSource();
            LOGGER.debug("【数据源】清除");
        }
    }
}
