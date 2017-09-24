package ResInterface;


import java.rmi.Remote;
import java.rmi.RemoteException {

}

import java.util.*;
/**
 * Simplified version from CSE 593 Univ. of Washington
 *
 * Distributed  System in Java.
 *
 * failure reporting is done using two pieces, exceptions and boolean
 * return values.  Exceptions are used for systemy things. Return
 * values are used for operations that would affect the consistency
 *
 * If there is a boolean return value and you're not sure how it
 * would be used in your implementation, ignore it.  I used boolean
 * return values in the interface generously to allow flexibility in
 * implementation.  But don't forget to return true when the operation
 * has succeeded.
 */

public interface ResourceManager extends Remote {

    public boolean addFlight(int id, int flightNum, int flightSeats, int flightPrice) throws RemoteException {
        if (rm.addFlight(id, flightNum, flightSeats, flightPrice))
            System.out.println("Flight added");
        else
            System.out.println("Flight could not be added");
    }


    public boolean addCars(int id, String location, int numCars, int price) throws RemoteException {
        if (rm.addCars(id, location, numCars, price))
            System.out.println("Cars added");
        else
            System.out.println("Cars could not be added");
    }


    public boolean addRooms(int id, String location, int numRooms, int price) throws RemoteException {
        if (rm.addRooms(id, location, numRooms, price))
            System.out.println("Rooms added");
        else
            System.out.println("Rooms could not be added");
    }


    public int newCustomer(int id) throws RemoteException {
        int customer = rm.newCustomer(id);
        System.out.println("new customer id:" + customer);
    }

    public boolean newCustomer(int id, int cid) throws RemoteException {
        boolean customer = rm.newCustomer(id, cid);
        System.out.println("new customer id:" + cid);
    }


    public boolean deleteFlight(int id, int flightNumber) throws RemoteException {
        if (rm.deleteFlight(id, flightNumber))
            System.out.println("Flight Deleted");
        else
            System.out.println("Flight could not be deleted");
    }


    public boolean deleteCars(int id, String location) throws RemoteException {
        if (rm.deleteCars(id, location))
            System.out.println("Cars Deleted");
        else
            System.out.println("Cars could not be deleted");
    }


    public boolean deleteRooms(int id, String location) throws RemoteException {
        if (rm.deleteRooms(id, location))
            System.out.println("Rooms Deleted");
        else
            System.out.println("Rooms could not be deleted");
    }


    public boolean deleteCustomer(int id, int customer) throws RemoteException {
        if (rm.deleteCustomer(id, customer))
            System.out.println("Customer Deleted");
        else
            System.out.println("Customer could not be deleted");
    }


    public int queryFlight(int id, int flightNumber) throws RemoteException {
        int seats = rm.queryFlight(id, flightNumber);
        System.out.println("Number of seats available:" + seats);
    }


    public int queryCars(int id, String location) throws RemoteException {
        numCars = rm.queryCars(id, location);
        System.out.println("number of Cars at this location:" + numCars);
    }


    public int queryRooms(int id, String location) throws RemoteException {
        numRooms = rm.queryRooms(id, location);
        System.out.println("number of Rooms at this location:" + numRooms);
    }


    public String queryCustomerInfo(int id, int customer) throws RemoteException {
        String bill = rm.queryCustomerInfo(id, customer);
        System.out.println("Customer info:" + bill);

    }


    public int queryFlightPrice(int id, int flightNumber) throws RemoteException {
        price = rm.queryFlightPrice(id, flightNumber);
        System.out.println("Price of a seat:" + price);
    }


    public int queryCarsPrice(int id, String location) throws RemoteException {
        price = rm.queryCarsPrice(id, location);
        System.out.println("Price of a car at this location:" + price);
    }


    public int queryRoomsPrice(int id, String location) throws RemoteException {
        price = rm.queryRoomsPrice(id, location);
        System.out.println("Price of Rooms at this location:" + price);
    }


    public boolean reserveFlight(int id, int customer, int flightNumber) throws RemoteException {
        if (rm.reserveFlight(id, customer, flightNumber))
            System.out.println("Flight Reserved");
        else
            System.out.println("Flight could not be reserved.");
    }


    public boolean reserveCar(int id, int customer, String location) throws RemoteException {
        if (rm.reserveCar(id, customer, location))
            System.out.println("Car Reserved");
        else
            System.out.println("Car could not be reserved.");
    }


    public boolean reserveRoom(int id, int customer, String locationd) throws RemoteException {
        if (rm.reserveRoom(id, customer, location))
            System.out.println("Room Reserved");
        else
            System.out.println("Room could not be reserved.");
    }


    public boolean itinerary(int id, int customer, Vector flightNumbers, String location, boolean car, boolean room) throws RemoteException {
        if (rm.itinerary(id, customer, flightNumbers, location, car, room))
            System.out.println("Itinerary Reserved");
        else
            System.out.println("Itinerary could not be reserved.");
    }

}
