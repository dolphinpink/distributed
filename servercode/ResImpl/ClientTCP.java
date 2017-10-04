package ResImpl;

import static ResImpl.Names.*;
import ResInterface.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import javax.json.*;
import java.util.*;
import java.io.*;
import java.net.*;


public class ClientTCP implements ResourceManager{

    private static final String RESULT = "result";
    private PrintWriter outToServer;
    private BufferedReader inFromServer;

    public ClientTCP(int portNum, String serverName) throws Exception, IOException {

        Socket socket = new Socket(serverName, portNum); // establish a socket with a server using the given port#
        outToServer = new PrintWriter(socket.getOutputStream(),true); // open an output stream to the server...
        inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream())); // open an input stream from the server...

    }

    private boolean bInvoke(String remoteString) throws RemoteException {

        String result = null;
        String resultJson = "";

        try {

            outToServer.println(remoteString); // send the user's input via the output stream to the server

            resultJson = inFromServer.readLine(); // receive the server's result via the input stream from the server
            
            JsonReader jsonReader = Json.createReader(new StringReader(resultJson));
            JsonObject obj = jsonReader.readObject();
            jsonReader.close();

            return obj.getBoolean(RESULT);

        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new RemoteException("Serialization went wrong, or wrong response: " + resultJson);
        }

    }

    private String sInvoke(String remoteString) throws RemoteException {

        String result = null;
        String resultJson = "";

        try {


            outToServer.println(remoteString); // send the user's input via the output stream to the server
            resultJson = inFromServer.readLine(); // receive the server's result via the input stream from the server
            
            JsonReader jsonReader = Json.createReader(new StringReader(resultJson));
            JsonObject obj = jsonReader.readObject();
            jsonReader.close();

            return obj.getString(RESULT);

        } catch (Exception e) {
            throw new RemoteException("Serialization went wrong, or wrong response" + resultJson);
        }

    }

    private int iInvoke(String remoteString) throws RemoteException {

        String result = null;
        String resultJson = "";

        try {

            outToServer.println(remoteString); // send the user's input via the output stream to the server
            resultJson = inFromServer.readLine(); // receive the server's result via the input stream from the server
            
            JsonReader jsonReader = Json.createReader(new StringReader(resultJson));
            JsonObject obj = jsonReader.readObject();
            jsonReader.close();

            return obj.getInt(RESULT);

        } catch (Exception e) {
            throw new RemoteException("Serialization went wrong, or wrong response" + resultJson);
        }
    }

    public boolean addFlight(int id, int number, int quantity, int price) throws RemoteException {

        boolean result = bInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 1)
                .add(ID, id)
                .add(NUMBER, number)
                .add(QUANTITY, quantity)
                .add(PRICE, price)
                .build()
                .toString()
            );
        
        if (result)
            System.out.println("Flight added");
        else
            System.out.println("Flight could not be added");

        return result;
    }


    public boolean addCars(int id, String location, int quantity, int price) throws RemoteException {

        boolean result = bInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 2)
                .add(ID, id)
                .add(LOCATION, location)
                .add(QUANTITY, quantity)
                .add(PRICE, price)
                .build()
                .toString()
        );

        if (result)
            System.out.println("Cars added");
        else
            System.out.println("Cars could not be added");

        return result;
    }


    public boolean addRooms(int id, String location, int quantity, int price) throws RemoteException {

        boolean result = bInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 3)
                .add(ID, id)
                .add(LOCATION, location)
                .add(QUANTITY, quantity)
                .add(PRICE, price)
                .build()
                .toString()
        );

        if (result)
            System.out.println("Rooms added");
        else
            System.out.println("Rooms could not be added");

        return result;
    }


    public int newCustomer(int id) throws RemoteException {

        int result = iInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 4)
                .add(ID, id)
                .build()
                .toString()
        );

        return result;
    }

    public boolean newCustomer(int id, int customerId) throws RemoteException {

        boolean result = bInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 5)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .build()
                .toString()
        );

        System.out.println("new customer id: " + result);

        return result;
    }

    public Customer getCustomer(int id, int customerId) throws RemoteException {
        System.out.println("This is deprecated");
        return null;
    }


    public boolean deleteFlight(int id, int number) throws RemoteException {

        String request = Json.createObjectBuilder()
                .add(METHOD, 6)
                .add(ID, id)
                .add(NUMBER, number)
                .build()
                .toString();

        System.out.println(request);

        boolean result = bInvoke(request);

        if (result)
            System.out.println("Flight Deleted");
        else
            System.out.println("Flight could not be deleted");

        return result;
    }


    public boolean deleteCars(int id, String location) throws RemoteException {

        boolean result = bInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 7)
                .add(ID, id)
                .add(LOCATION, location)
                .build()
                .toString()
        );

        if (result)
            System.out.println("Cars Deleted");
        else
            System.out.println("Cars could not be deleted");

        return result;
    }


    public boolean deleteRooms(int id, String location) throws RemoteException {

        boolean result = bInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 8)
                .add(ID, id)
                .add(LOCATION, location)
                .build()
                .toString()
        );

        if (result)
            System.out.println("Rooms Deleted");
        else
            System.out.println("Rooms could not be deleted");

        return result;
    }


    public boolean deleteCustomer(int id, int customerId) throws RemoteException {

        boolean result = bInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 9)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .build()
                .toString()
        );

        if (result)
            System.out.println("Customer Deleted");
        else
            System.out.println("Customer could not be deleted");

        return result;
    }


    public int queryFlight(int id, int number) throws RemoteException {

        int result = iInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 10)
                .add(ID, id)
                .add(NUMBER, number)
                .build()
                .toString()
        );

        System.out.println("Number of seats available:" + result);

        return result;
    }


    public int queryCars(int id, String location) throws RemoteException {

        int result = iInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 11)
                .add(ID, id)
                .add(LOCATION, location)
                .build()
                .toString()
        );

        System.out.println("number of Cars at this location:" + result);

        return result;
    }


    public int queryRooms(int id, String location) throws RemoteException {

        int result = iInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 12)
                .add(ID, id)
                .add(LOCATION, location)
                .build()
                .toString()
        );

        System.out.println("number of Rooms at this location:" + result);

        return result;
    }


    public String queryCustomerInfo(int id, int customerId) throws RemoteException {

        String result = sInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 13)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .build()
                .toString()
        );

        System.out.println("Customer info:" + result);

        return result;
    }


    public int queryFlightPrice(int id, int number) throws RemoteException {

        int result = iInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 14)
                .add(ID, id)
                .add(NUMBER, number)
                .build()
                .toString()
        );

        System.out.println("Price of a seat:" + result);

        return result;
    }


    public int queryCarsPrice(int id, String location) throws RemoteException {

        int result = iInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 15)
                .add(ID, id)
                .add(LOCATION, location)
                .build()
                .toString()
        );

        System.out.println("Price of a car at this location:" + result);

        return result;
    }


    public int queryRoomsPrice(int id, String location) throws RemoteException {

        int result = iInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 16)
                .add(ID, id)
                .add(LOCATION, location)
                .build()
                .toString()
        );

        System.out.println("Price of Rooms at this location:" + result);

        return result;
    }


    public boolean reserveFlight(int id, int customerId, int number) throws RemoteException {

        boolean result = bInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 17)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .add(NUMBER, number)
                .build()
                .toString()
        );

        if (result)
            System.out.println("Flight Reserved");
        else
            System.out.println("Flight could not be reserved.");

        return result;
    }


    public boolean reserveCar(int id, int customerId, String location) throws RemoteException {

        boolean result = bInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 18)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .add(LOCATION, location)
                .build()
                .toString()
        );

        if (result)
            System.out.println("Car Reserved");
        else
            System.out.println("Car could not be reserved.");

        return result;
    }


    public boolean reserveRoom(int id, int customerId, String location) throws RemoteException {

        boolean result = bInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 19)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .add(LOCATION, location)
                .build()
                .toString()
        );


        if (result)
            System.out.println("Room Reserved");
        else
            System.out.println("Room could not be reserved.");

        return true;
    }


    public boolean itinerary(int id, int customerId, Vector numbers, String location, boolean car, boolean room) throws RemoteException {

        boolean result = bInvoke(
            Json.createObjectBuilder()
                .add(METHOD, 20)
                .add(ID, id)
                .add(CUSTOMER_ID, customerId)
                .add(QUANTITY, "vector can't be json serialized")
                .add(LOCATION, location)
                .add(CAR, car)
                .add(ROOM, room)
                .build()
                .toString()
        );

        
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