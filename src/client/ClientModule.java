package client;

import client.utility.Console;
import common.Port;
import common.Reply;
import common.Request;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Set;

public class ClientModule implements Serializable
{
        private String host;
        private int port;
        private Console console;
        private DatagramChannel datagramChannel;
        private SocketAddress address;
        private ByteBuffer byteBuffer = ByteBuffer.allocate(16384);
        private Selector selector;

        public ClientModule(String host, int port, Console console) {
            this.host = host;
            this.port = port;
            this.console = console;
        }

        public void run(String fileName){
            try {
                datagramChannel = DatagramChannel.open();
                address = new InetSocketAddress("localhost", this.port);
                datagramChannel.connect(address);
                datagramChannel.configureBlocking(false);
                selector = Selector.open();
                datagramChannel.register(selector, SelectionKey.OP_WRITE);
                send(new Request("loadCollection", fileName));
                Reply responseLoading = receive();
                System.out.print(responseLoading.getReplyBody());
                Request requestToServer = null;
                Reply serverResponse = null;
                do {
                    requestToServer = serverResponse != null ? console.interactiveMode(serverResponse.getReplyCode()) :
                            console.interactiveMode(null);
                    if (requestToServer.isEmpty()) continue;
                    send(requestToServer);
                    serverResponse = receive();
                    System.out.print(serverResponse.getReplyBody());
                } while(!requestToServer.getCommandName().equals("exit"));
            } catch (IOException | ClassNotFoundException exception) {
//                System.out.println("Произошла ошибка при работе с сервером!");
                exception.printStackTrace();
                System.exit(0);
            }
        }

        private void makeByteBufferToRequest(Request request) throws IOException {
            byteBuffer.put(serialize(request));
            byteBuffer.flip();
        }

        private byte[] serialize(Request request) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(request);
            byte[] buffer = byteArrayOutputStream.toByteArray();
            objectOutputStream.flush();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            objectOutputStream.close();
            return buffer;
        }

        private Reply deserialize() throws IOException, ClassNotFoundException {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteBuffer.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Reply response = (Reply) objectInputStream.readObject();
            byteArrayInputStream.close();
            objectInputStream.close();
            byteBuffer.clear();
            return response;
        }

        private void send(Request request) throws IOException {
            makeByteBufferToRequest(request);
            DatagramChannel channel = null;
            while (channel == null) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey key : selectionKeys) {
                    if (key.isWritable()) {
                        channel = (DatagramChannel) key.channel();
                        channel.write(byteBuffer);
                        channel.register(selector, SelectionKey.OP_READ);
                        break;
                    }
                }
            }
            byteBuffer.clear();
        }

        private Reply receive() throws IOException, ClassNotFoundException {
            DatagramChannel channel = null;
            while (channel == null) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey key : selectionKeys) {
                    if (key.isReadable()) {
                        channel = (DatagramChannel) key.channel();
                        channel.read(byteBuffer);
                        byteBuffer.flip();
                        channel.register(selector, SelectionKey.OP_WRITE);
                        break;
                    }
                }
            }
            return deserialize();
        }



}
