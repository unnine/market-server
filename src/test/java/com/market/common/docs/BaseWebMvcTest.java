package com.market.common.docs;

import com.epages.restdocs.apispec.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.function.Consumer;

@AutoConfigureRestDocs
@WebMvcTest
@ExtendWith(RestDocumentationExtension.class)
public class BaseWebMvcTest {

    protected MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .build();
    }

    protected RestDocumentationResultHandler documentation(String identifier, Consumer<ResourceSnippetParametersBuilder> builderConfigurer) {
        ResourceSnippetParameters parameters = createResourceSnippetParameters(identifier, builderConfigurer);
        ResourceSnippet resourceSnippet = ResourceDocumentation.resource(parameters);

        return MockMvcRestDocumentationWrapper.document(
                identifier,
                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                resourceSnippet
        );
    }

    private ResourceSnippetParameters createResourceSnippetParameters(String identifier, Consumer<ResourceSnippetParametersBuilder> builderConfigurer) {
        ResourceSnippetParametersBuilder builder = ResourceSnippetParameters.builder().summary(identifier + " API");
        builderConfigurer.accept(builder);
        return builder.build();
    }

}
