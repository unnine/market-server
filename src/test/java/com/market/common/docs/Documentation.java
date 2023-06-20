package com.market.common.docs;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceDocumentation;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.ResourceSnippetParametersBuilder;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.operation.preprocess.Preprocessors;

import java.util.Objects;
import java.util.function.Function;

public class Documentation {

    private final String tag;
    private final String identifier;
    private final ResourceSnippetParametersBuilder builder;
    private ResourceSnippetParameters parameters;

    public Documentation(String tag, String identifier) {
        this.tag = tag;
        this.identifier = identifier;
        this.builder = createBuilder();
    }

    private ResourceSnippetParametersBuilder createBuilder() {
        return ResourceSnippetParameters.builder()
                .tag(tag)
                .summary(identifier);
    }

    private ResourceSnippetParameters getParameters() {
        if (parameters == null) {
            return builder.build();
        }
        return parameters;
    }

    public RestDocumentationResultHandler write() {
        return MockMvcRestDocumentationWrapper.document(
                identifier,
                Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                ResourceDocumentation.resource(getParameters()));
    }

    public Documentation parameters(Function<ResourceSnippetParametersBuilder, ResourceSnippetParameters> parametersBuilder) {
        this.parameters = parametersBuilder.apply(builder);
        return this;
    }

}