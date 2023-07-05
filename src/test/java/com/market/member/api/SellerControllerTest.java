package com.market.member.api;

import com.market.common.docs.BaseWebMvcTest;
import com.market.common.docs.Documentation;
import com.market.member.application.SellerApplicationService;
import com.market.member.dto.CustomerDto;
import com.market.member.dto.SellerDto;
import com.market.member.dto.SellerModifyDto;
import com.market.member.dto.SellerRegisterDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static com.epages.restdocs.apispec.Schema.schema;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.subsectionWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SellerController.class)
class SellerControllerTest extends BaseWebMvcTest {

    private final String tag = "판매자";
    private final String mappingPath = "/v1/member/sellers";

    @MockBean
    private SellerApplicationService sellerApplicationService;

    @Test
    void getSellerList_call_success() throws Exception {
        // given
        MultiValueMap<String, String> pageParam = new LinkedMultiValueMap<>();
        pageParam.add("page", "0");
        pageParam.add("size", "20");

        // when
        ResultActions actions = mockMvc().perform(get(mappingPath)
                .contentType(MediaType.APPLICATION_JSON)
                .queryParams(pageParam));

        // then
        actions.andExpect(status().isOk());

        actions.andDo(new Documentation(tag, "판매자 목록 조회")
                .parameters(builder -> builder
                        .queryParameters(
                                parameterWithName("page").description("조회 페이지 번호"),
                                parameterWithName("size").description("페이지 당 사이즈")
                        )
                        .responseSchema(schema(SellerDto.class.getSimpleName()))
                        .responseFields(
                                fieldWithPath("id").description("판매자 ID").type(JsonFieldType.NUMBER).optional(),
                                fieldWithPath("email").description("이메일").type(JsonFieldType.STRING).optional(),
                                fieldWithPath("name").description("이름").type(JsonFieldType.STRING).optional(),
                                subsectionWithPath("phone").description("연락처").type(JsonFieldType.OBJECT).optional()
                        )
                        .build()
                )
                .write());
    }

    @Test
    void getSeller_call_success() throws Exception {
        // given
        when(sellerApplicationService.getSeller(any())).thenReturn(SellerDto.builder().build());

        // when
        ResultActions actions = mockMvc().perform(get(mappingPath + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk());

        actions.andDo(new Documentation(tag, "판매자 상세 정보")
                .parameters(builder -> builder
                        .pathParameters(
                                parameterWithName("id").description("판매자 ID")
                        )
                        .responseSchema(schema(CustomerDto.class.getSimpleName()))
                        .responseFields(
                                fieldWithPath("id").description("판매자 ID").type(JsonFieldType.NUMBER).optional(),
                                fieldWithPath("email").description("이메일").type(JsonFieldType.STRING).optional(),
                                fieldWithPath("name").description("이름").type(JsonFieldType.STRING).optional(),
                                subsectionWithPath("phone").description("연락처").type(JsonFieldType.OBJECT).optional()
                        )
                        .build()
                )
                .write());
    }

    @Test
    void signUpSeller_call_success() throws Exception {
        // given
        SellerRegisterDto param = SellerRegisterDto.builder()
                .name("test seller")
                .email("test@email.com")
                .phoneNumber("01012345678")
                .build();
        String paramJson = toJson(param);

        // when
        ResultActions actions = mockMvc().perform(post(mappingPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(paramJson));

        // then
        actions.andExpect(status().isNoContent());

        actions.andDo(new Documentation(tag, "판매자 등록")
                .parameters(builder -> builder
                        .requestSchema(schema(SellerRegisterDto.class.getSimpleName()))
                        .requestFields(
                                fieldWithPath("email").description("이메일").type(JsonFieldType.STRING),
                                fieldWithPath("name").description("이름").type(JsonFieldType.STRING),
                                fieldWithPath("phoneNumber").description("모바일폰 번호").type(JsonFieldType.STRING)
                        )
                        .build()
                )
                .write());
    }

    @Test
    void modifyInfoSeller_call_success() throws Exception {
        // given
        SellerModifyDto param = SellerModifyDto.builder()
                .phoneNumber("01098765432")
                .build();
        String paramJson = toJson(param);

        // when
        ResultActions actions = mockMvc().perform(put(mappingPath + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(paramJson));

        // then
        actions.andExpect(status().isNoContent());

        actions.andDo(new Documentation(tag, "판매자 정보 수정")
                .parameters(builder -> builder
                        .requestSchema(schema(SellerModifyDto.class.getSimpleName()))
                        .requestFields(
                                fieldWithPath("phoneNumber").description("연락처").type(JsonFieldType.STRING)
                        )
                        .build()
                )
                .write());
    }

    @Test
    void withdrawSeller_call_success() throws Exception {
        // when
        ResultActions actions = mockMvc().perform(delete(mappingPath + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isNoContent());

        actions.andDo(new Documentation(tag, "판매자 정보 삭제")
                .parameters(builder -> builder
                        .pathParameters(
                                parameterWithName("id").description("판매자 ID")
                        )
                        .build()
                )
                .write());

    }

}