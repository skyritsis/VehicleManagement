/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclemanagement;
import java.io.File;
import java.io.IOException;
import org.netbeans.xml.schema.vehicleManagement.VehicleDocument;
import org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Vehicle;
import org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Vehicle.CompaniesAssociated;
import org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Vehicle.VehicleDetails;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Vehicle.DistributionDetails;
import org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Vehicle.DistributionDetails.DistributorWarranty;

/**
 *
 * @author Sarantis
 */
public class VehicleCreator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        VehicleDocument vdoc = org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Factory.newInstance();
        Vehicle veh = vdoc.addNewVehicle();
        Random r = new Random();
        Calendar c = new GregorianCalendar();
        //
        CompaniesAssociated ca = veh.addNewCompaniesAssociated();
        ca.setCoManufacturer("Bilstain");
        ca.setManufacturer("Kawasaki");
        ca.setCopyrightAgreement("www.honda.gr");
        //
        VehicleDetails vd = veh.addNewVehicleDetails();
        vd.setCreationDate(new GregorianCalendar(r.nextInt(12)+2000,r.nextInt(12),r.nextInt(30)));
        vd.setModelName("zrx400");
        //
        DistributionDetails dd = veh.addNewDistributionDetails();
        dd.setDistributor("Kyritsis");
        DistributorWarranty dw = dd.addNewDistributorWarranty();
        dw.setWarrantyEnd(new GregorianCalendar(r.nextInt(12)+2000,r.nextInt(12),r.nextInt(30)));
        dw.setWarrantyStart(new GregorianCalendar(r.nextInt(12)+2000,r.nextInt(12),r.nextInt(30)));
        dd.setDistributorWarranty(dw);
        veh.setCompaniesAssociated(ca);
        veh.setDistributionDetails(dd);
        veh.setVehicleDetails(vd);
        vdoc.setVehicle(veh);
        System.out.println(vdoc.toString());
        String fileName = "myVehicle.xml";
        try {
            vdoc.save(new File(fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public VehicleDocument createV(String CoManufacturer, String Manufacturer, String CopyrightAgreement, Calendar CreationDate, String ModelName, String VehicleAncestor, String VehicleDescendant, String VehicleIdentificationNumber, String VehicleType, String Distributor, Calendar WarrantyEnd, Calendar WarrantyStart, String DistributionManualLanguage)
    {
        VehicleDocument vdoc =org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Factory.newInstance();
        Vehicle veh = vdoc.addNewVehicle();
        //
        CompaniesAssociated ca = veh.addNewCompaniesAssociated();
        ca.setCoManufacturer(CoManufacturer);
        ca.setManufacturer(Manufacturer);
        ca.setCopyrightAgreement(CopyrightAgreement);
        //
        VehicleDetails vd = veh.addNewVehicleDetails();
        vd.setCreationDate(CreationDate);
        vd.setModelName(ModelName);
        vd.setVehicleAncestor(VehicleAncestor);
        vd.setVehicleDescendant(VehicleDescendant);
        vd.setVehicleIdentificationNumber(VehicleIdentificationNumber);
        vd.setVehicleType(VehicleType);
        //
        DistributionDetails dd = veh.addNewDistributionDetails();
        dd.setDistributor(Distributor);
        DistributorWarranty dw = dd.addNewDistributorWarranty();
        dw.setWarrantyEnd(WarrantyEnd);
        dw.setWarrantyStart(WarrantyStart);
        dd.setDistributionManualLanguage(DistributionManualLanguage);
        dd.setDistributorWarranty(dw);
        //
        veh.setCompaniesAssociated(ca);
        veh.setDistributionDetails(dd);
        veh.setVehicleDetails(vd);
        vdoc.setVehicle(veh);
        return vdoc;
    }
    
}
