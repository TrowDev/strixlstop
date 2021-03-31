package br.com.strixcloud.lstop.services.lojasquare.load;

import br.com.strixcloud.lstop.data.AccountsDAO;
import br.com.strixcloud.lstop.provider.lojasquare.ILSProvider;
import br.com.strixcloud.lstop.provider.log.IStrixLogger;
import lombok.RequiredArgsConstructor;
import lombok.var;

import java.io.IOException;

@RequiredArgsConstructor
public class LojasquareLoadService {

    private final ILSProvider lsProvider;
    private final IStrixLogger logger;

    public boolean execute() {
        try {
            AccountsDAO.getInstance().clear();

            for (var acc : lsProvider.getAccounts()) {
                AccountsDAO.getInstance().add(acc);
            }

            var size = AccountsDAO.getInstance().get().size();
            logger.info(String.format("Successfully loaded %s top players", size));
        } catch (IOException exception) {
            logger.error("An error occurred when trying to login in Lojasquare API, disabling plugin.");
            return false;
        }
        return true;
    }

}
