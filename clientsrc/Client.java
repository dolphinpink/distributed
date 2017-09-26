import ResImpl.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import java.util.*;
import java.io.*;


public class Client implements ResourceManager{

    private ResourceManager rm;

    public Client(int portNum, String server) {

        try {
            // get a reference to the rmiregistry
            Registry registry = LocateRegistry.getRegistry(server, portNum);
            // get the proxy and the remote reference by rmiregistry lookup

            Object o = registry.lookup("Group40");
            System.out.println(o.getClass().getName());
            rm = (ResourceManager) registry.lookup("Group40");
            if (rm != null) {
                System.out.println("Successful");
                System.out.println("Connected to RM");
            } else {
                System.out.println("Unsuccessful");
            }
            // make call on remote method
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }

        if (System.getSecurityManager() == null) {
            //System.setSecurityManager(new RMISecurityManager());
        }
    }

    public boolean addFlight(int id, int flightNum, int flightSeats, int flightPrice) throws RemoteException {

    	boolean result = rm.addFlight(id, flightNum, flightSeats, flightPrice);
        
        if (result)
            System.out.println("Flight added");
        else
            System.out.println("Flight could not be added");

        return result;
    }


    public boolean addCars(int id, String location, int numCars, int price) throws RemoteException {

    	boolean result = rm.addCars(id, location, numCars, price);

        if (result)
            System.out.println("Cars added");
        else
            System.out.println("Cars could not be added");

        return result;
    }


    public boolean addRooms(int id, String location, int numRooms, int price) throws RemoteException {

    	boolean result = rm.addRooms(id, location, numRooms, price);

        if (result)
            System.out.println("Rooms added");
        else
            System.out.println("Rooms could not be added");

        return result;
    }


    public int newCustomer(int id) throws RemoteException {

        int customer = rm.newCustomer(id);
        System.out.println("new customer id:" + customer);

        return customer;
    }

    public boolean newCustomer(int id, int cid) throws RemoteException {

        boolean customerAdded = rm.newCustomer(id, cid);
        System.out.println("new customer id:" + cid);

        return customerAdded;
    }

    public Customer getCustomer(int id, int customerId) throws RemoteException {

        Customer customer = rm.getCustomer(id, customerId);

        if (customer != null)
            System.out.println("Customer exists");
        else
            System.out.println("Customer does not exist");

        return customer;
    }


    public boolean deleteFlight(int id, int flightNumber) throws RemoteException {

    	boolean result = rm.deleteFlight(id, flightNumber);

        if (result)
            System.out.println("Flight Deleted");
        else
            System.out.println("Flight could not be deleted");

        return result;
    }


    public boolean deleteCars(int id, String location) throws RemoteException {

    	boolean result = rm.deleteCars(id, location);

        if (result)
            System.out.println("Cars Deleted");
        else
            System.out.println("Cars could not be deleted");

        return result;
    }


    public boolean deleteRooms(int id, String location) throws RemoteException {

    	boolean result = rm.deleteRooms(id, location);

        if (result)
            System.out.println("Rooms Deleted");
        else
            System.out.println("Rooms could not be deleted");

        return result;
    }


    public boolean deleteCustomer(int id, int customer) throws RemoteException {

    	boolean result = rm.deleteCustomer(id, customer);

        if (result)
            System.out.println("Customer Deleted");
        else
            System.out.println("Customer could not be deleted");

        return result;
    }


    public int queryFlight(int id, int flightNumber) throws RemoteException {

        int seats = rm.queryFlight(id, flightNumber);
        System.out.println("Number of seats available:" + seats);

        return seats;
    }


    public int queryCars(int id, String location) throws RemoteException {

        int numCars = rm.queryCars(id, location);
        System.out.println("number of Cars at this location:" + numCars);

        return numCars;
    }


    public int queryRooms(int id, String location) throws RemoteException {

        int numRooms = rm.queryRooms(id, location);
        System.out.println("number of Rooms at this location:" + numRooms);

        return numRooms;
    }


    public String queryCustomerInfo(int id, int customer) throws RemoteException {

        String bill = rm.queryCustomerInfo(id, customer);
        System.out.println("Customer info:" + bill);

        return bill;
    }


    public int queryFlightPrice(int id, int flightNumber) throws RemoteException {

        int price = rm.queryFlightPrice(id, flightNumber);
        System.out.println("Price of a seat:" + price);

        return price;
    }


    public int queryCarsPrice(int id, String location) throws RemoteException {

        int price = rm.queryCarsPrice(id, location);
        System.out.println("Price of a car at this location:" + price);

        return price;
    }


    public int queryRoomsPrice(int id, String location) throws RemoteException {

        int price = rm.queryRoomsPrice(id, location);
        System.out.println("Price of Rooms at this location:" + price);

        return price;
    }


    public boolean reserveFlight(int id, int customer, int flightNumber) throws RemoteException {

    	boolean result = rm.reserveFlight(id, customer, flightNumber);

        if (result)
            System.out.println("Flight Reserved");
        else
            System.out.println("Flight could not be reserved.");

        return result;
    }


    public boolean reserveCar(int id, int customer, String location) throws RemoteException {

    	boolean result = rm.reserveCar(id, customer, location); 

        if (result)
            System.out.println("Car Reserved");
        else
            System.out.println("Car could not be reserved.");

        return result;
    }


    public boolean reserveRoom(int id, int customer, String location) throws RemoteException {

    	boolean result = rm.reserveRoom(id, customer, location);

        if (result)
            System.out.println("Room Reserved");
        else
            System.out.println("Room could not be reserved.");

        return true;
    }


    public boolean itinerary(int id, int customer, Vector flightNumbers, String location, boolean car, boolean room) throws RemoteException {
        
        if (rm.itinerary(id, customer, flightNumbers, location, car, room)) {
            System.out.println("Itinerary Reserved");
        	return true;
        }

        else {
            System.out.println("Itinerary could not be reserved.");
        	return false;
        }
    }
}