package br.com.strixcloud.lstop.task;

import br.com.strixcloud.lstop.services.lojasquare.load.LojasquareLoadController;

import java.util.TimerTask;

public class LojasquareUpdateTask extends TimerTask {
    @Override
    public void run() {
        LojasquareLoadController.getInstance().handle();
    }
}
