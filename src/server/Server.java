package server;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import remoteservices.ChatService;
import remoteservices.ChatServiceImpl;

public class Server {
	
	public static final String SERVER_IP = "localhost"; // TODO: somente para teste, alterar em produção
	public static final int SERVER_PORT = 1099;
	
	public Server() {
		try {
			ChatService chatService = new ChatServiceImpl();
			
			System.setProperty("java.rmi.server.hostname", SERVER_IP);
			LocateRegistry.createRegistry(SERVER_PORT);
			
			Naming.bind("ChatService", (Remote) chatService);
		} catch (MalformedURLException | RemoteException | AlreadyBoundException e) {
			System.out.println("Erro no servidor: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}

}
