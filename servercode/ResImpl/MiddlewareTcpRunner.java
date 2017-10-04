package ResImpl;

class MiddlewareTcpRunner {

	private static final int MIDDLEWARE_PORT = 1099;

	public static void main(String[] args) {

		MiddlewareTCP middleware = new MiddlewareTCP();
		ServerTCP server = new ServerTCP(middleware, MIDDLEWARE_PORT);

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