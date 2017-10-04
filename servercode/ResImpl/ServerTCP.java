package ResImpl;

import static ResImpl.Names.*;
import ResInterface.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import javax.json.*;
import java.net.*;

import java.util.*;
import java.io.*;


public class ServerTCP {

    private static final int CUSTOMER_PORT = 1043;
    private static final String RESULT = "result";

    ResourceManager rm;
    int socket;

    public ServerTCP(ResourceManager rm, int socket) {
        this.rm = rm;
        this.socket = socket;
    }

    public void runServer(int socket) throws IOException {

        try (
                ServerSocket serverSocket = new ServerSocket(socket);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {

            System.out.println("Server ready.");

            String inputJson;
            
            while ((inputJson = in.readLine()) != null) {

                JsonReader jsonReader = Json.createReader(new StringReader(inputJson));
                JsonObject object = jsonReader.readObject();
                jsonReader.close();

                JsonObject result = invokeMethod(object);
                out.println(result);

            }

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                               + socket + " or listening for a connection");
            System.out.println(e.getMessage());

        }
    }

    private JsonObject invokeMethod(JsonObject json) throws RemoteException {

        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

        switch (json.getInt(METHOD)) {

        case 1: jsonBuilder.add(RESULT, rm.addFlight(
                    json.getInt(ID),
                    json.getInt(NUMBER),
                    json.getInt(QUANTITY),
                    json.getInt(PRICE)));
            break;

        case 2: jsonBuilder.add(RESULT, rm.addCars(
                    json.getInt(ID),
                    json.getString(NUMBER),
                    json.getInt(QUANTITY),
                    json.getInt(PRICE)));
            break;

        case 3: jsonBuilder.add(RESULT, rm.addRooms(
                    json.getInt(ID),
                    json.getString(NUMBER),
                    json.getInt(QUANTITY),
                    json.getInt(PRICE)));
            break;

        case 4: jsonBuilder.add(RESULT, rm.newCustomer(
                    json.getInt(ID)));
            break;

        case 5: jsonBuilder.add(RESULT, rm.newCustomer(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID)));
            break;

        case 6: jsonBuilder.add(RESULT, rm.deleteFlight(
                    json.getInt(ID),
                    json.getInt(NUMBER)));
            break;

        case 7: jsonBuilder.add(RESULT, rm.deleteCars(
                    json.getInt(ID),
                    json.getString(LOCATION)));
            break;

        case 8: jsonBuilder.add(RESULT, rm.deleteRooms(
                    json.getInt(ID),
                    json.getString(LOCATION)));
            break;

        case 9: jsonBuilder.add(RESULT, rm.deleteCustomer(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID)));
            break;

        case 10: jsonBuilder.add(RESULT, rm.queryFlight(
                    json.getInt(ID),
                    json.getInt(NUMBER)));
            break;

        case 11: jsonBuilder.add(RESULT, rm.queryCars(
                    json.getInt(ID),
                    json.getString(LOCATION)));
            break;

        case 12: jsonBuilder.add(RESULT, rm.queryRooms(
                    json.getInt(ID),
                    json.getString(LOCATION)));
            break;

        case 13: jsonBuilder.add(RESULT, rm.queryCustomerInfo(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID)));
            break;

        case 14: jsonBuilder.add(RESULT, rm.queryFlightPrice(
                    json.getInt(ID),
                    json.getInt(NUMBER)));
            break;

        case 15: jsonBuilder.add(RESULT, rm.queryCarsPrice(
                    json.getInt(ID),
                    json.getString(LOCATION)));
            break;

        case 16: jsonBuilder.add(RESULT, rm.queryRoomsPrice(
                    json.getInt(ID),
                    json.getString(LOCATION)));
            break;

        case 17: jsonBuilder.add(RESULT, rm.reserveFlight(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID),
                    json.getInt(NUMBER)));
            break;

        case 18: jsonBuilder.add(RESULT, rm.reserveCar(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID),
                    json.getString(LOCATION)));
            break;

        case 19: jsonBuilder.add(RESULT, rm.reserveRoom(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID),
                    json.getString(LOCATION)));
            break;

        case 20: jsonBuilder.add(RESULT, rm.itinerary(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID),
                    null,
                    json.getString(LOCATION),
                    json.getBoolean(CAR),
                    json.getBoolean(ROOM)));
            break;

        default:
            break;

        }

        
        JsonObject resultJson = jsonBuilder.build();

        return resultJson;
    }
}