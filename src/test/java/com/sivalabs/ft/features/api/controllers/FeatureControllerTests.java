package com.sivalabs.ft.features.api.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import com.sivalabs.ft.features.AbstractIT;
import com.sivalabs.ft.features.domain.dtos.FeatureDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class FeatureControllerTests extends AbstractIT {

    @Test
    void shouldGetFeaturesByReleaseCode() {
        var result = mvc.get()
                .uri("/api/features?releaseCode={code}", "IDEA-2023.3.8")
                .exchange();
        assertThat(result)
                .hasStatusOk()
                .bodyJson()
                .extractingPath("$.size()")
                .asNumber()
                .isEqualTo(2);
    }

    @Test
    void shouldGetFeatureByCode() {
        String code = "IDEA-1";
        var result = mvc.get().uri("/api/features/{code}", code).exchange();
        assertThat(result).hasStatusOk().bodyJson().convertTo(FeatureDto.class).satisfies(dto -> {
            assertThat(dto.code()).isEqualTo(code);
        });
    }

    @Test
    void shouldReturn404WhenFeatureNotFound() {
        var result = mvc.get().uri("/api/features/{code}", "INVALID_CODE").exchange();
        assertThat(result).hasStatus(HttpStatus.NOT_FOUND);
    }
}
