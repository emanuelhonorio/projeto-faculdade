package remoteservices;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import server.dao.MessageDAO;
import server.model.Mensagem;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
	private static final long serialVersionUID = 1L;
	
	private List<Mensagem> mensagens = new ArrayList<>();

	public ChatServiceImpl() throws RemoteException {
		super();
		this.mensagens = MessageDAO.selectAll(); // Inicializa as mensagens com as mensagens do banco de dados
	}

	/**
	 * Salva a mensagem no "cache" e no banco de dados
	 */
	@Override
	public void sendMessage(Mensagem mensagem) throws RemoteException {
		this.mensagens.add(mensagem);
		MessageDAO.save(mensagem);
	}
	
	@Override
	public List<Mensagem> getMessages() {
		return mensagens;
	}

	@Override
	public long getTotalMessages() throws RemoteException {
		return mensagens.size();
	}
	
}
