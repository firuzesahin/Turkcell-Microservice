package com.kodlamaio.invoiceservice.business.concretes;

import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.business.dto.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.dto.requests.UpdateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.dto.responses.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.dto.responses.GetAllInvoicesResponse;
import com.kodlamaio.invoiceservice.business.dto.responses.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.business.dto.responses.UpdateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.rules.InvoicesBusinessRules;
import com.kodlamaio.invoiceservice.entities.Invoice;
import com.kodlamaio.invoiceservice.repository.InvoiceRepository;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceManager implements InvoiceService
{
    private final InvoiceRepository repository;
    private final ModelMapperService mapper;
    private final InvoicesBusinessRules rules;

    @Override
    public List<GetAllInvoicesResponse> getAll()
    {
        List<Invoice> invoices = repository.findAll();
        List<GetAllInvoicesResponse> response = invoices
                .stream()
                .map(invoice -> mapper.forResponse().map(invoice, GetAllInvoicesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetInvoiceResponse getById(UUID id)
    {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = repository.findById(id).orElseThrow();
        GetInvoiceResponse response = mapper.forResponse().map(invoice, GetInvoiceResponse.class);

        return response;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest request)
    {
        Invoice invoice = mapper.forRequest().map(request, Invoice.class);

        //invoice.setId(0);

        invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);
        CreateInvoiceResponse response = mapper.forResponse().map(invoice, CreateInvoiceResponse.class);

        return response;
    }

    @Override
    public UpdateInvoiceResponse update(UUID id, UpdateInvoiceRequest request)
    {
        rules.checkIfInvoiceExists(id);
        Invoice invoice = mapper.forRequest().map(request, Invoice.class);

        invoice.setId(id);

        invoice.setTotalPrice(getTotalPrice(invoice));
        repository.save(invoice);
        UpdateInvoiceResponse response = mapper.forResponse().map(invoice, UpdateInvoiceResponse.class);

        return response;
    }

    @Override
    public void delete(UUID id)
    {
        rules.checkIfInvoiceExists(id);
        repository.deleteById(id);
    }

    public double getTotalPrice(Invoice invoice)
    {
        return invoice.getDailyPrice() * invoice.getRentedForDays();
    }

}