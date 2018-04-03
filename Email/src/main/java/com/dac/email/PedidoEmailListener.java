package com.dac.email;

import com.dac.shared.Pedido;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * @brief Classe PedidoEmailListener
 * @author Joseph Sousa
 * @mail jsantos.te@gmail.com
 * @date   19/03/2018
 */
@MessageDriven(mappedName = "jms/MyQueue",
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Queue")
            ,
            @ActivationConfigProperty(propertyName = "destinationName",
                    propertyValue = "queue")
        })
public class PedidoEmailListener implements MessageListener{

     private static final String EMAIL = "projetodacq@gmail.com";
    private static final String SENHA = "projetodac123";

    @Override
    public void onMessage(Message message) {
        Pedido pedido;
        try {
            pedido = message.getBody(Pedido.class);
            enviarEmail(pedido);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    private void enviarEmail(Pedido pedido) {
        try {
            Email email = new SimpleEmail();
            email.setHostName("smtp.googlemail.com");
            email.setAuthenticator(new DefaultAuthenticator(EMAIL, SENHA));
            email.setTLS(true);
            email.setSSL(true);
            email.setSubject("Compra"+pedido.getTransaction().toString());
            email.setFrom(EMAIL);
            email.setMsg("O pedido com os produtos " + pedido.getProdutos()
                    + " foi"+ pedido.getProdutos() );
            email.addTo(pedido.getCliente().getEmail());
            email.send();
        }catch (EmailException ex) {
            ex.printStackTrace();
         }
    }
}
