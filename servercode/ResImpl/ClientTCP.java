package ResImpl;

import ResInterface.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import java.util.*;
import java.io.*;


public class ClientTCP implements ResourceManager{

    private static final int CUSTOMER_PORT = 1043;
    private static final String RESULT = "result";


    public Client(int portNum, String server) {

        String serverName=args[0];

        Socket socket= new Socket(serverName, portNum); // establish a socket with a server using the given port#

        PrintWriter outToServer= new PrintWriter(socket.getOutputStream(),true); // open an output stream to the server...
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream())); // open an input stream from the server...

        BufferedReader bufferedReader =new java.io.BufferedReader(new InputStreamReader(System.in)); //to read user's input

    
        outToServer.println(readerInput); // send the user's input via the output stream to the server
        String res=inFromServer.readLine(); // receive the server's result via the input stream from the server
        System.out.println("result: "+res); // print the server result to the user

    }

    private boolean bInvoke(String remoteString) throws RemoteException{

        String result = null;

        try {
            outToServer.println(remoteString); // send the user's input via the output stream to the server
            String resultJson = inFromServer.readLine(); // receive the server's result via the input stream from the server
            JSONObject obj = new JSONObject(resultJson);
            return obj.getBoolean(RESULT);
        } catch (Exception e) {
            throw new Exception("Serialization went wrong, or wrong response" + resultJson);
        }


    }

    private String sInvoke(remoteString) {

        String result = null;

        try {
            outToServer.println(remoteString); // send the user's input via the output stream to the server
            String resultJson = inFromServer.readLine(); // receive the server's result via the input stream from the server
            JSONObject obj = new JSONObject(resultJson);
            return obj.getString(RESULT);
        } catch (Exception e) {
            throw new Exception("Serialization went wrong, or wrong response" + resultJson);
        }

        return result;

    }

    private int iInvoke() {

        String result = null;

        try {
            outToServer.println(remoteString); // send the user's input via the output stream to the server
            String resultJson = inFromServer.readLine(); // receive the server's result via the input stream from the server
            JSONObject obj = new JSONObject(resultJson);
            return obj.getInt(RESULT);
        } catch (Exception e) {
            throw new Exception("Serialization went wrong, or wrong response" + resultJson);
        }
    }

    public boolean addFlight(int id, int flightNum, int flightSeats, int flightPrice) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add("method", 1)
                .add("id", id)
                .add("flightNum", flightNum)
                .add("flightSeats", flightSeats)
                .add("flightPrice", flightPrice)
                .build()
                .toString();
            )
        
        if (result)
            System.out.println("Flight added");
        else
            System.out.println("Flight could not be added");

        return result;
    }


    public boolean addCars(int id, String location, int numCars, int price) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add("method", 2)
                .add("id", id)
                .add("location", location)
                .add("numCars", numCars)
                .add("price", price)
                .build()
                .toString();
        )

        if (result)
            System.out.println("Cars added");
        else
            System.out.println("Cars could not be added");

        return result;
    }


    public boolean addRooms(int id, String location, int numRooms, int price) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add("method", 2)
                .add("id", id)
                .add("location", location)
                .add("numRooms", numRooms)
                .add("price", price)
                .build()
                .toString();
        )

        if (result)
            System.out.println("Rooms added");
        else
            System.out.println("Rooms could not be added");

        return result;
    }


    public int newCustomer(int id) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add("method", 2)
                .add("id", id)
                .build()
                .toString();
        )

        return customer;
    }

    public boolean newCustomer(int id, int cid) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add("method", 2)
                .add("id", id)
                .add("cid", cid)
                .build()
                .toString();
        )

        System.out.println("new customer id:" + cid);

        return customerAdded;
    }

    public Customer getCustomer(int id, int customerId) throws RemoteException {

        String result = null;
        String json = factory.createObjectBuilder()
                .add("method", 2)
                .add("id", id)
                .add("cid", cid)
                .build()
                .toString();

        try {
            outToServer.println(remoteString); // send the user's input via the output stream to the server
            String resultJson = inFromServer.readLine(); // receive the server's result via the input stream from the server
            JSONObject obj = new JSONObject(resultJson);
            return obj.getString(RESULT);
        } catch (Exception e) {
            throw new Exception("Serialization went wrong, or wrong response" + resultJson);
        }

        return result;

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