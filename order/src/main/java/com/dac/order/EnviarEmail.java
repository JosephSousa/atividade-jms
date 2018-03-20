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
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 12/03/2018, 10:17:56
 */
@JMSDestinationDefinition(
        name = "java:global/jms/Topic",
        interfaceName = "javax.jms.Topic",
        resourceAdapter = "jmsra")
@Stateless
public class EnviarEmail {

    //"java:global/jms/Fila
//    @Resource(lookup = "jms/aulaQueue")
//    @Resource(lookup = "jms/dac/Queue")
    @Resource(lookup = "java:global/jms/Topic")
    private Queue fila;

    @Inject
    private JMSContext context;

    public void enviar(Pedido pedido) {
        JMSProducer createProducer = context.createProducer();
        createProducer.send(fila, pedido);
        System.out.println("email enviado.. " + pedido);
    }
}
