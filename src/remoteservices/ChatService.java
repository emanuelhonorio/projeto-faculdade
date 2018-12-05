package remoteservices;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import server.model.Mensagem;

public interface ChatService extends Remote {
	public void sendMessage(Mensagem mensagem) throws RemoteException;
	public List<Mensagem> getMessages() throws RemoteException;
	public long getTotalMessages() throws RemoteException;
}
