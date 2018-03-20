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
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 13/03/2018, 08:32:43
 */
@MessageDriven(mappedName = "java:global/jms/Topic",
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Topic")
            ,
            @ActivationConfigProperty(propertyName = "destinationName",
                    propertyValue = "topic")
        })
public class ConsumidorDeEmail implements MessageListener {

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
            email.setSubject("Compra realizada");
            email.setFrom(EMAIL);
            email.setMsg("O pedido com os produtos " + pedido.getProdutos()
                    + " foi efetuado");
            email.addTo(pedido.getCliente().getEmail());
            email.send();
        }catch (EmailException ex) {
            ex.printStackTrace();
         }
    }

}
