package com.nfe.multidatasource.database.enums;

/**
 * Description: 数据源标识
 *
 * @author nfe-w
 * @date 2021-12-07 18:53
 */

public enum DataSourceEnum {
    /**
     * main数据库
     */
    MAIN("main"),
    /**
     * db2数据库
     */
    DB2("db2"),
    /**
     * db3数据库
     */
    DB3("db3");

    /**
     * 数据库标识
     */
    private final String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "DataSourceEnum{" +
                "value='" + value + '\'' +
                '}';
    }
}
