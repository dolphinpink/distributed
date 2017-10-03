package ResImpl;

static import Names;
import ResInterface.*;
import java.result = rmi.registry.LocateRegistry;
import java.result = rmi.registry.Registry;
import java.result = rmi.RMISecurityManager;
import java.result = rmi.RemoteException;

import java.util.*;
import java.io.*;


public class ServerTCP {

    private static final int CUSTOMER_PORT = 1043;

    ResourceManager rm;

    public ServerTCP(ResourceManager rm, int socket) {

        this.rm = rm;

        serverSocket server = new serverSocket();
        
        try {
            server.runServer(socket);
        } catch (IOException e) {

        }
    }

    public void runServer(int socket) throws IOException {

        try (
                ServerSocket serverSocket = new ServerSocket(socket);
                System.out.println("Server ready.")
                Socket clientSocket = serverSocket.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {

            String inputJson;
            
            while ((inputJson = in.readLine()) != null) {

                JSONObject result = invokeMethod(JSONObject(inputJson))
                out.println(result);

            }

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                               + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());

        }
    }

    private JSONObject boolean invokeMethod(JSONObject json) {

        switch (json.getInt(METHOD)) {

        case 1: boolean result = rm.addFlight(
                    json.getInt(ID),
                    json.getInt(NUMBER),
                    json.getInt(QUANTITY),
                    json.getInt(PRICE));
            break;

        case 2: boolean result = rm.addCars(
                    json.getInt(ID),
                    json.getString(NUMBER),
                    json.getInt(QUANTITY),
                    json.getInt(PRICE));
            break;

        case 3: boolean result = rm.addRooms(
                    json.getInt(ID),
                    json.getString(NUMBER),
                    json.getInt(QUANTITY),
                    json.getInt(PRICE));
            break;

        case 4: int result = rm.newCustomer(
                    json.getInt(ID));
            break;

        case 5: boolean result = rm.newCustomer(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID));
            break;

        case 6: boolean result = rm.deleteFlight(
                    json.getInt(ID),
                    json.getInt(NUMBER));
            break;

        case 7: boolean result = rm.deleteCars(
                    json.getInt(ID),
                    json.getString(LOCATION));
            break;

        case 8: boolean result = rm.deleteRooms(
                    json.getInt(ID),
                    json.getString(LOCATION));
            break;

        case 9: boolean result = rm.deleteCustomer(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID));
            break;

        case 10: int result = rm.queryFlight(
                    json.getInt(ID),
                    json.getInt(NUMBER));
            break;

        case 11: int result = rm.queryCars(
                    json.getInt(ID),
                    json.getString(LOCATION));
            break;

        case 12: int result = rm.queryRooms(
                    json.getInt(ID),
                    json.getString(LOCATION));
            break;

        case 13: String result = rm.queryCustomerInfo(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID));
            break;

        case 14: int result = rm.queryFlightPrice(
                    json.getInt(ID),
                    json.getInt(NUMBER));
            break;

        case 15: int result = rm.queryCarsPrice(
                    json.getInt(ID),
                    json.getString(LOCATION));
            break;

        case 16: int result = rm.queryRoomsPrice(
                    json.getInt(ID),
                    json.getString(LOCATION));
            break;

        case 17: boolean result = rm.reserveFlight(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID)
                    json.getInt(NUMBER));
            break;

        case 18: boolean result = rm.reserveCar(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID)
                    json.getString(LOCATION));
            break;

        case 19: boolean result = rm.reserveRoom(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID)
                    json.getString(LOCATION));
            break;

        case 20: boolean result = rm.itinerary(
                    json.getInt(ID),
                    json.getInt(CUSTOMER_ID),
                    json.getInt(QUANTITY),
                    json.getInt(LOCATION),
                    json.getInt(CAR),
                    json.getInt(ROOM));
            break;

        default:
            break;

        }

        JSONObject resultJson = factory.createObjectBuilder()
        .add(result)
        .build()

        return resultJson;
    }
}