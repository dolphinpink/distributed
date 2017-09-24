
package ResImpl;

import ResInterface.*;

import java.util.*;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RMISecurityManager;

public class Middleware implements ResourceManager {

    private static final int portFlight = 1040;
    private static final int portRoom = 1041;
    private static final int portCar = 1042;

    protected RMHashtable m_itemHT = new RMHashtable();
    MiddlewareClient mcFlight;
    MiddlewareClient mcRoom;
    MiddlewareClient mcCar;

    public static void main(String args[]) {
        // Figure out where server is running
        String server = "localhost";

        mcFlight = new MiddlewareClient(portFlight, server);
        mcRoom = new MiddlewareClient(portRoom, server);
        mcCar = new MiddlewareClient(portCar, server);

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
        return mcFlight.addFlight(id, flightNum, flightSeats, flightPrice);
    }


    public boolean deleteFlight(int id, int flightNum) throws RemoteException {
        return mcFlight.deleteFlight(id, flightNum);
    }


    public boolean addRooms(int id, String location, int count, int price) throws RemoteException {
        return mcRoom.addRooms(id, location, count, price);
    }


    public boolean deleteRooms(int id, String location) throws RemoteException {
        return mcRoom.deleteRooms(id, Hotel.getKey(location));

    }


    public boolean addCars(int id, String location, int count, int price) throws RemoteException {
        return mcCar.addCars(id, location, count, price);
    }


    public boolean deleteCars(int id, String location) throws RemoteException {
        return mcCar.deleteCars(id, Car.getKey(location));
    }


    public int queryFlight(int id, int flightNum) throws RemoteException {
        return mcFlight.queryFlight(id, Flight.getKey(flightNum));
    }


    public int queryFlightPrice(int id, int flightNum ) throws RemoteException {
        return mcFlight.queryFlightPrice(id, Flight.getKey(flightNum));
    }


    public int queryRooms(int id, String location) throws RemoteException {
        return mcRoom.queryRooms(id, Hotel.getKey(location));
    }


    public int queryRoomsPrice(int id, String location) throws RemoteException {
        return mcRoom.queryRoomsPrice(id, Hotel.getKey(location));
    }


    public int queryCars(int id, String location) throws RemoteException {
        return mcCar.queryCars(id, Car.getKey(location));
    }


    public int queryCarsPrice(int id, String location) throws RemoteException {
        return mcCar.queryCarsPrice(id, Car.getKey(location));
    }


    public String queryCustomerInfo(int id, int customerID) throws RemoteException {
        return mc.queryCustomerInfo(id, customerID);
    }


    public int newCustomer(int id) throws RemoteException {
        return mcCar.newCustomer(id);
    }


    public boolean newCustomer(int id, int customerID ) throws RemoteException {
        return mcCar.newCustomer(id, customerID);
    }


    public boolean deleteCustomer(int id, int customerID) throws RemoteException {
        return mcCar.deleteCustomer(id, customerID);
    }


    public boolean reserveCar(int id, int customerID, String location) throws RemoteException {
        return mcCar.reserveCar(id, customerID, location);
    }


    public boolean reserveRoom(int id, int customerID, String location) throws RemoteException {
        return mcRoom.reserveRoom(id, customerID, location);
    }


    public boolean reserveFlight(int id, int customerID, int flightNum) throws RemoteException {
        return mcFlight.reserveFlight(id, customerID, flightNum);
    }


    public boolean itinerary(int id, int customer, Vector flightNumbers, String location, boolean Car, boolean Room) throws RemoteException {
        return false;
    }

}