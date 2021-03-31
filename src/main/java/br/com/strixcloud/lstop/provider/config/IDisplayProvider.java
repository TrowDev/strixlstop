package br.com.strixcloud.lstop.provider.config;

import br.com.strixcloud.lstop.entities.HologramData;

public interface IDisplayProvider {

    void load();

    String getLegendChatTag();

    HologramData getHologramData();

}
