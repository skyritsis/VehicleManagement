/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclemanagement;

import java.io.File;
import java.io.IOException;
import org.apache.xmlbeans.XmlException;
import org.netbeans.xml.schema.vehicleManagement.VehicleDocument;
import org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Vehicle;

/**
 *
 * @author Sarantis
 */
public class VehicleHandler {
    public VehicleHandler() {
                
        }
        
        public static void main (String[] Args){
                VehicleDocument vDoc = null;
                String fileName = "myVehicle.xml";
                //Populate the orderDoc with the contents of the stored xml File
                try {
                        vDoc = org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Factory.parse(new File(fileName));                                                
                } catch (XmlException ex) {
                        ex.printStackTrace();
                } catch (IOException ex) {
                        ex.printStackTrace();
                }
                //Get the Order root Element from the OrderDocument
                Vehicle veh = vDoc.getVehicle();
                //Get the Order's Customer
               /* Customer customer = ord.getCustomer();
                System.out.println("This Order is placed by:\n Customer: " + customer.getName() + "\n From:" + customer.getAddress());
                //Get the Order's Line Items (more than one according to the xsd...)
                LineItem[] orderedProducts =  ord.getLineItemArray();
                LineItem item;
                System.out.println("The Customers Orders these products:");
                //Parse the Line Items and Print their information...
                for (int i=0; i<orderedProducts.length; i++){
                        item = orderedProducts[i];
                        System.out.println(" Product " + i + ": " + item.getProduct());
                }*/

        }
}
