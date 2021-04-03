package br.com.strixcloud.lstop.entities.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class HttpResponse {

    @Getter private final int CODE;
    @Getter private final JsonElement OBJECT;
    @Getter private final long MS;

}
