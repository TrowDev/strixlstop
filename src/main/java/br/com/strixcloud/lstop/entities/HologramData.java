package br.com.strixcloud.lstop.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor
public class HologramData {

    private List<String> header;
    private List<String> footer;

    private String contentValid;
    private String contentInvalid;

}
