package com.nfe.multidatasource.database.wrapper;

import com.alibaba.druid.filter.config.ConfigFilter;
import com.alibaba.druid.filter.encoding.EncodingConvertFilter;
import com.alibaba.druid.filter.logging.CommonsLogFilter;
import com.alibaba.druid.filter.logging.Log4j2Filter;
import com.alibaba.druid.filter.logging.Log4jFilter;
import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: 数据源-db2
 *
 * @author nfe-w
 * @date 2021-12-07 17:02
 */

@Component
@ConfigurationProperties("spring.datasource.druid.db2")
public class Db2SourceWrapper extends DruidDataSource implements InitializingBean {

    private String url;
    private String username;
    private String password;
    private String driverClassName;

    public Db2SourceWrapper() {
    }

    @Override
    public void afterPropertiesSet() {

        if (super.getUsername() == null) {
            super.setUsername(username);
        }
        if (super.getPassword() == null) {
            super.setPassword(password);
        }
        if (super.getUrl() == null) {
            super.setUrl(url);
        }
        if (super.getDriverClassName() == null) {
            super.setDriverClassName(driverClassName);
        }

        // 此处可对数据库密码进行加密解密
        String password = getPassword();
        setPassword(password);
    }

    @Autowired(required = false)
    public void addStatFilter(StatFilter statFilter) {

        super.filters.add(statFilter);
    }

    @Autowired(required = false)
    public void addConfigFilter(ConfigFilter configFilter) {

        super.filters.add(configFilter);
    }

    @Autowired(required = false)
    public void addEncodingConvertFilter(EncodingConvertFilter encodingConvertFilter) {

        super.filters.add(encodingConvertFilter);
    }

    @Autowired(required = false)
    public void addSlf4jLogFilter(Slf4jLogFilter slf4jLogFilter) {

        super.filters.add(slf4jLogFilter);
    }

    @Autowired(required = false)
    public void addLog4jFilter(Log4jFilter log4jFilter) {

        super.filters.add(log4jFilter);
    }

    @Autowired(required = false)
    public void addLog4j2Filter(Log4j2Filter log4j2Filter) {

        super.filters.add(log4j2Filter);
    }

    @Autowired(required = false)
    public void addCommonsLogFilter(CommonsLogFilter commonsLogFilter) {

        super.filters.add(commonsLogFilter);
    }

    @Autowired(required = false)
    public void addWallFilter(WallFilter wallFilter) {

        super.filters.add(wallFilter);
    }

    /**
     * Ignore the 'maxEvictableIdleTimeMillis < minEvictableIdleTimeMillis' validate,
     * it will be validated again in {@link DruidDataSource#init()}.
     * <p>
     * for fix issue #3084, #2763
     *
     * @since 1.1.14
     */
    @Override
    public void setMaxEvictableIdleTimeMillis(long maxEvictableIdleTimeMillis) {

        try {
            super.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
        } catch (IllegalArgumentException ignore) {
            super.maxEvictableIdleTimeMillis = maxEvictableIdleTimeMillis;
        }
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getDriverClassName() {
        return driverClassName;
    }

    @Override
    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
