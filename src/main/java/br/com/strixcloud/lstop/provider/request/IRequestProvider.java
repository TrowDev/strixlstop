package br.com.strixcloud.lstop.provider.request;

import br.com.strixcloud.lstop.entities.util.HttpResponse;

import java.io.IOException;

public interface IRequestProvider {

    void auth(String keyApi);

    HttpResponse get(String url) throws IOException;

}
