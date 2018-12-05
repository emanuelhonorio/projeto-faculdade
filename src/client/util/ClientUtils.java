package client.util;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import server.Server;

public class ClientUtils {

	public static Object getService(String serviceName) {
		try {
			return Naming.lookup("rmi://"+Server.SERVER_IP+":"+Server.SERVER_PORT+"/"+serviceName);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			handleFatalError(e.getMessage());
		}
		return null;
	}
	
	public static void handleFatalError(String message) {
		JOptionPane.showMessageDialog(null, message, "Fatal Error", JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
	public static void handleError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}
}
