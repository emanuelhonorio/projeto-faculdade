package client;

import java.rmi.RemoteException;
import java.util.Scanner;

import client.thread.Receiver;
import client.thread.Sender;
import client.util.ClientUtils;
import remoteservices.ChatService;

public class Client {
	public static void main(String[] args) throws RemoteException {
		ChatService chatService;
		String usuario = "";
		Sender sender;
		Receiver receiver;
		Thread threadSender, threadReceiver;

		chatService = (ChatService) ClientUtils.getService("ChatService");
		
		usuario = getNomeUsu();
		sender = new Sender(usuario, chatService);
		receiver = new Receiver(chatService); // TODO: tirar o lancamento de excessao
		
		threadSender =  new Thread(sender);
		threadReceiver =  new Thread(receiver);
		
		threadSender.start();
		threadReceiver.start();
	}

	@SuppressWarnings("resource")
	private static String getNomeUsu() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Escolha um nome: ");
		return scan.nextLine();
	}
}
