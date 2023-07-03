package com.market.store.api;

import com.market.common.docs.BaseWebMvcTest;
import com.market.common.docs.Documentation;
import com.market.store.application.ItemApplicationService;
import com.market.store.dto.ItemModifyDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.Schema.schema;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ItemController.class)
class ItemControllerTest extends BaseWebMvcTest {

    private final String tag = "상품";
    private final String mappingPath = "/v1/store/items";

    @MockBean
    private ItemApplicationService itemApplicationService;

    @Test
    public void getItem_call_success() throws Exception {
        // given

        // when
        ResultActions actions = mockMvc().perform(get(mappingPath + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk());

        actions.andDo(new Documentation(tag, "상품 상세 정보")
                .parameters(builder -> builder
                        .pathParameters(
                                parameterWithName("id").description("상품 ID")
                        )
                        .build()
                )
                .write());
    }

    @Test
    public void modifyItem_call_success() throws Exception {
        // given
        ItemModifyDto param = ItemModifyDto.builder()
                .name("test item2")
                .price(2000L)
                .build();
        String paramJson = toJson(param);

        // when
        ResultActions actions = mockMvc().perform(put(mappingPath + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(paramJson));

        // then
        actions.andExpect(status().isOk());

        actions.andDo(new Documentation(tag, "상품 정보 수정")
                .parameters(builder -> builder
                        .pathParameters(
                                parameterWithName("id").description("상품 ID")
                        )
                        .requestSchema(schema(ItemModifyDto.class.getSimpleName()))
                        .requestFields(
                                fieldWithPath("name").description("상품명").type(JsonFieldType.STRING),
                                fieldWithPath("price").description("가격").type(JsonFieldType.NUMBER)
                        )
                        .build()
                )
                .write());
    }

    @Test
    public void deleteItem_call_success() throws Exception {
        // when
        ResultActions actions = mockMvc().perform(delete(mappingPath + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        actions.andExpect(status().isOk());

        actions.andDo(new Documentation(tag, "상품 삭제")
                .parameters(builder -> builder
                        .pathParameters(
                                parameterWithName("id").description("상품 ID")
                        )
                        .build()
                )
                .write());
    }
}