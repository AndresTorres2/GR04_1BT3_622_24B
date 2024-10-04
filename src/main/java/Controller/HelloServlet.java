package Controller;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Tabla de Datos</title></head>");
        out.println("<body>");
        out.println("<h1>" + message + " Hola</h1>");


        out.println("</body>");
        out.println("</html>");
    }

    public void destroy() {
    }
}
