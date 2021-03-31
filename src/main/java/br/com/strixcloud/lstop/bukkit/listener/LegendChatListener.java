package br.com.strixcloud.lstop.bukkit.listener;

import br.com.devpaulo.legendchat.api.events.ChatMessageEvent;
import br.com.strixcloud.lstop.data.AccountsDAO;
import br.com.strixcloud.lstop.provider.config.IDisplayProvider;
import lombok.AllArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

@AllArgsConstructor
public class LegendChatListener implements Listener {

    private final IDisplayProvider displayProvider;

    @EventHandler
    public void onChat(ChatMessageEvent e) {
        if (e.getTags().contains("top_donator")) {
            if (AccountsDAO.getInstance().has(e.getSender().getDisplayName())) {
                e.setTagValue("top_donator", displayProvider.getLegendChatTag());
            }
        }
    }

}
