package org.example.testproj;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Operation(
            summary = "Тестовый эндпоинт",
            description = "Возвращает список строк для проверки работы API и Swagger документации"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный ответ",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "array",
                                    example = "[\"test\", \"for\", \"hahaton\"]"
                            ),
                            examples = @ExampleObject(
                                    name = "Пример ответа",
                                    value = "[\"test\", \"for\", \"hahaton\"]"
                            )
                    )
            )
    })
    @GetMapping("/test")
    public List<String> testMethod() {
        return List.of("test", "for", "hahaton");
    }
}
