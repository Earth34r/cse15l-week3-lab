import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    StringBuilder messages = new StringBuilder();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return messages.toString();
        }
        
        else {
            if (url.getPath().contains("/add-message")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    messages.append("\n" + parameters[1]);
                    return messages.toString();
                }
            }
            return "404 Not Found!";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number!");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}