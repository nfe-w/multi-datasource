package com.nfe.multidatasource.database.config;

import com.nfe.multidatasource.database.enums.DataSourceEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 多数据源配置类
 *
 * @author nfe-w
 * @date 2021-12-07 17:06
 */

@Configuration
@EnableTransactionManagement
public class DynamicDataSourceConfig {

    @Bean
    @Primary
    public DynamicDataSourceRouting dynamicDataSource(@Qualifier("mainSourceWrapper") DataSource mainDataSource,
                                                      @Qualifier("db2SourceWrapper") DataSource db2DataSource,
                                                      @Qualifier("db3SourceWrapper") DataSource db3DataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>(8);
        targetDataSource.put(DataSourceEnum.MAIN.getValue(), mainDataSource);
        targetDataSource.put(DataSourceEnum.DB2.getValue(), db2DataSource);
        targetDataSource.put(DataSourceEnum.DB3.getValue(), db3DataSource);
        return new DynamicDataSourceRouting(mainDataSource, targetDataSource);
    }

    @Primary
    @Bean("mainJdbcTemplate")
    public JdbcTemplate mainJdbcTemplate(@Qualifier("mainSourceWrapper") DataSource mainDataSource) {
        return new JdbcTemplate(mainDataSource);
    }

    @Bean("db2JdbcTemplate")
    public JdbcTemplate db2JdbcTemplate(@Qualifier("db2SourceWrapper") DataSource db2DataSource) {
        return new JdbcTemplate(db2DataSource);
    }

    @Bean("db3JdbcTemplate")
    public JdbcTemplate db3JdbcTemplate(@Qualifier("db3SourceWrapper") DataSource db3DataSource) {
        return new JdbcTemplate(db3DataSource);
    }

    @Primary
    @Bean("mainPlatformTransactionManager")
    public PlatformTransactionManager mainPlatformTransactionManager(@Qualifier("mainSourceWrapper") DataSource mainDataSource) {
        return new DataSourceTransactionManager(mainDataSource);
    }

    @Bean("db2PlatformTransactionManager")
    public PlatformTransactionManager db2PlatformTransactionManager(@Qualifier("db2SourceWrapper") DataSource db2DataSource) {
        return new DataSourceTransactionManager(db2DataSource);
    }

    @Bean("db3PlatformTransactionManager")
    public PlatformTransactionManager db3PlatformTransactionManager(@Qualifier("db3SourceWrapper") DataSource db3DataSource) {
        return new DataSourceTransactionManager(db3DataSource);
    }

}
