package com.market.member.api;

import com.market.common.docs.BaseWebMvcTest;
import com.market.member.application.CustomerApplicationService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

@ExtendWith(MockitoExtension.class)
@MockBean(CustomerApplicationService.class)
class CustomerControllerTest extends BaseWebMvcTest {

    private final String documentTag = "고객";
    private final String mappingPath = "/v1/customers";

}