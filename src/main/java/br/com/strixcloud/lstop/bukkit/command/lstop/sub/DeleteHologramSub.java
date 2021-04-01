package br.com.strixcloud.lstop.bukkit.command.lstop.sub;

import br.com.strixcloud.lstop.entities.Messages;
import br.com.strixcloud.lstop.entities.util.SubCommand;
import br.com.strixcloud.lstop.services.hologram.create.HologramCreateController;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteHologramSub extends SubCommand {

    public DeleteHologramSub() {
        super("deletehologram", "", "strixlstop.delete");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        HologramCreateController.getInstance().handle(((Player) sender).getLocation());

        sender.sendMessage(Messages.HOLOGRAM_DELETED.getMessage());
    }
}
