
package ResImpl;

import ResInterface.*;

import java.util.*;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RMISecurityManager;

public class Middleware implements ResourceManager {

    private static final int FLIGHT_PORT = 1040;
    private static final int ROOM_PORT = 1041;
    private static final int CAR_PORT = 1042;	
    private static final int CUSTOMER_PORT = 1043;

    protected RMHashtable m_itemHT = new RMHashtable();
   	Client roomClient;
   	Client flightClient;
   	Client carClient;
   	Client customerClient;

    public static void main(String args[]) {
        // Figure out where server is running
        String server = "localhost";

        flightClient = new Client(FLIGHT_PORT, server);
        roomClient = new Client(ROOM_PORT, server);
        carClient = new Client(CAR_PORT, server);
        customerClient = new Client(CUSTOMER_PORT, server);

        try {
            // create a new Server object
            ResourceManagerImpl obj = new ResourceManagerImpl();
            // dynamically generate the stub (client proxy)
            ResourceManager rm = (ResourceManager) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry(port);
            registry.rebind"Group40", r(m);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

        // Create and install a security manager
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
    }

    public ResourceManagerImpl() throws RemoteException {}


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
        return roomClient.deleteRooms(id, Hotel.getKey(location));

    }


    public boolean addCars(int id, String location, int count, int price) throws RemoteException {
        return carClient.addCars(id, location, count, price);
    }


    public boolean deleteCars(int id, String location) throws RemoteException {
        return carClient.deleteCars(id, Car.getKey(location));
    }


    public int queryFlight(int id, int flightNum) throws RemoteException {
        return flightClient.queryFlight(id, Flight.getKey(flightNum));
    }


    public int queryFlightPrice(int id, int flightNum ) throws RemoteException {
        return flightClient.queryFlightPrice(id, Flight.getKey(flightNum));
    }


    public int queryRooms(int id, String location) throws RemoteException {
        return roomClient.queryRooms(id, Hotel.getKey(location));
    }


    public int queryRoomsPrice(int id, String location) throws RemoteException {
        return roomClient.queryRoomsPrice(id, Hotel.getKey(location));
    }


    public int queryCars(int id, String location) throws RemoteException {
        return carClient.queryCars(id, Car.getKey(location));
    }


    public int queryCarsPrice(int id, String location) throws RemoteException {
        return carClient.queryCarsPrice(id, Car.getKey(location));
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
        return carClient.reserveCar(id, customerID, location);
    }


    public boolean reserveRoom(int id, int customerID, String location) throws RemoteException {
        return roomClient.reserveRoom(id, customerID, location);
    }


    public boolean reserveFlight(int id, int customerID, int flightNum) throws RemoteException {
        return flightClient.reserveFlight(id, customerID, flightNum);
    }


    public boolean itinerary(int id, int customer, Vector flightNumbers, String location, boolean Car, boolean Room) throws RemoteException {
        return false;
    }

}