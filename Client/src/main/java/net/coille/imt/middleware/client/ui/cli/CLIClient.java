package net.coille.imt.middleware.client.ui.cli;

import net.coille.imt.middleware.client.arch.archDetection.ArchDetection;
import net.coille.imt.middleware.client.arch.archDetection.ArchDetectionImpl;
import net.coille.imt.middleware.client.ui.UIController;
import net.coille.imt.middleware.common.message.GlobalMessageImpl;
import net.coille.imt.middleware.common.message.Message;
import net.coille.imt.middleware.common.message.PersonalMessageImpl;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Scanner;

public class CLIClient {
    protected UIController uiController;
    protected String username;
    // Can change the used output, but still using System.out
    protected PrintStream printStream = System.out;
    protected InputStream inputStream = System.in;

    protected CLIClient() throws RemoteException, NotBoundException, MalformedURLException {
        this.initUiController();
        this.initCLIClient();
    }

    protected CLIClient(UIController uiController) throws RemoteException {
        this.uiController = uiController;
        this.initCLIClient();
    }

    protected void initCLIClient() throws RemoteException {
        this.initUsername();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                uiController.exit(username);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }));
    }

    protected void initUiController() throws RemoteException, NotBoundException, MalformedURLException {
        ArchDetection archDetection = new ArchDetectionImpl();
        uiController = archDetection.getUIControllerClient();
        printStream.println("Initializing " + archDetection.getServerArchitecture().getArchName() + " net.coille.imt.middleware.net.coille.imt.middleware.client.");

        uiController.initSystem();
    }

    protected void initUsername() throws RemoteException {
        Scanner scanner = new Scanner(inputStream);

        printStream.println("Enter username");
        String userInputName = scanner.nextLine();

        while (!uiController.initUser(userInputName)) {
            printStream.println("Username already taken. Choose another username.");
            userInputName = scanner.nextLine();
        }

        this.username = userInputName;
    }

    protected String stringifyMessage(Message message) {
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

    protected boolean execCommand(String command) throws RemoteException {
        String[] splitCommand = command.split(" ", 3);

        if ("exit".equals(splitCommand[0])){
            return false;
        }
        else if("all".equals(splitCommand[0])){
            if (splitCommand.length >= 2) {
                uiController.sendMessageToAll(new GlobalMessageImpl(username, String.join(" ", Arrays.copyOfRange(splitCommand, 1, splitCommand.length))));
            }
            else {
                printStream.println("Not enough arguments. Type \"help\" for help.");
            }
        }
        else if("w".equals(splitCommand[0])){
            if (splitCommand.length >= 3) {
                uiController.sendPersonalMessage(new PersonalMessageImpl(username, splitCommand[1], splitCommand[2]));
            }
            else {
                printStream.println("Not enough arguments. Type \"help\" for help.");
            }
        }
        else if("get".equals(splitCommand[0])){
            uiController.getMessages().forEach(message -> printStream.println(this.stringifyMessage(message)));
        }
        else if("who".equals(splitCommand[0])){
            uiController.getUsers().forEach(s -> printStream.println("["+s+"]"));
        }
        else if("help".equals(splitCommand[0])){
            printStream.println();
            printStream.println("exit:\tDisconnect from the session");
            printStream.println("w [recipient] [message]:\tSend message to recipient");
            printStream.println("all [message]:\tSend message to everyone");
            printStream.println("get:\tGet the received messages");
            printStream.println("who:\tGet other clients name");
            printStream.println("help:\tShow the help menu");
            printStream.println();
        }
        else {
            printStream.println("Command not recognised. Type \"help\" for help.");
        }

        return true;
    }

    public void start() throws RemoteException {
        Scanner scanner = new Scanner(inputStream);
        printStream.println("Write your message. Type \"help\" for help.");

        boolean loopExecCommand = true;
        while (loopExecCommand) {
            if(!execCommand(scanner.nextLine())) loopExecCommand = false;
        }

        uiController.exit(username);
        printStream.println("Disconnected successfully");
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
