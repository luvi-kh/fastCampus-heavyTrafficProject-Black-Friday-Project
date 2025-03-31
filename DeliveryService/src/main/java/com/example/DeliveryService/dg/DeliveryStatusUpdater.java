package com.example.DeliveryService.dg;

import com.example.DeliveryService.enums.DeliveryStatus;
import com.example.DeliveryService.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DeliveryStatusUpdater {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Scheduled(fixedDelay = 10000)
    public void deliveryStatusUpdate() {
        System.out.println("----------- delivery status update ------------");

        var inDeliveryList = deliveryRepository.findAllByStatus(DeliveryStatus.IN_DELIVERY);
        inDeliveryList.forEach(delivery -> {
            delivery.status = DeliveryStatus.COMPLETED;
            deliveryRepository.save(delivery);
        });

        var requestedList = deliveryRepository.findAllByStatus(DeliveryStatus.REQUESTED);
        requestedList.forEach(delivery -> {
            delivery.status = DeliveryStatus.IN_DELIVERY;
            deliveryRepository.save(delivery);
        });
    }
}
