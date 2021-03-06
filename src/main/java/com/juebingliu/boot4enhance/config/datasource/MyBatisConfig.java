package com.juebingliu.boot4enhance.config.datasource;

import com.juebingliu.boot4enhance.config.SqlCostInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author juebing
 * @date 2018/12/11 17:52
 * @description
 */
@Configuration
@MapperScan(basePackages = "com.juebingliu.boot4enhance.mapper")
public class MyBatisConfig {

    @Autowired
    private Environment env;

    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.hikari.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @ConfigurationProperties(prefix="spring.datasource.hikari.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }


    /**
     * @Primary 该注解表示在同一个接口有多个实现类可以注入的时候，默认选择哪一个，而不是让@autowire注解报错
     * @Qualifier 根据名称进行注入，通常是在具有相同的多个类型的实例的一个注入（例如有多个DataSource类型的实例）
     */
    @Bean
    @Primary
    public DynamicDataSource dataSource(@Qualifier("primaryDataSource") DataSource primaryDataSource,
                                            @Qualifier("secondaryDataSource") DataSource secondaryDataSource){
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.test, primaryDataSource);
        targetDataSources.put(DatabaseType.test2, secondaryDataSource);

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
        dataSource.setDefaultTargetDataSource(primaryDataSource);// 默认的datasource设置为myTestDbDataSource

        return dataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
//    @Bean
//    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
//        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
//        fb.setDataSource(ds);// 指定数据源(这个必须有，否则报错)
//        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
//        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));// 指定基包
//        fb.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//
//
//        return fb.getObject();
//    }
    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("primaryDataSource") DataSource myTestDbDataSource,
                                               @Qualifier("secondaryDataSource") DataSource myTestDb2DataSource) throws Exception{
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(this.dataSource(myTestDbDataSource, myTestDb2DataSource));
        fb.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        fb.setPlugins(new Interceptor[]{new SqlCostInterceptor()});
        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

}