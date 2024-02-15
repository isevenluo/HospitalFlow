package com.seventech.hospitalflow.controller;

import com.seventech.hospitalflow.dao.MedicineRepository;
import com.seventech.hospitalflow.entity.Medicine;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Seven Luo
 * @date 2024/2/14 21:39
 */

@RestController
@Tag(name = "药品库存管理")
@RequestMapping("/medicines")
public class MedicineController {

    @Resource
    private MedicineRepository medicineRepository;


    @PostMapping
    @Operation(
        summary = "新增药品",
        description = "新增药品",
        parameters = {
            @Parameter(name = "药品属性", description = "json格式",schema = @Schema(implementation = Medicine.class), required = true),
        },
        requestBody = @RequestBody(
            description = "药品包含的属性",
            required = true,
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Medicine.class)
            )
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Medicine.class))),
            @ApiResponse(responseCode = "400", description = "错误", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Medicine.class)))
        }
    )
    public Medicine addMedicine(@org.springframework.web.bind.annotation.RequestBody Medicine medicine) {

        return medicineRepository.save(medicine);

    }

}
