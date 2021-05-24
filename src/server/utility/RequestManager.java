package server.utility;

import common.*;

import java.io.Serializable;

public class RequestManager implements Serializable {
    private CommandManager commandManager;

    public RequestManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Reply manage(Request request) {
        CheckCode responseCode = executeCommand(request.getCommandName(), request.getArgument(), request.getObjectArgument());
        return new Reply(responseCode, ReplyManager.getAndClear());
    }

    private CheckCode executeCommand(String command, String argument, Object obj) {
        switch (command) {
            case "":
                break;
            case "loadCollection":
            case "help":
                if (!commandManager.help()) return CheckCode.ERROR;
                break;
            case "info":
                if (!commandManager.info()) return CheckCode.ERROR;
                break;
            case "show":
                if (!commandManager.show()) return CheckCode.ERROR;
                break;
            case "add":
                if (!commandManager.add(argument, obj)) return CheckCode.ERROR;
                break;
            case "update_by_id":
                if (!commandManager.updateById(argument, obj)) return CheckCode.ERROR;
                break;
            case "count_less_than_mood":
                if (!commandManager.countLessMood(argument)) return CheckCode.ERROR;
                break;
            case "clear":
                if (!commandManager.clear()) return CheckCode.ERROR;
                break;
            case "save":
                if (!commandManager.save()) return CheckCode.ERROR;
                break;
            case "execute_script":
                if (!commandManager.executeScript(argument)) return CheckCode.ERROR;
            case "add_if_max":
                if (!commandManager.addIfMax(argument)) return CheckCode.ERROR;
                break;
            case "filter_starts_with_soundtrack_name":
                if (!commandManager.filterSound(argument)) return CheckCode.ERROR;
                break;
            case "print_field_descending_impact_speed":
                if (!commandManager.printDescendingImpact()) return CheckCode.ERROR;
                break;
            case "remove_first":
                if (!commandManager.removeFirst()) return CheckCode.ERROR;
                break;
            case "remove_by_id":
                if (!commandManager.removeById(argument)) return CheckCode.ERROR;
                break;
            case "sort":
                if (!commandManager.sort()) return CheckCode.ERROR;
                break;
            case "exit":
                if (!commandManager.exit()) return CheckCode.ERROR;
                break;
            default:
                System.out.println("Команда не найдена. Наберите 'help' для справки.");
                return CheckCode.ERROR;
        }
        return CheckCode.OKAY;
    }

}
