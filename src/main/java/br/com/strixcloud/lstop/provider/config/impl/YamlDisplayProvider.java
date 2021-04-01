package br.com.strixcloud.lstop.provider.config.impl;

import br.com.strixcloud.lstop.entities.HologramData;
import br.com.strixcloud.lstop.entities.util.ConfigFile;
import br.com.strixcloud.lstop.provider.config.IDisplayProvider;
import lombok.RequiredArgsConstructor;
import lombok.var;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class YamlDisplayProvider implements IDisplayProvider {
    private final ConfigFile config;

    private HologramData hologramData;
    private String legendChatTag;

    @Override
    public void load() {
        var cs = config.getConfig().getConfigurationSection("Display");

        legendChatTag = cs.getString("lc-tag").replace("&", "§");

        var holoHeader = replaceAll(cs.getStringList("hologram.header"));
        var holoFooter = replaceAll(cs.getStringList("hologram.footer"));
        var holoValid = cs.getString("hologram.content.valid").replace("&", "§");
        var holoInvalid = cs.getString("hologram.content.invalid").replace("&", "§");

        var holoSize = cs.getInt("hologram.size");

        hologramData = new HologramData(holoHeader, holoFooter, holoValid, holoInvalid, holoSize);
    }

    @Override
    public String getLegendChatTag() {
        return legendChatTag;
    }

    @Override
    public HologramData getHologramData() {
        return hologramData;
    }

    private List<String> replaceAll(List<String> list) {
        return list.stream()
                .map(s -> s.replace("&", "§"))
                .collect(Collectors.toList());
    }

}
