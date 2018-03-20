package com.dac.creditcard;

import com.dac.shared.Pedido;
import com.dac.shared.TransactionCard;

/**
 * @brief Classe VerificaTransacao
 * @author Joseph Sousa
 * @mail jsantos.te@gmail.com
 * @date   19/03/2018
 */
public class VerificaTransacao {

    public void verifica(Pedido pedido){
        if(pedido.getValor()>500){
            pedido.setTransaction(TransactionCard.NEGADA);
        }else{
            pedido.setTransaction(TransactionCard.APROVADA);
        }
    }
    
}
