<?xml version="1.0"?>
<!--
$Id: //open/mondrian-release/3.0/webapp/WEB-INF/mdxtable.xsl#2 $

This software is subject to the terms of the Common Public License
Agreement, available at the following URL:
http://www.opensource.org/licenses/cpl.html.
(C) Copyright 2002-2005 Kana Software, Inc. and others.
All Rights Reserved.
You must accept the terms of that agreement to use this software.
-->

<!DOCTYPE xsl:stylesheet [
  <!ENTITY nbsp "&#160;">
]>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:tt="http://www.tonbeller.com/bii/treetable"
>

<xsl:output method="html" indent="yes"/>


<xsl:template match="mdxtable">

 <style type="text/css">
   <xsl:text>

/* top columns */
th.column-heading {
  background-color : #DEE3EF;
  font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
  font-size :10pt;
  color : Black;
}

/* row headings */
th.row-heading-even {
  background-color : #DEE3EF;
  font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
  font-size :10pt;
  color : Black;
}

th.row-heading-odd {
  background-color : #EEF3FF;
  font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
  font-size :10pt;
  color : Black;
}

th.row-heading-span {
  background-color : #DEE3EF;
  font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
  font-size :10pt;
  color : Black;
}

/* data cells */
td.cell-even {
  background-color : #f0f0f0;
  font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
  font-size :10pt;
  color : Black;
}
td.cell-odd {
  background-color : #ffffff;
  font-family: Verdana, Geneva, Arial, Helvetica, sans-serif;
  font-size :10pt;
  color : Black;
}
   </xsl:text>
 </style>



  <table border="1" cellspacing="0" cellpadding="2">
    <xsl:apply-templates select="head"/>
    <xsl:apply-templates select="body"/>
  </table>
</xsl:template>


<xsl:template match="head | body">
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="row">
  <tr>
    <xsl:apply-templates/>
  </tr>
</xsl:template>

<xsl:template match="corner">
  <th nowrap="nowrap" class="column-heading" colspan="{@colspan}" rowspan="{@rowspan}">
    <xsl:text>&nbsp;</xsl:text>
  </th>
</xsl:template>


<xsl:template match="column-heading">
  <th nowrap="nowrap" class="column-heading" colspan="{@colspan}" rowspan="{@rowspan}">
    <xsl:value-of select="@caption"/>
    <xsl:apply-templates select="property"/>
  </th>
</xsl:template>


<xsl:template match="row-heading">
  <th align="left" nowrap="nowrap" class="row-heading-{@style}" colspan="{@colspan}" rowspan="{@rowspan}">
    <!--xsl:value-of select="@depth"/ -->
    <xsl:value-of select="@caption"/>
    <xsl:apply-templates select="property"/>
  </th>
</xsl:template>

<xsl:template match="property">
<!--
  <xsl:text>[</xsl:text>
  <xsl:value-of select="@name"/>
  <xsl:text>=</xsl:text>
  <xsl:value-of select="@value"/>
  <xsl:text>]</xsl:text>
-->
</xsl:template>


<xsl:template match="cell">
  <td nowrap="nowrap" class="cell-{@style}" align="right">
    <xsl:value-of select="@value"/>
  </td>
</xsl:template>

<xsl:template match="*|@*|node()">
  <xsl:copy>
    <xsl:apply-templates select="*|@*|node()"/>
  </xsl:copy>
</xsl:template>

</xsl:stylesheet>
