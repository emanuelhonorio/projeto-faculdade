package client.thread;

import java.rmi.RemoteException;
import java.util.List;

import client.util.ClientUtils;
import remoteservices.ChatService;
import server.model.Mensagem;

public class Receiver implements Runnable {

	private ChatService chatService;
	private List<Mensagem> mensagens;
	
	public Receiver(ChatService chatService) throws RemoteException {
		this.chatService = chatService;
		this.mensagens = chatService.getMessages(); // Pega as mensagens antigas do servidor
		
		// Printa todas as mensagens antigas
		for(Mensagem m : mensagens) {
			m.print();
		}	
	}

	@Override
	public void run() {
		/* Busca a cada 2s novas mensagens e as printa */
		while(true) {
			try {
				Thread.sleep(2000);
				if(hasNewMessages()) {
					for(Mensagem m : getNewMessages()) {
						this.mensagens.add(m); // adiciona a mensagem no "cache"
						m.print();
					}				
				}
				
			} catch (RemoteException | InterruptedException e) {
				ClientUtils.handleFatalError(e.getMessage());
			}
		}
	}

	private boolean hasNewMessages() throws RemoteException {
		return chatService.getTotalMessages() > this.mensagens.size();
	}

	private List<Mensagem> getNewMessages() throws RemoteException {
		List<Mensagem> updatedMessages = chatService.getMessages();
		List<Mensagem> newMessages =  updatedMessages.subList(this.mensagens.size(), updatedMessages.size());
		return newMessages;
	}

}
