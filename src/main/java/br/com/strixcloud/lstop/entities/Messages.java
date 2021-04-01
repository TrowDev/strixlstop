package br.com.strixcloud.lstop.entities;

import br.com.strixcloud.lstop.StrixLSTop;
import lombok.var;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Messages {

    TOP_DONATOR_HEADER("Messages.donator.header", Arrays.asList(" ", "&8&m-------------------------", "&8[&5&lStrixLSTop&8] &5Top doadores", " ")),
    TOP_DONATOR_FOOTER("Messages.donator.footer", Arrays.asList(" ", "&8&m-------------------------")),
    TOP_DONATOR_VALUE("Messages.donator.value", "&5@player &f- &d@value"),
    HOLOGRAM_CREATED("Messages.hologram-create", "&8[&5&lStrixLSTop&8] &fHolograma &acriado &fcom sucesso!"),
    HOLOGRAM_DELETED("Messages.hologram-delete", "&8[&5&lStrixLSTop&8] &fHolograma &cdeletado &fcom sucesso!"),
    ERROR_NO_PERMISSION("Messages.error-no-perm", "&8[&5&lStrixLSTop&8] &cSem permissao!"),
    ERROR_NOT_PLAYER("Messages.error-not-player", "&8[&5&lStrixLSTop&8] &cSomente jogadores podem utilizar esse comando"),
    ERROR_NOT_HOLOGRAM("Messages.error-not-hologram", "&8[&5&lStrixLSTop&8] &cHolographicDisplays não hookado"),
    HELP_MESSAGE("Messages.help", "&8[&5&lStrixLSTop&8] &fUtillize /lstop <createhologram|deletehologram>");

    private Object value;

    Messages(String key, Object defaultValue) {
        var config = StrixLSTop.getInstance().getConfigFile();
        if (config.getConfig().contains(key)) {
            value = config.getConfig().get(key);
        } else {
            config.getConfig().set(key, defaultValue);
            config.saveConfig();
            this.value = defaultValue;
            StrixLSTop.getInstance().getSLogger()
                    .warn(String.format("Created key '%s' at config, because that not exists", key));
        }
    }

    public String getMessage() {
        return ((String) value).replace("&", "§");
    }

    public List<String> getListMessage() {
        return ((List<String>) value)
                .stream()
                .map(s -> s.replace("&", "§"))
                .collect(Collectors.toList());
    }

}
