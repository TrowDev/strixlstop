package br.com.strixcloud.lstop.services.lojasquare.load;

import br.com.strixcloud.lstop.StrixLSTop;
import lombok.Getter;
import lombok.var;

public class LojasquareLoadController {

    @Getter
    private static final LojasquareLoadController instance = new LojasquareLoadController();

    private final LojasquareLoadService service;

    public LojasquareLoadController() {
        var lsProvider = StrixLSTop.getInstance().getLsProvider();
        var logger = StrixLSTop.getInstance().getSLogger();
        this.service = new LojasquareLoadService(lsProvider, logger);
    }

    public boolean handle() {
        return service.execute();
    }

}
