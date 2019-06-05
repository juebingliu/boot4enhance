package com.juebingliu.boot4enhance.config.datasource;

/**
 * @author juebing
 * @date 2018/12/11 17:49
 * @description
 */
public class DatabaseContextHolder {
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type){
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType(){
        return contextHolder.get();
    }
}