import java.io.*;
import java.util.*; // make call to history and list for info 

public class ShipmentTest {
    public static void main(String[] args) throws Exception {
        // creating shipment
        Shipment s1 = new Shipment(123, 5, 120);

        Shipment s2 = new Shipment(789, 22, 200);

        // receiving info and displaying
        ShipmentList shipmentList = ShipmentList.instance();
        if (shipmentList.insertShipment(s1)) {
            System.out.println("S1 Inserted into ShipmentList");
        }

        if (shipmentList.insertShipment(s2)) {
            System.out.println("S2 Inserted into ShipmentList");
        }
        System.out.println("\nShipment Information\n" + shipmentList.toString());

        // Make a payment for the shipment
        for (final Iterator<Shipment> s = shipmentList.getShipments(); s.hasNext();) {
            final Shipment value = s.next();
            if (value.getShipmentId() == 1) {
                value.makePayment(30.00);
            }

            else if (value.getShipmentId() == 2) {
                value.makePayment(50.00);
            }
        }
        System.out.println("\nMaking Shipment Payment\n" + shipmentList.toString());

    }
}
