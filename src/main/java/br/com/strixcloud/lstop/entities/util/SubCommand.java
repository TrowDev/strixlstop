package br.com.strixcloud.lstop.entities.util;

import lombok.Data;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

@Data
public abstract class SubCommand {

    private String name;
    private String usage;
    private String permission;
    private List<String> alias;

    public SubCommand(String name, String usage, String permission, String... alias) {
        this.name = name;
        this.usage = usage;
        this.permission = permission;
        this.alias = Arrays.asList(alias);
    }

    public abstract void execute(CommandSender sender, String[] args);

}