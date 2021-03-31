package br.com.strixcloud.lstop.data;

import br.com.strixcloud.lstop.entities.data.TopAccount;
import br.com.strixcloud.lstop.entities.util.DAO;
import lombok.Getter;

public class AccountsDAO extends DAO<String, TopAccount> {

    @Getter
    private static final AccountsDAO instance = new AccountsDAO();

    @Override
    public void add(TopAccount value) {
        this.add(value.getPlayer(), value);
    }

    @Override
    public void remove(String key) {
        this.OBJECTS.remove(key);
    }
}
