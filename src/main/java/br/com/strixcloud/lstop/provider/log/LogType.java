package br.com.strixcloud.lstop.provider.log;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum LogType {

    INFO("&f(INFO)", "INFO"),
    WARN("&e(WARN)", "WARN"),
    ERROR("&c(ERROR)", "ERROR");

    private final String console;
    private final String file;

}
