package com.eolink.support.controller;

import com.eolink.common.domain.dto.BaseResponse;
import com.eolink.support.service.AutoScanService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自动扫描添加api
 * @author sunanzhi work
 */
@RestController
@RequestMapping("/autoScan")
public class AutoScanController {

    private final AutoScanService autoScanService;

    public AutoScanController(AutoScanService autoScanService) {
        this.autoScanService = autoScanService;
    }

    @GetMapping("/gitlab")
    public BaseResponse<Boolean> gitlab() {
        return BaseResponse.success(this.autoScanService.gitlab());
    }

    @PostMapping("/github")
    public BaseResponse<Boolean> github() {
        return BaseResponse.success(this.autoScanService.github());
    }
}
