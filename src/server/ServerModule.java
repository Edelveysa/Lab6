package server;

import common.CheckCode;
import common.Reply;
import common.Request;
import server.utility.RequestManager;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ServerModule
{

    private int port;
    private RequestManager requestManager;
    private DatagramSocket socket;
    private InetAddress address;
    private Scanner scanner;

    public ServerModule(int port, RequestManager requestManager)
    {
        this.port = port;
        this.requestManager = requestManager;
    }


    public void run()
    {
        System.out.println("Запуск сервера!");
        boolean processStatus = true;
        scanner = new Scanner(System.in);
        Runnable userInput = () -> {
            try {
                while (true) {
                    String[] userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                    if (!userCommand[0].equals("save")) {
                        System.out.println("Сервер не может сам принимать такую команду!");
                        return;
                    }
                    Reply reply = executeRequest(new Request(userCommand[0], userCommand[1]));
                    System.out.println(reply.getReplyBody());
                }
            } catch (Exception e) {}
        };
        Thread thread = new Thread(userInput);
        thread.start();
        while (processStatus) {
            processStatus = processingClientRequest();
        }
    }


    private boolean processingClientRequest()
    {
        Request request = null;
        Reply reply = null;
        try {
            socket = new DatagramSocket(6112);
            scanner = new Scanner(System.in);
            do {
                request = getRequest();
                System.out.println("Получена команда '" + request.getCommandName() + "'");
                reply = executeRequest(request);
                System.out.println("Команда '" + request.getCommandName() + "' выполнена");
                sendReply(reply);
            } while (reply.getReplyCode() != CheckCode.CLOSE_SERVER);
            return false;
        } catch (IOException | ClassNotFoundException exception) {
            System.out.println("Произошла ошибка при работе с клиентом!");
        }
        return true;
    }

    private Request getRequest() throws IOException, ClassNotFoundException
    {
        byte[] getBuffer = new byte[socket.getReceiveBufferSize()];
        DatagramPacket getPacket = new DatagramPacket(getBuffer, getBuffer.length);
        socket.receive(getPacket);
        address = getPacket.getAddress();
        port = getPacket.getPort();
        return deserialize(getPacket, getBuffer);
    }


    private void sendReply(Reply reply) throws IOException
    {
        byte[] sendBuffer = serialize(reply);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
        socket.send(sendPacket);
    }

    private Reply executeRequest(Request request)
    {
        return requestManager.manage(request);
    }

    private Request deserialize(DatagramPacket getPacket, byte[] buffer) throws IOException, ClassNotFoundException
    {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getPacket.getData());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Request request = (Request) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return request;
    }

    private byte[] serialize(Reply reply) throws IOException
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(reply);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        objectOutputStream.close();
        return buffer;
    }

}
