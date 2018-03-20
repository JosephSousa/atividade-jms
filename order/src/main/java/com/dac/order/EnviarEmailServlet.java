package com.dac.order;

/**
 * @brief Classe EnviarEmailServlet
 * @author Joseph Sousa
 * @mail jsantos.te@gmail.com
 * @date   20/03/2018
 */
import com.dac.shared.Cliente;
import com.dac.shared.Pedido;
import com.dac.shared.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EnviarEmailServlet", urlPatterns = {"/email"})
public class EnviarEmailServlet extends HttpServlet {

    @Inject
    private EnviarEmail produtor;

    
    Pedido pedido = new Pedido();
   
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        Produto produto= new Produto("coca", new BigDecimal(12.0));
        pedido.add(produto);
        Cliente cliente = new Cliente("Joseph", "jsantos.te@gmail.com");
        pedido.setCliente(cliente);
        

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EnviarEmailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"+pedido.getProdutos().toString()+"</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        
        this.produtor.enviar(pedido);
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        this.produtor.enviar(pedido);

    }

}
