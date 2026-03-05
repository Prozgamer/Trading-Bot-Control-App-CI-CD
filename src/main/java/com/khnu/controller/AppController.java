package com.khnu.controller;

import com.khnu.model.AppConfigurations;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
// Додаємо опис для Swagger
@Tag(name = "Application Info", description = "API для отримання налаштувань програми")
public class AppController {

    private final AppConfigurations appConfigurations;

    public AppController(AppConfigurations appConfigurations) {
        this.appConfigurations = appConfigurations;
    }

    @Operation(
            summary = "Отримати інформацію про додаток",
            description = "Повертає версію додатка та статус тестового режиму з файлу application.yml"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Успішно отримано дані"),
            @ApiResponse(responseCode = "500", description = "Внутрішня помилка сервера")
    })
    @GetMapping("info")
    public AppConfigurations getInfo() {
        return this.appConfigurations;
    }
}