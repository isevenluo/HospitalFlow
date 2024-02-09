package com.seventech.hospitalflow.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller              //定义api入口
@Tag(name = "Home Page")     //一般放在类上
public class IndexController {       //定义api的方法

    @Operation(summary = "Zugang zur System-Homepage", description = "用于测试用户登录成功的重定向API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "text/html")),
                    @ApiResponse(responseCode = "401", description = "未登录用户", content = @Content(mediaType = "text/html"))})

    @GetMapping("/home")                  //访问路径: GetMapping是简化写法
    public String home() {
        return "home";                      //默认寻找resources里的templates文件夹下面的home.html文件
    }

    @Operation(summary = "Zugang zur System-Homepage", description = "用于测试用户登录成功的重定向API",
            responses = {
                    @ApiResponse(responseCode = "200", description = "success", content = @Content(mediaType = "text/html")),
                    @ApiResponse(responseCode = "401", description = "未登录用户", content = @Content(mediaType = "text/html"))})
    @GetMapping("/index")//访问路径: GetMapping是简化写法
    public String index() {
        return "home";                      //默认寻找resources里的templates文件夹下面的home.html文件
    }

}
