package com.seventech.hospitalflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for "/".
 *
 * @author Seven Luo
 */
@Controller
@Tag(name = "首页")
public class IndexController {

    @Operation(summary = "系统主页访问", description = "用于测试用户登录成功的重定向API",
        responses = {
            @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "text/html")),
            @ApiResponse(responseCode = "401", description = "为登录用户", content = @Content(mediaType = "text/html"))})
    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @Operation(summary = "系统主页访问", description = "用于测试用户登录成功的重定向API",
        responses = {
            @ApiResponse(responseCode = "200", description = "成功", content = @Content(mediaType = "text/html")),
            @ApiResponse(responseCode = "401", description = "为登录用户", content = @Content(mediaType = "text/html"))})
    @GetMapping("/index")
    public String index() {
        return "home";
    }


}