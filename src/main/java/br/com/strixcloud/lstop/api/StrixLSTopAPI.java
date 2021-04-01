package br.com.strixcloud.lstop.api;

import br.com.strixcloud.lstop.data.AccountsDAO;
import br.com.strixcloud.lstop.entities.data.TopAccount;
import br.com.strixcloud.lstop.services.lojasquare.load.LojasquareLoadController;

import java.util.List;

public class StrixLSTopAPI {

    /**
     * Get all accounts from loaded cache
     * @return List of accounts with value and player
     */
    public static List<TopAccount> getAccounts() {
        return AccountsDAO.getInstance().get();
    }

    /**
     * Forces cache update from Lojasquare API
     */
    public static void forceUpdate() {
        LojasquareLoadController.getInstance().handle();
    }

}
