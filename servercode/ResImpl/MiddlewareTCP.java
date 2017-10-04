package ResImpl;

import ResInterface.*;

import java.util.*;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RMISecurityManager;
import javax.json.*;

public class MiddlewareTCP implements ResourceManager {

	private static final int FLIGHT_PORT = 1040;
	private static final int ROOM_PORT = 1041;
	private static final int CAR_PORT = 1042;
	private static final int CUSTOMER_PORT = 1043;
	private static final int MIDDLEWARE_PORT = 1099;

	private Object reservationLock = new Object();

	private static ClientTCP roomClient;
	private static ClientTCP flightClient;
	private static ClientTCP carClient;
	private static ClientTCP customerClient;

	public MiddlewareTCP() {
		// Figure out where server is running
		String server = "localhost";

		flightClient = new ClientTCP(FLIGHT_PORT, server);
		roomClient = new ClientTCP(ROOM_PORT, server);
		carClient = new ClientTCP(CAR_PORT, server);
		customerClient = new ClientTCP(CUSTOMER_PORT, server);

	}

	public boolean addFlight(int id, int flightNum, int flightSeats, int flightPrice) throws RemoteException {
		return flightClient.addFlight(id, flightNum, flightSeats, flightPrice);
	}


	public boolean deleteFlight(int id, int flightNum) throws RemoteException {
		return flightClient.deleteFlight(id, flightNum);
	}


	public boolean addRooms(int id, String location, int count, int price) throws RemoteException {
		return roomClient.addRooms(id, location, count, price);
	}


	public boolean deleteRooms(int id, String location) throws RemoteException {
		return roomClient.deleteRooms(id, location);
	}


	public boolean addCars(int id, String location, int count, int price) throws RemoteException {
		return carClient.addCars(id, location, count, price);
	}


	public boolean deleteCars(int id, String location) throws RemoteException {
		return carClient.deleteCars(id, location);
	}

	public Customer getCustomer(int id, int customerId) throws RemoteException {
		return customerClient.getCustomer(id, customerId);
	}


	public int queryFlight(int id, int flightNum) throws RemoteException {
		return flightClient.queryFlight(id, flightNum);
	}


	public int queryFlightPrice(int id, int flightNum ) throws RemoteException {
		return flightClient.queryFlightPrice(id, flightNum);
	}


	public int queryRooms(int id, String location) throws RemoteException {
		return roomClient.queryRooms(id, location);
	}


	public int queryRoomsPrice(int id, String location) throws RemoteException {
		return roomClient.queryRoomsPrice(id, Hotel.getKey(location));
	}


	public int queryCars(int id, String location) throws RemoteException {
		return carClient.queryCars(id, location);
	}


	public int queryCarsPrice(int id, String location) throws RemoteException {
		return carClient.queryCarsPrice(id, location);
	}


	public String queryCustomerInfo(int id, int customerID) throws RemoteException {
		return customerClient.queryCustomerInfo(id, customerID);
	}


	public int newCustomer(int id) throws RemoteException {
		return customerClient.newCustomer(id);
	}


	public boolean newCustomer(int id, int customerID ) throws RemoteException {
		return customerClient.newCustomer(id, customerID);
	}


	public boolean deleteCustomer(int id, int customerID) throws RemoteException {
		return customerClient.deleteCustomer(id, customerID);
	}


	public boolean reserveCar(int id, int customerID, String location) throws RemoteException {

		synchronized (reservationLock) {
			return carClient.reserveCar(id, customerID, location);
		}
	}


	public boolean reserveRoom(int id, int customerID, String location) throws RemoteException {

		synchronized (reservationLock) {
			return roomClient.reserveRoom(id, customerID, location);
		}

	}


	public boolean reserveFlight(int id, int customerID, int flightNum) throws RemoteException {

		synchronized (reservationLock) {
			return flightClient.reserveFlight(id, customerID, flightNum);
		}

	}


	public boolean itinerary(int id, int customer, Vector flightNumbers, String location, boolean car, boolean room) throws RemoteException {

		synchronized (reservationLock) {
			return false;
		}
	}

}