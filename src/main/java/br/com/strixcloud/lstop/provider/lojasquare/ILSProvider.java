package br.com.strixcloud.lstop.provider.lojasquare;

import br.com.strixcloud.lstop.entities.data.TopAccount;

import java.io.IOException;
import java.util.List;

public interface ILSProvider {

    List<TopAccount> getAccounts() throws IOException;

}
