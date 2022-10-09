package com.eolink.support.service.impl;

import com.eolink.support.service.AutoScanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author sunanzhi work
 */
@Slf4j
@Service
public class AutoScanServiceImpl implements AutoScanService {
    @Override
    public Boolean gitlab() {
        log.error("im error message");
        return false;
    }

    @Override
    public Boolean github() {
        return true;
    }
}
