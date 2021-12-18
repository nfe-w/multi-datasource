package com.nfe.multidatasource.database.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Description: 动态数据源路由
 *
 * @author nfe-w
 * @date 2021-12-10 15:09
 */

public class DynamicDataSourceRouting extends AbstractRoutingDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceRouting.class);

    public DynamicDataSourceRouting(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DynamicDataSourceContextHolder.getDataSource();
        LOGGER.debug("【数据源】调用determineCurrentLookupKey：{}，如果为null则会使用defaultTargetDataSource", dataSource);
        return dataSource;
    }
}
