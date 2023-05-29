package com.kodlamaio.commonpackage.events.rental;

import com.kodlamaio.commonpackage.events.Event;

import java.util.UUID;

public class RentalInvoiceCreatedEvent implements Event
{
    private UUID carId;
}