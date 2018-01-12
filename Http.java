import java.io.*;
import java.net.*;

import com.sun.net.httpserver.*;

public class Http {
  public static void main(String[] args) throws IOException {
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
      String message = "Hello";

      req.sendResponseHeaders(200, message.length());

      OutputStream res = req.getResponseBody();
      res.write(message.getBytes());
      res.close();
    }
  }
}
