package ResImpl;

class ResourceManagerTcpRunner {

	public static void main(String[] args) {

		ResourceManager rm;
		try {
			rm = new ResourceManagerTCP();

			ServerTCP server = new ServerTCP(rm, Integer.parseInt(args[0]));

			System.out.println("Server ready.");
			server.runServer();

		} catch (Exception e) {
			System.err.println("ResourceManager exception: " + e.toString());
			e.printStackTrace();
		}
	}
}