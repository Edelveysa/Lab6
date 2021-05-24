package server;

import common.Port;
import server.utility.*;
import server.сommands.*;

import java.io.Serializable;


public class ServerStarter implements Serializable
{

    public static void main(String[] args) {
        FileManager fileManager = new FileManager(System.getenv("COLLECT"));
        CollectionManager collectionManager = new CollectionManager(fileManager);
        CommandManager commandManager = new CommandManager(
                new AddCommand(collectionManager),
                new AddIfMaxCommand(collectionManager),
                new ClearCommand(collectionManager),
                new CountLessThanMoodCommand(collectionManager),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new FilterStartsWithSoundNameCommand(collectionManager),
                new HelpCommand(collectionManager),
                new InfoCommand(collectionManager),
                new PrintFieldDescendingImpactSpeedCommand(collectionManager),
                new RemoveByIdCommand(collectionManager),
                new RemoveFirstCommand(collectionManager),
                new SaveCommand(collectionManager),
                new ShowCommand(collectionManager),
                new SortCommand(collectionManager),
                new UpdateIdCommand(collectionManager),
                new LoadCollectionCommand(collectionManager));
        RequestManager requestManager = new RequestManager(commandManager);
        ServerModule server = new ServerModule(Port.port, requestManager);
        server.run();
        collectionManager.saveCollection();
    }
}

