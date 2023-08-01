package com.market.store.api;

import com.market.common.docs.BaseWebMvcTest;
import com.market.common.docs.Documentation;
import com.market.store.application.ItemApplicationService;
import com.market.store.application.StoreApplicationService;
import com.market.store.dto.ItemRegisterDto;
import com.market.store.dto.StoreDto;
import com.market.store.dto.StoreModifyDto;
import com.market.store.dto.StoreRegisterDto;
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
@WebMvcTest(StoreController.class)
class StoreControllerTest extends BaseWebMvcTest {

    private final String tag = "가게";
    private final String mappingPath = "/api/v1/stores";

    @MockBean
    private StoreApplicationService storeApplicationService;

    @MockBean
    private ItemApplicationService itemApplicationService;

    @Test
    void getStoreList_call_success() throws Exception {
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

        actions.andDo(new Documentation(tag, "가게 목록")
                .parameters(builder -> builder
                        .queryParameters(
                                parameterWithName("page").description("조회 페이지 번호"),
                                parameterWithName("size").description("페이지 당 사이즈")
                        )
                        .responseSchema(schema(StoreDto.class.getSimpleName()))
                        .responseFields(
                                fieldWithPath("id").description("가게 ID").type(JsonFieldType.NUMBER).optional(),
                                fieldWithPath("name").description("이름").type(JsonFieldType.STRING).optional(),
                                subsectionWithPath("owner").description("판매자").type(JsonFieldType.OBJECT).optional()
                        )
                        .build()
                )
                .write());
    }

    @Test
    void getStore_call_success() throws Exception {
        // given
        when(storeApplicationService.getStore(any())).thenReturn(StoreDto.builder().build());

        // when
        ResultActions actions = mockMvc().perform(get(mappingPath + "/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk());

        actions.andDo(new Documentation(tag, "가게 상세 정보")
                .parameters(builder -> builder
                        .pathParameters(
                                parameterWithName("id").description("가게 ID")
                        )
                        .responseSchema(schema(StoreDto.class.getSimpleName()))
                        .responseFields(
                                fieldWithPath("id").description("가게 ID").type(JsonFieldType.NUMBER).optional(),
                                fieldWithPath("name").description("이름").type(JsonFieldType.STRING).optional(),
                                subsectionWithPath("owner").description("판매자").type(JsonFieldType.OBJECT).optional()
                        )
                        .build()
                )
                .write());
    }


    @Test
    void registerStore_call_success() throws Exception {
        // given
        StoreRegisterDto param = StoreRegisterDto.builder()
                .name("test store")
                .ownerId(1L)
                .build();
        String paramJson = toJson(param);

        // when
        ResultActions actions = mockMvc().perform(post(mappingPath)
                .contentType(MediaType.APPLICATION_JSON)
                .content(paramJson));

        // then
        actions.andExpect(status().isNoContent());

        actions.andDo(new Documentation(tag, "가게 등록")
                .parameters(builder -> builder
                        .requestSchema(schema(StoreRegisterDto.class.getSimpleName()))
                        .requestFields(
                                fieldWithPath("name").description("이름").type(JsonFieldType.STRING),
                                fieldWithPath("ownerId").description("판매자 ID").type(JsonFieldType.NUMBER)
                        )
                        .build()
                )
                .write());
    }

    @Test
    void modifyStore_call_success() throws Exception {
        // given
        StoreModifyDto param = StoreModifyDto.builder()
                .name("updated store name")
                .build();
        String paramJson = toJson(param);

        // when
        ResultActions actions = mockMvc().perform(put(mappingPath + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(paramJson));

        // then
        actions.andDo(new Documentation(tag, "가게 정보 수정")
                .parameters(builder -> builder
                        .pathParameters(
                                parameterWithName("id").description("가게 ID")
                        )
                        .requestSchema(schema(StoreModifyDto.class.getSimpleName()))
                        .requestFields(
                                fieldWithPath("name").description("이름").type(JsonFieldType.STRING)
                        )
                        .build()
                )
                .write());
    }

    @Test
    void deleteStore_call_success() throws Exception {
        // when
        ResultActions actions = mockMvc().perform(delete(mappingPath + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isNoContent());

        actions.andDo(new Documentation(tag, "가게 삭제")
                .parameters(builder -> builder
                        .pathParameters(
                                parameterWithName("id").description("가게 ID")
                        )
                        .build()
                )
                .write());
    }

    @Test
    void getItemList_call_success() throws Exception {
        // when
        ResultActions actions = mockMvc().perform(get(mappingPath + "/{id}/items", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk());

        actions.andDo(new Documentation(tag, "상품 목록")
                .parameters(builder -> builder
                        .pathParameters(
                            parameterWithName("id").description("가게 ID")
                        )
                        .build()
                )
                .write());
    }

    @Test
    void registerItems_call_success() throws Exception {
        // given
        ItemRegisterDto param = ItemRegisterDto.builder()
                .name("test item")
                .price(1000L)
                .build();
        String paramJson = toJson(param);

        // when
        ResultActions actions = mockMvc().perform(post(mappingPath + "/{id}/items", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(paramJson));

        // then
        actions.andExpect(status().isNoContent());

        actions.andDo(new Documentation(tag, "상품 등록")
                .parameters(builder -> builder
                        .pathParameters(
                                parameterWithName("id").description("가게 ID")
                        )
                        .requestSchema(schema(ItemRegisterDto.class.getSimpleName()))
                        .requestFields(
                                fieldWithPath("name").description("상품명").type(JsonFieldType.STRING),
                                fieldWithPath("price").description("가격").type(JsonFieldType.NUMBER)
                        )
                        .build()
                )
                .write());
    }

}