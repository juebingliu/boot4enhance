package com.juebingliu.boot4enhance.config.aspect;

import com.juebingliu.boot4enhance.config.datasource.DatabaseContextHolder;
import com.juebingliu.boot4enhance.config.datasource.DatabaseType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author juebing
 * @date 2018/12/11 18:03
 * @description
 */
@Component
@Aspect
public class PrimaryDataSourceAspect {

    private Logger logger = LoggerFactory.getLogger(PrimaryDataSourceAspect.class);

    @Pointcut("execution(* com.juebingliu.boot4enhance.mapper.one..*(..))")
    public void declareJoinPointExpression(){}

    @Before("declareJoinPointExpression()")
    public void setDataSourceKey(JoinPoint point){
        logger.info("【当前数据源：test】");
        DatabaseContextHolder.setDatabaseType(DatabaseType.test);
    }
}