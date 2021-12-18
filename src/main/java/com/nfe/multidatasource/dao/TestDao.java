package com.nfe.multidatasource.dao;

import com.nfe.multidatasource.database.annotation.DS;
import com.nfe.multidatasource.database.enums.DataSourceEnum;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author nfe-w
 * @date 2021-12-18 13:52
 */
@Repository
public interface TestDao {

    /**
     * 查询main的数量
     *
     * @return 数量
     */
    @DS(DataSourceEnum.MAIN)
    @Select("SELECT COUNT(*) FROM TSYS_USER")
    int countForMain();

    /**
     * 查询db2的数量
     *
     * @return 数量
     */
    @DS(DataSourceEnum.DB2)
    @Select("SELECT COUNT(*) FROM TSYS_USER")
    int countForDb2();

    /**
     * 查询db3的数量
     *
     * @return 数量
     */
    @DS(DataSourceEnum.DB3)
    @Select("SELECT COUNT(*) FROM TSYS_USER")
    int countForDb3();
}
