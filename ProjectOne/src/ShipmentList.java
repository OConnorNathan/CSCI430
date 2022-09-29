package ProjectOne.src;

import java.util.*;

public class ShipmentList {

    private LinkedList<Shipment> shipments;

    public ShipmentList() {
        shipments = new LinkedList<Shipment>();
    }

    public boolean addShipment(Shipment shipment) {
        return shipments.add(shipment);
    }

    public boolean receiveShipment(Shipment shipment) {
        return shipments.add(shipment);
    }

    public boolean makePayment(Shipment shipment) {
        return shipments.add(shipment);
    }

    public Iterator getShipments() {
        return shipments.iterator();
    }
}
