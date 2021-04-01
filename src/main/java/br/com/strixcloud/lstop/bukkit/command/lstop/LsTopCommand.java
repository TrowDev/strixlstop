package br.com.strixcloud.lstop.bukkit.command.lstop;

import br.com.strixcloud.lstop.bukkit.command.lstop.sub.CreateHologramSub;
import br.com.strixcloud.lstop.bukkit.command.lstop.sub.DeleteHologramSub;
import br.com.strixcloud.lstop.entities.Messages;
import br.com.strixcloud.lstop.entities.util.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class LsTopCommand implements CommandExecutor {

    private final List<SubCommand> subCommands;

    public LsTopCommand() {
        subCommands = new ArrayList<>();

        subCommands.add(new CreateHologramSub());
        subCommands.add(new DeleteHologramSub());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length > 0) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(Messages.ERROR_NOT_PLAYER.getMessage());
                return false;
            }
            String currentArg = args[0];
            for (SubCommand subCmd : subCommands) {
                if (currentArg.equalsIgnoreCase(subCmd.getName()) || subCmd.getAlias().contains(currentArg)) {
                    if (sender.hasPermission(subCmd.getPermission()) || subCmd.getPermission().isEmpty()) {
                        subCmd.execute(sender, args);
                        return true;
                    }
                    sender.sendMessage(Messages.ERROR_NO_PERMISSION.getMessage());
                    return false;
                }
            }
        }
        sender.sendMessage(Messages.HELP_MESSAGE.getMessage());
        return false;
    }
}
