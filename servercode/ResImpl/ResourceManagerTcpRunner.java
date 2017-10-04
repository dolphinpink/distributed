package ResImpl;

class ResourceManagerTcpRunner {

	public static void main(String[] args) {

		ResourceManagerImpl rm = new ResourceManagerImpl();
		ServerTCP server = new ServerTCP(rm, Integer.parseInt(args[0]));

		try {

			while(true) {
				server.runServer(Integer.parseInt(args[1]));
			}

		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}