<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:ns1="http://www.ebayopensource.org/turmeric/security/v1/services"
  xmlns:json="http://json.org/">

<xsl:import href="xml-to-json.xsl"/>

<xsl:output method="text"/>

<xsl:template match="/">
<xsl:value-of select="json:generate(//ns1:Policy)"></xsl:value-of>
</xsl:template>
</xsl:stylesheet>