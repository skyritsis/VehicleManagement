<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : veh2dc.xsl
    Created on : January 23, 2012, 11:35 AM
    Author     : Sarantis
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
    xmlns:veh="http://xml.netbeans.org/schema/VehicleManagement"
    xmlns="http://purl.org/dc/elements/1.1/">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" omit-xml-declaration="yes"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:element name="veh:Vehicle">
            <xsl:apply-templates select="/veh:Vehicle/veh:VehicleDetails/veh:ModelName"/>
            <xsl:apply-templates select="/veh:Vehicle/veh:VehicleDetails/veh:VehicleIdentificationNumber"/>
            <xsl:apply-templates select="/veh:Vehicle/veh:VehicleDetails/veh:VehicleType"/>
            <xsl:apply-templates select="/veh:Vehicle/veh:VehicleDetails/veh:CreationDate"/>
            <xsl:apply-templates select="/veh:Vehicle/veh:VehicleDetails/veh:VehicleAncestor"/>
            <xsl:apply-templates select="/veh:Vehicle/veh:VehicleDetails/veh:VehicleDescendant"/>
            
            <xsl:apply-templates select="/veh:Vehicle/veh:DistributionDetails/veh:Distributor"/>
            <xsl:apply-templates select="/veh:Vehicle/veh:DistributionDetails/veh:DistributionManualLanguage"/>
            <xsl:apply-templates select="/veh:Vehicle/veh:DistributionDetails/veh:DistributorWarranty"/>
            
            <xsl:apply-templates select="/veh:Vehicle/veh:CompaniesAssociated/veh:Manufacturer"/>
            <xsl:apply-templates select="/veh:Vehicle/veh:CompaniesAssociated/veh:CoManufacturer"/>
            <xsl:apply-templates select="/veh:Vehicle/veh:CompaniesAssociated/veh:CopyrightAgreement"/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="veh:VehicleDetails/veh:ModelName">
        <xsl:element name="title">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="veh:VehicleDetails/veh:VehicleIdentificationNumber">
        <xsl:element name="identifier">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="veh:VehicleDetails/veh:CreationDate">
        <xsl:element name="date">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="veh:VehicleDetails/veh:VehicleAncestor">
        <xsl:element name="Source">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="veh:VehicleDetails/veh:VehicleDescendant">
        <xsl:element name="relation">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="veh:VehicleDetails/veh:VehicleType">
        <xsl:element name="type">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="veh:DistributionDetails/veh:Distributor">
        <xsl:element name="publisher">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="veh:DistributionDetails/veh:DistributionManualLanguage">
        <xsl:element name="language">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="veh:DistributionDetails/veh:DistributorWarranty">
        <xsl:element name="coverage">
            Start: <xsl:value-of select="veh:WarrantyStart"/>
            End: <xsl:value-of select="veh:WarrantyEnd"/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="veh:CompaniesAssociated/veh:Manufacturer">
        <xsl:element name="creator">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="veh:CompaniesAssociated/veh:CoManufacturer">
        <xsl:element name="contributor">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="veh:CompaniesAssociated/veh:CopyrightAgreement">
        <xsl:element name="rights">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
