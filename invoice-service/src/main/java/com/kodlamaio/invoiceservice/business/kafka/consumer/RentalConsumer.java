package com.kodlamaio.invoiceservice.business.kafka.consumer;

import com.kodlamaio.commonpackage.events.inventory.CarCreatedEvent;
import com.kodlamaio.commonpackage.events.rental.RentalInvoiceCreatedEvent;
import com.kodlamaio.commonpackage.utils.mappers.ModelMapperService;
import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.business.dto.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.entities.Invoice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RentalConsumer
{
    private final InvoiceService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "rental-invoice-created",
            groupId = "rental-invoice-created"
    )
    public void consume(CarCreatedEvent event) {
        var invoice = mapper.forRequest().map(event, CreateInvoiceRequest.class);
        service.add(invoice);
        log.info("Rental-Invoice created event consumed {}", event);
    }
}