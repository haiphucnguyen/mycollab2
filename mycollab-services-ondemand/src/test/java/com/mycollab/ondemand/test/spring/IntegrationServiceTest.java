package com.mycollab.ondemand.test.spring;

import com.mycollab.cache.service.CacheService;
import com.mycollab.module.file.service.AbstractStorageService;
import org.springframework.boot.test.mock.mockito.MockBean;

public class IntegrationServiceTest extends com.mycollab.test.spring.IntegrationServiceTest {
    @MockBean
    private CacheService cacheService;

    @MockBean
    private AbstractStorageService storageService;
}
