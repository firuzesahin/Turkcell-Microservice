package com.kodlamaio.paymentservice.adapters;

import com.kodlamaio.commonpackage.utils.constants.Messages;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.paymentservice.business.abstracts.PosService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePosServiceAdapter implements PosService
{
    @Override
    public void pay()
    {
        boolean isPaymentSuccessful = new Random().nextBoolean();
        if (!isPaymentSuccessful) throw new BusinessException(Messages.Payment.Failed);
    }
}