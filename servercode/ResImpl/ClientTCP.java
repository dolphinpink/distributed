package ResImpl;

static import Names;
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

    public boolean addFlight(int id, int number, int quantity, int price) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 1)
                .add(ID, id)
                .add(NUMBER, number)
                .add(QUANTITY, quantity)
                .add(PRICE, price)
                .build()
                .toString();
            )
        
        if (result)
            System.out.println("Flight added");
        else
            System.out.println("Flight could not be added");

        return result;
    }


    public boolean addCars(int id, String location, int quantity, int price) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 2)
                .add(ID, id)
                .add(LOCATION, location)
                .add(QUANTITY, quantity)
                .add(PRICE, price)
                .build()
                .toString();
        )

        if (result)
            System.out.println("Cars added");
        else
            System.out.println("Cars could not be added");

        return result;
    }


    public boolean addRooms(int id, String location, int quantity, int price) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 3)
                .add(ID, id)
                .add(LOCATION, location)
                .add(NUM_ROOMS, quantity)
                .add(PRICE, price)
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

        boolean result = Invoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 4)
                .add(ID, id)
                .build()
                .toString();
        )

        return customerId;
    }

    public boolean newCustomer(int id, int customerId) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 5)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .build()
                .toString();
        )

        System.out.println("new customer id:" + customerId);

        return customerAdded;
    }

    @deprecated
    public Customer getCustomer(int id, int customerId) throws RemoteException {

        return null;
    }


    public boolean deleteFlight(int id, int number) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 6)
                .add(ID, id)
                .add(NUMBER, number)
                .build()
                .toString();
        )

        if (result)
            System.out.println("Flight Deleted");
        else
            System.out.println("Flight could not be deleted");

        return result;
    }


    public boolean deleteCars(int id, String location) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 7)
                .add(ID, id)
                .add(LOCATION, location)
                .build()
                .toString();
        )

        if (result)
            System.out.println("Cars Deleted");
        else
            System.out.println("Cars could not be deleted");

        return result;
    }


    public boolean deleteRooms(int id, String location) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 8)
                .add(ID, id)
                .add(LOCATION, location)
                .build()
                .toString();
        )

        if (result)
            System.out.println("Rooms Deleted");
        else
            System.out.println("Rooms could not be deleted");

        return result;
    }


    public boolean deleteCustomer(int id, int customerId) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 9)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .build()
                .toString();
        )

        if (result)
            System.out.println("Customer Deleted");
        else
            System.out.println("Customer could not be deleted");

        return result;
    }


    public int queryFlight(int id, int number) throws RemoteException {

        int result = iInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 10)
                .add(ID, id)
                .add(NUMBER, number)
                .build()
                .toString();
        )

        System.out.println("Number of seats available:" + seats);

        return seats;
    }


    public int queryCars(int id, String location) throws RemoteException {

        int result = iInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 11)
                .add(ID, id)
                .add(LOCATION, location)
                .build()
                .toString();
        )

        System.out.println("number of Cars at this location:" + quantity);

        return quantity;
    }


    public int queryRooms(int id, String location) throws RemoteException {

        int result = iInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 12)
                .add(ID, id)
                .add(LOCATION, location)
                .build()
                .toString();
        )

        System.out.println("number of Rooms at this location:" + quantity);

        return quantity;
    }


    public String queryCustomerInfo(int id, int customerId) throws RemoteException {

        String result = sInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 13)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .build()
                .toString();
        )

        System.out.println("Customer info:" + bill);

        return bill;
    }


    public int queryFlightPrice(int id, int number) throws RemoteException {

        int result = iInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 14)
                .add(ID, id)
                .add(NUMBER, number)
                .build()
                .toString();
        )

        System.out.println("Price of a seat:" + price);

        return price;
    }


    public int queryCarsPrice(int id, String location) throws RemoteException {

        int result = iInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 15)
                .add(ID, id)
                .add(LOCATION, location)
                .build()
                .toString();
        )

        System.out.println("Price of a car at this location:" + price);

        return price;
    }


    public int queryRoomsPrice(int id, String location) throws RemoteException {

        int result = iInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 16)
                .add(ID, id)
                .add(LOCATION, location)
                .build()
                .toString();
        )

        System.out.println("Price of Rooms at this location:" + price);

        return price;
    }


    public boolean reserveFlight(int id, int customerId, int number) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 17)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .add(NUMBER, number)
                .build()
                .toString();
        )

        if (result)
            System.out.println("Flight Reserved");
        else
            System.out.println("Flight could not be reserved.");

        return result;
    }


    public boolean reserveCar(int id, int customerId, String location) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 18)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .add(LOCATION, location)
                .build()
                .toString();
        )

        if (result)
            System.out.println("Car Reserved");
        else
            System.out.println("Car could not be reserved.");

        return result;
    }


    public boolean reserveRoom(int id, int customerId, String location) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 19)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .add(LOCATION, location)
                .build()
                .toString();
        )


        if (result)
            System.out.println("Room Reserved");
        else
            System.out.println("Room could not be reserved.");

        return true;
    }


    public boolean itinerary(int id, int customerId, Vector numbers, String location, boolean car, boolean room) throws RemoteException {

        boolean result = bInvoke(
            String value = factory.createObjectBuilder()
                .add(METHOD, 20)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .add(QUANTITY, quantity)
                .add(LOCATION, location)
                .add(CAR, car)
                .add(ROOM, room)
                .build()
                .toString();
        )

        
        if (result) {
            System.out.println("Itinerary Reserved");
            return true;
        }

        else {
            System.out.println("Itinerary could not be reserved.");
            return false;
        }
    }
}