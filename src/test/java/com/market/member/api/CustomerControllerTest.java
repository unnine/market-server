package com.market.member.api;

import com.market.common.docs.BaseWebMvcTest;
import com.market.common.docs.Documentation;
import com.market.member.application.CustomerApplicationService;
import com.market.member.domain.vo.Phone;
import com.market.member.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.Schema.schema;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@MockBean(CustomerApplicationService.class)
class CustomerControllerTest extends BaseWebMvcTest {

    private final String tag = "고객";
    private final String mappingPath = "/v1/customers";

    @Test
    void getCustomerList_call_success() throws Exception {
        // given
        PageRequest pageRequest = PageRequest.of(0, 20);

        // when
        ResultActions actions = mockMvc().perform(get(mappingPath)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParam());

        // then
        actions.andExpect(status().isOk());

        actions.andDo(new Documentation(tag, "고객 목록").write());
    }

    @Test
    void getCustomer_call_success() throws Exception {
        // when
        ResultActions actions = mockMvc().perform(get(mappingPath + "/1")
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk());

        actions.andDo(new Documentation(tag, "고객 상세 정보").write());
    }

    @Test
    void signUpCustomer_call_success() throws Exception {
        // given
        CustomerDto param = CustomerDto.builder()
                .name("test name")
                .email("test@email.com")
                .phone(new Phone("01012345678"))
                .build();
        String paramJson = toJson(param);

        // when
        ResultActions actions = mockMvc().perform(post(mappingPath)
                .content(paramJson)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk());

        actions.andDo(new Documentation(tag, "고객 신규 등록")
                .parameters(builder -> builder
                        .requestSchema(schema(CustomerDto.class.getSimpleName()))
                        .requestFields(
                                fieldWithPath("id").ignored(),
                                fieldWithPath("email").description("이메일").type(JsonFieldType.STRING),
                                fieldWithPath("name").description("고객명").type(JsonFieldType.STRING),
                                subsectionWithPath("phone").description("연락처").type(JsonFieldType.OBJECT)
                        )
                        .build()
                )
                .write());
    }

    @Test
    void modifyCustomer_call_success() throws Exception {
        // given
        CustomerDto param = CustomerDto.builder()
                .id(1L)
                .name("test name")
                .email("test@email.com")
                .phone(new Phone("01012345678"))
                .build();
        String paramJson = toJson(param);

        // when
        ResultActions actions = mockMvc().perform(patch(mappingPath + "/1")
                .content(paramJson)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk());

        actions.andDo(new Documentation(tag, "고객 정보 수정")
                .parameters(builder -> builder
                        .requestSchema(schema(CustomerDto.class.getSimpleName()))
                        .requestFields(
                                fieldWithPath("id").description("고객 식별 번호").type(JsonFieldType.NUMBER),
                                fieldWithPath("name").description("고객명").type(JsonFieldType.STRING),
                                fieldWithPath("email").description("이메일").type(JsonFieldType.STRING),
                                subsectionWithPath("phone").description("연락처").type(JsonFieldType.OBJECT)
                        )
                        .build()
                )
                .write());
    }

}