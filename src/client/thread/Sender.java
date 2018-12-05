package client.thread;

import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import client.util.ClientUtils;
import remoteservices.ChatService;
import server.model.Mensagem;

public class Sender implements Runnable {
	
	private String usuario;
	private ChatService chatService;
	
	public Sender(String usuario, ChatService chatService) {
		this.usuario = usuario;
		this.chatService = chatService;
	}

	@Override
	public void run() {
		while(true) {
			String mensagem = JOptionPane.showInputDialog(null, "Mensagem: ", usuario, JOptionPane.QUESTION_MESSAGE);
			try {
				if( isMessageValid(mensagem) )
					this.chatService.sendMessage( new Mensagem(usuario, mensagem ) );
			} catch (RemoteException e) {
				ClientUtils.handleFatalError(e.getMessage());
			}
		}
	}

	private boolean isMessageValid(String mensagem) {
		return !(mensagem == null || mensagem.trim().isEmpty());
	}
	
}
