package com.nfe.multidatasource.service.impl;

import com.nfe.multidatasource.dao.TestDao;
import com.nfe.multidatasource.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author nfe-w
 * @date 2021-12-18 13:57
 */
@Service
public class TestServiceImpl implements TestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);

    private final TestDao testDao;

    public TestServiceImpl(TestDao testDao) {
        this.testDao = testDao;
    }

    @Override
    public void execute() {
        LOGGER.debug("countForMain: {}", testDao.countForMain());
        LOGGER.debug("countForDb2: {}", testDao.countForDb2());
        LOGGER.debug("countForDb3: {}", testDao.countForDb3());
    }
}
