package serv;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

@WebServlet("/hello")
public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        showParameters(req, resp);
    }

    void showParameters(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println(
                "<html><head><tittle>Show Parameters</title></head><body>"+
                        "<h1> Parameters </h1><ul> "+
                        "</body></html>"
        );

        Map<String, String[]> params = req.getParameterMap();
        for(String name: params.keySet()){
            String [] values = params.get(name);
            out.println("<li>"+ name +"="+ Arrays.asList(values));
        }

        out.println("</ul><p><form method=\"POST\" action=\""
            + req.getRequestURL() +"\">"
                + "Field 1 <input name=\"Field1\" size=20><br>"
                + "Field 2 <input name=\"Field2\" size=20><br>"
                + "<br><input type=\"submit\" value=\"Submit\"></form>"
        );

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
