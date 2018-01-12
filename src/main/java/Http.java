import java.io.*;
import java.net.*;

import com.sun.net.httpserver.*;

public class Http {

  public static void main(String[] args) throws IOException {
    // Heroku gives us a $PORT env variable that tells us where to bind our
    // socket for listening to http traffic.
    Integer port = Integer.parseInt(System.getenv("PORT"));
    InetSocketAddress socket = new InetSocketAddress(port);
    HttpServer server = HttpServer.create(socket, 0);

    server.createContext("/", new RootHandler());
    server.setExecutor(null);
    server.start();
  }

  static class RootHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange req) throws IOException {
      String message = "<h1>Hello CS2810/CS2815/CS3811!</h1>";

      req.sendResponseHeaders(200, message.length());

      OutputStream res = req.getResponseBody();
      res.write(message.getBytes());
      res.close();
    }
  }
}
