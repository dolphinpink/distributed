package ResImpl;

class MiddlewareTcpRunner {

	private static final int MIDDLEWARE_SOCKET = 9060;

	public static void main(String[] args) {

		try {

			MiddlewareTCP middleware = new MiddlewareTCP();
			ServerTCP server = new ServerTCP(middleware, MIDDLEWARE_SOCKET);

				server.runServer();

		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}