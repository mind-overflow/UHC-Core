package wtf.beatrice.uhccore.completers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class InfoCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args)
    {
        List<String> list = new ArrayList<String>();
        if(args.length == 1)
        {
            list.add("help");
            list.add("setspawn");
            list.add("setfirework");
            list.add("start");
            list.add("reload");
            list.add("list");
            if(args[0].startsWith("h"))
            {
                list.clear();
                list.add("help");
            } else
            if(args[0].startsWith("s"))
            {
                list.clear();
                list.add("setspawn");
                list.add("setfirework");
                list.add("start");

            } else
            if(args[0].startsWith("r"))
            {
                list.clear();
                list.add("reload");
            } else
            if(args[0].equalsIgnoreCase("list"))
            {
                list.clear();
                list.add("list");
            }
        }
        return list;
    }
}
