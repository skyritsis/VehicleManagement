<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : veh2dc.xsl
    Created on : January 23, 2012, 11:35 AM
    Author     : Sarantis
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
    xmlns:v="http://xml.netbeans.org/schema/VehicleManagement"
    xmlns="http://purl.org/dc/elements/1.1/">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" omit-xml-declaration="yes"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <xsl:element name="Vehicle">
            <xsl:apply-templates select="/v:Vehicle/v:VehicleDetails/v:ModelName"/>
        </xsl:element>
    </xsl:template>
    
    <xsl:template match="v:ModelName">
        <xsl:element name="title">
            <xsl:value-of select="."/>
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>
