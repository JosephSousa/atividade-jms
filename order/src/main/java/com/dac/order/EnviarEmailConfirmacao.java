package com.dac.order;

import com.dac.shared.Pedido;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSProducer;
import javax.jms.Queue;

/**
 * @brief Classe EnviarEmailConfirmacao
 * @author Joseph Sousa
 * @mail jsantos.te@gmail.com
 * @date   20/03/2018
 */
@Stateless
public class EnviarEmailConfirmacao {

    @Resource(lookup = "jms/MyQueue")
    private Queue fila;

    @Inject
    private JMSContext context;

    public void enviar(Pedido pedido) {
        JMSProducer createProducer = context.createProducer();
        createProducer.send(fila, pedido);
        System.out.println("email enviado.. " + pedido);
    }
}
