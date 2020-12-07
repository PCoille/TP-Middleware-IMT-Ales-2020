package net.coille.client.ui.cli;

import net.coille.client.ui.ArchDetection;
import net.coille.client.ui.ArchDetectionImpl;
import net.coille.client.ui.UIController;
import net.coille.common.message.GlobalMessageImpl;
import net.coille.common.message.MessageImpl;
import net.coille.common.message.PersonalMessageImpl;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Scanner;

public class CLIClient {
    private UIController uiController;
    private String username;

    CLIClient() throws RemoteException, NotBoundException, MalformedURLException {
        this.initUiController();
        this.initUsername();

        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            @Override
            public void run()
            {
                try {
                    uiController.exit(username);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initUiController() throws RemoteException, NotBoundException, MalformedURLException {
        ArchDetection archDetection = new ArchDetectionImpl();
        uiController = archDetection.getUIControllerClient();
        System.out.println("Initializing " + archDetection.getServerArchitecture().getArchName() + " client.");

        uiController.initSystem();
    }

    private void initUsername() throws RemoteException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username");
        String username = scanner.nextLine();

        while (!uiController.initUser(username)) {
            System.out.println("Username already taken. Choose another username.");
            username = scanner.nextLine();
        }

        this.username = username;
    }

    private String stringifyMessage(MessageImpl message) {
        if (message instanceof PersonalMessageImpl) {
            PersonalMessageImpl tmpMessage = (PersonalMessageImpl) message;
            return "[w] " + tmpMessage.getSender() + " to " + tmpMessage.getReceiver() + ": " + tmpMessage.getRawMessage();
        }
        else if (message instanceof GlobalMessageImpl) {
            return "[g] " + message.getSender() + ": " + message.getRawMessage();
        }
        else {
            return message.getSender() + ": " + message.getRawMessage();
        }
    }

    private boolean execCommand(String command) throws RemoteException {
        String[] splitCommand = command.split(" ", 3);

        if ("exit".equals(splitCommand[0])){
            return false;
        }
        else if("all".equals(splitCommand[0])){
            if (splitCommand.length >= 2) {
                uiController.sendMessageToAll(new GlobalMessageImpl(username, String.join(" ", Arrays.copyOfRange(splitCommand, 1, splitCommand.length))));
            }
            else {
                System.out.println("Not enough arguments. Type \"help\" for help.");
            }
        }
        else if("w".equals(splitCommand[0])){
            if (splitCommand.length >= 3) {
                uiController.sendPersonalMessage(new PersonalMessageImpl(username, splitCommand[1], splitCommand[2]));
            }
            else {
                System.out.println("Not enough arguments. Type \"help\" for help.");
            }
        }
        else if("get".equals(splitCommand[0])){
            uiController.getMessages().forEach(message -> System.out.println(this.stringifyMessage(message)));
        }
        else if("who".equals(splitCommand[0])){
            uiController.getUsers().forEach(s -> System.out.println("["+s+"]"));
        }
        else if("help".equals(splitCommand[0])){
            System.out.println();
            System.out.println("exit:\tDisconnect from the session");
            System.out.println("w [recipient] [message]:\tSend message to recipient");
            System.out.println("all [message]:\tSend message to everyone");
            System.out.println("get:\tGet the received messages");
            System.out.println("who:\tGet other clients name");
            System.out.println("help:\tShow the help menu");
            System.out.println();
        }
        else {
            System.out.println("Command not recognised. Type \"help\" for help.");
        }

        return true;
    }

    public void start() throws RemoteException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write your message. Type \"help\" for help.");

        while (execCommand(scanner.nextLine())) {}

        uiController.exit(username);
        System.out.println("Disconnected successfully");
    }



    public static void main(String[] args) throws RemoteException {
        try {


            CLIClient client = new CLIClient();
            client.start();

            System.exit(0);

        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
