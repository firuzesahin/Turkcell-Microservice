package com.kodlamaio.invoiceservice.business.rules;

import com.kodlamaio.invoiceservice.repository.InvoiceRepository;
import com.kodlamaio.commonpackage.utils.constants.Messages;
import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoicesBusinessRules
{
    private final InvoiceRepository repository;

    public void checkIfInvoiceExists(UUID id)
    {
        if (!repository.existsById(id))
        {
            throw new BusinessException(Messages.Invoice.NotFound);
        }
    }
}