/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vehiclemanagement;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import gr.tuc.music.xmldb.connector.impl.ExistRpcConnector;
import gr.tuc.music.xmldb.connector.impl.ExistRpcConnectorException;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.xmlbeans.XmlException;
import org.netbeans.xml.schema.vehicleManagement.VehicleDocument;

/**
 *  this class has the basic functions to be performed by a client
 * the create/delete/insert and update functions are for creating an xml doc,deleting a doc or a collection inserting a premade xml doc
 * and updating an xml doc from the db, respectively.
 * execQueryafter is for elem>value
 * execQuerybefore is for elem<value
 * execQuery conc is a special query made by string concatenations, in order
 * to create more complex querys
 * it takes elem,elem,..,elem operand,..,operand value,..,value and and/or,..,and/or,[space] and at last the number of the
 * elements included and makes elem operand value and/or by concatenating the above strings
 * execQueryEq is for equation and EqOr EqAnd is for equation or equation / equation and equation respectively
 * execQuery range is for ranged query x<=value<y
 * @author Sarantis
 */
public class ExistXmlDbClient 
{
    public static void main(String[] args) {
        ExistXmlDbClient exdbcl = new ExistXmlDbClient();
        ExistRpcConnector db = null;
        Xslttrans xt = new Xslttrans();
        String collection = "myproject/vehicles";
        String filename = "myVehicle.xml";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            DatatypeFactory df = DatatypeFactory.newInstance();
            GregorianCalendar xg = new GregorianCalendar(2012, 0, 18);
            //XMLGregorianCalendar xg1 = df.newXMLGregorianCalendarDate(2012, 1, 18);
            GregorianCalendar xg2 = new GregorianCalendar(2007, 10, 28);
            db = new ExistRpcConnector("http://localhost:8080/exist/xmlrpc", "admin", "");
            if (db != null) {
                System.out.println("Connected to Database");     
               
                
                //*/insert method with filename variable in it
                exdbcl.insert(db,filename, collection);
                exdbcl.insert(db,"myVehicle00.xml", collection);
                exdbcl.insert(db,"myVehicle01.xml", collection);
                exdbcl.insert(db,"myVehicle02.xml", collection);
                exdbcl.insert(db,"myVehicle03.xml", collection);
                
                //*/
                //exdbcl.update(db, filename, collection, "ModelName", "gsxr");
                //Now search for Vehicles of Model CBR
                //String query = ("declare default element namespace 'http://xml.netbeans.org/schema/VehicleManagement';"
                //        + "for $vehicle in collection ('myproject')/Vehicle where $vehicle/VehicleDetails/CreationDate='"+xg+"' return $vehicle");
                //String query = exdbcl.execQueryEq("CreationDate", format.format(xg2.getTime()));
                //String query = exdbcl.execQueryEq("WarrantyStart", format.format(xg.getTime()));
                //String query = exdbcl.execQueryEq("CreationDate", xg.toString());
                //String query = exdbcl.execQueryConc("CreationDate,WarrantyStart","<,=", format.format(xg2.getTime())+","+format.format(xg.getTime()), "or, ",2);
                String query = exdbcl.execQueryEqOr("ModelName", "CBR", "Manufacturer", "Honda");
                System.out.println("\n"+query+"\n");
                String results = db.executeQuery(query);
                System.out.println(results+"\nholy\n"+format.format(xg.getTime())+"\n"+results.split("</veh:Vehicle>")[0]);
                //Bind the Resulted Document...
                VehicleDocument outVDoc = null;
                try {
                    outVDoc = org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Factory.parse(results.split("</veh:Vehicle>")[0]+"</veh:Vehicle>");
                    System.out.println(outVDoc.toString());
                    String dc = xt.Transformation(outVDoc.toString());
                } catch (XmlException ex) {
                    //handle exception    
                    ex.printStackTrace();
                } 
                System.out.println("Vehicle input");
                System.out.println(outVDoc.toString());
                //exdbcl.deleteD(db, filename, collection);
                
            }
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(ExistXmlDbClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExistRpcConnectorException ex) {
            Logger.getLogger(ExistXmlDbClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insert(ExistRpcConnector db,String filename,String collection)
    {

        try {
            db.createCollection(collection);
            //Store 2 new Orders in the Repository
            VehicleDocument inVDoc = null;
            //Documents are parsed with the Schema-aware API that we produced in the previous Lab
            //with XML Beans. Typically this will be done by getting some String params (or a stream) from a front-end
            inVDoc = org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Factory.parse(new File(filename));                    
            //Now store the (Valid) xml document into your repository.
            System.out.println(inVDoc.getVehicle().getVehicleDetails().getCreationDate().getTime());
            db.storeXmlDocument(filename, inVDoc.toString(), collection, true); 
            //inVDoc = org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Factory.parse(new File("order01.xml"));
            //Now store the (Valid) xml document into your repository.
            //db.storeXmlDocument("order01.xml", inVDoc.toString(), "myproject/vehicles", true);
            } catch (XmlException ex) {
                //handle exception    
                ex.printStackTrace();
            } catch (IOException ex) {
                //handle exceptionF
                ex.printStackTrace();
            }catch (ExistRpcConnectorException ex) {
            Logger.getLogger(ExistXmlDbClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void create(ExistRpcConnector db,String filename,String collection,String CoManufacturer, String Manufacturer, String CopyrightAgreement, int cdyear,int cdmonth,int cdday, String ModelName, String VehicleAncestor, String VehicleDescendant, String VehicleIdentificationNumber, String VehicleType, String Distributor, int weyear,int wemonth,int weday, int wsyear,int wsmonth,int wsday, String DistributionManualLanguage)
    {
        try {
            VehicleCreator vc = new VehicleCreator();
            VehicleDocument vdoc = vc.createV(CoManufacturer, Manufacturer, CopyrightAgreement, new GregorianCalendar(cdyear,cdmonth,cdday), ModelName, VehicleAncestor, VehicleDescendant, VehicleIdentificationNumber, VehicleType, Distributor, new GregorianCalendar(weyear,wemonth,weday), new GregorianCalendar(wsyear,wsmonth,wsday), DistributionManualLanguage);
            db.storeXmlDocument(filename, vdoc.toString(), collection, true);
        } catch (ExistRpcConnectorException ex) {
            Logger.getLogger(ExistXmlDbClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void update(ExistRpcConnector db,String filename,String collection,String elem, String val)
    {
        try {
            String res = db.getXmlDocument(filename, collection);
            System.out.println(res);
            VehicleDocument inVDoc = null;
            inVDoc = org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Factory.parse(res);
            res=res.replace(res.substring(res.indexOf("<veh:"+elem), res.indexOf("</veh:"+elem+">")+("</veh:"+elem+">").length()), "<veh:"+elem+">"+val+"</veh:"+elem+">");
            System.out.println(res);
            inVDoc = org.netbeans.xml.schema.vehicleManagement.VehicleDocument.Factory.parse(res);
            db.storeXmlDocument(filename, inVDoc.toString(), collection, true);
        } catch (XmlException ex) {
            Logger.getLogger(ExistXmlDbClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExistRpcConnectorException ex) {
            Logger.getLogger(ExistXmlDbClient.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    public void deleteD(ExistRpcConnector db,String filename,String collection)
    {
        try {
            db.removeDocument(filename, collection);
        } catch (ExistRpcConnectorException ex) {
            Logger.getLogger(ExistXmlDbClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteC(ExistRpcConnector db,String collection)
    {
        try {
            db.removeCollection(collection);
        } catch (ExistRpcConnectorException ex) {
            Logger.getLogger(ExistXmlDbClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String execQueryEq(String elem,String val)
    {
        String query = ("declare default element namespace 'http://xml.netbeans.org/schema/VehicleManagement';"
                 + "for $vehicle in collection ('myproject')/Vehicle where $vehicle//"+elem+"='"+val+"' return $vehicle");
        return query;
    }
    
    public String execQueryRange(String elem,String val,String elem1,String val1)
    {
        String query = ("declare default element namespace 'http://xml.netbeans.org/schema/VehicleManagement';"
                 + "for $vehicle in collection ('myproject')/Vehicle where $vehicle//"+elem+">='"+val+"' and $vehicle//"+elem1+"<'"+val1+"' return $vehicle");
        return query;
    }
    
    public String execQueryEqAnd(String elem,String val,String elem1,String val1)
    {
        String query = ("declare default element namespace 'http://xml.netbeans.org/schema/VehicleManagement';"
                 + "for $vehicle in collection ('myproject')/Vehicle where $vehicle//"+elem+"='"+val+"' and $vehicle//"+elem1+"='"+val1+"' return $vehicle");
        return query;
    }
    
    public String execQueryEqOr(String elem,String val,String elem1,String val1)
    {
        String query = ("declare default element namespace 'http://xml.netbeans.org/schema/VehicleManagement';"
                 + "for $vehicle in collection ('myproject')/Vehicle where $vehicle//"+elem+"='"+val+"' or $vehicle//"+elem1+"='"+val1+"' return $vehicle");
        return query;
    }
    
    public String execQueryBefore(String elem,String val)
    {
        String query = ("declare default element namespace 'http://xml.netbeans.org/schema/VehicleManagement';"
                 + "for $vehicle in collection ('myproject')/Vehicle where $vehicle//"+elem+"<'"+val+"' return $vehicle");
        return query;
    }
    
    public String execQueryAfter(String elem,String val)
    {
        String query = ("declare default element namespace 'http://xml.netbeans.org/schema/VehicleManagement';"
                 + "for $vehicle in collection ('myproject')/Vehicle where $vehicle//"+elem+">'"+val+"' return $vehicle");
        return query;
    }
    
    public String execQueryConc(String elem,String op,String val,String ao,int siz)
    {
        
        String query = ("declare default element namespace 'http://xml.netbeans.org/schema/VehicleManagement';"
                 + "for $vehicle in collection ('myproject')/Vehicle where $vehicle//");
        
        for(int i=0;i<siz;i++)
        {
            query = query.concat(elem.split(",")[i]+op.split(",")[i]+"'"+val.split(",")[i]+"'"+" "+ao.split(",")[i]+" ");
        }
        query = query.concat("return $vehicle");
        return query;
    }
}
