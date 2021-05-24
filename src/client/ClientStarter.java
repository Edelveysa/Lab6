package client;

import client.utility.Console;
import common.Port;
import common.exceptions.NotDeclaredValueException;
import common.exceptions.WrongAmountOfParametersException;

import java.util.Scanner;

public class ClientStarter {
    private static String host;
    private static int port;
    private static String fileName;

    public static void main(String[] args) {
        //if (!checkArgs(args)) return;
        System.out.println("Клиент запущен");
        Scanner scanner = new Scanner(System.in);
        Console console = new Console(scanner);
        ClientModule client = new ClientModule("localhost", Port.port, console);
        client.run("collect");
        scanner.close();
    }

    private static boolean checkArgs(String[] args) {
        try {
            if (args.length != 3) throw new WrongAmountOfParametersException();
            host = args[0];
            port = Integer.parseInt(args[1]);
            fileName = args[2];
            if (port < 0) throw new NotDeclaredValueException();
            String[] s = fileName.split("\\.");
            if (!s[s.length - 1].equals("json")) throw new NotDeclaredValueException();
            return true;
        } catch (WrongAmountOfParametersException e) {
            System.out.println("Нужно передавать 3 параметра <String host, int port, String fileName>");
        } catch (NotDeclaredValueException e) {
            System.out.println("Проверьте правильность ввода порта и имени файла.");
            System.out.println("Порт не может быть отрицательным и имя файла может быть только с расширеним .json.");
        } catch (NumberFormatException e) {
            System.out.println("Порт должен быть представлен числом!");
        }
        return false;
    }
}
