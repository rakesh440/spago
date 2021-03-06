/* SpagoBI, the Open Source Business Intelligence suite

 * Copyright (C) 2012 Engineering Ingegneria Informatica S.p.A. - SpagoBI Competency Center
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0, without the "Incompatible With Secondary Licenses" notice. 
 * If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package it.eng.spagobi.kpi.config.metadata;
// Generated 5-nov-2008 17.16.36 by Hibernate Tools 3.1.0 beta3

import it.eng.spagobi.commons.metadata.SbiHibernateModel;
import it.eng.spagobi.kpi.model.metadata.SbiResources;
import it.eng.spagobi.kpi.ou.metadata.SbiOrgUnit;
import it.eng.spagobi.kpi.ou.metadata.SbiOrgUnitHierarchies;

import java.util.Date;


/**
 * SbiKpiValue generated by hbm2java
 */

public class SbiKpiValue  extends SbiHibernateModel {


    // Fields    
	
     private Integer idKpiInstanceValue;
     private SbiKpiInstance sbiKpiInstance;
     private SbiResources sbiResources;
     private String value;
     private Date beginDt;
     private Date endDt;
     private String description;
     private String xmlData; 
     private SbiOrgUnit sbiOrgUnit;
     private SbiOrgUnitHierarchies sbiOrgUnitHierarchies;
     private String company;

    // Constructors

   

	/** default constructor */
    public SbiKpiValue() {
    }

	/** minimal constructor */
    public SbiKpiValue(Integer idKpiInstanceValue, SbiKpiInstance sbiKpiInstance, SbiResources sbiResources) {
        this.idKpiInstanceValue = idKpiInstanceValue;
        this.sbiKpiInstance = sbiKpiInstance;
        this.sbiResources = sbiResources;
    }
	/** minimal constructor with ou*/
    public SbiKpiValue(Integer idKpiInstanceValue, SbiKpiInstance sbiKpiInstance, SbiResources sbiResources,SbiOrgUnit sbiOrgUnit, SbiOrgUnitHierarchies sbiOrgUnitHierarchies) {
        this.idKpiInstanceValue = idKpiInstanceValue;
        this.sbiKpiInstance = sbiKpiInstance;
        this.sbiResources = sbiResources;
        this.sbiOrgUnit = sbiOrgUnit;
        this.sbiOrgUnit = sbiOrgUnit;
        this.sbiOrgUnitHierarchies = sbiOrgUnitHierarchies;
    } 
    /** full constructor */
    public SbiKpiValue(Integer idKpiInstanceValue, SbiKpiInstance sbiKpiInstance, SbiResources sbiResources, String value, Date beginDt, Date endDt, String description, String xmlData) {
        this.idKpiInstanceValue = idKpiInstanceValue;
        this.sbiKpiInstance = sbiKpiInstance;
        this.sbiResources = sbiResources;
        this.value = value;
        this.beginDt = beginDt;
        this.endDt = endDt;
        this.description = description;
        this.xmlData = xmlData;
    }
    
    /** full constructor */
    public SbiKpiValue(Integer idKpiInstanceValue, SbiKpiInstance sbiKpiInstance, SbiResources sbiResources, String value, Date beginDt, Date endDt, String description, String xmlData, 
    					SbiOrgUnit sbiOrgUnit,
    					SbiOrgUnitHierarchies sbiOrgUnitHierarchies,
    					String company) {
        this.idKpiInstanceValue = idKpiInstanceValue;
        this.sbiKpiInstance = sbiKpiInstance;
        this.sbiResources = sbiResources;
        this.value = value;
        this.beginDt = beginDt;
        this.endDt = endDt;
        this.description = description;
        this.xmlData = xmlData;
        this.sbiOrgUnit = sbiOrgUnit;
        this.sbiOrgUnitHierarchies = sbiOrgUnitHierarchies;
        this.company = company;
    }
   
    // Property accessors

    public Integer getIdKpiInstanceValue() {
        return this.idKpiInstanceValue;
    }
    
    public void setIdKpiInstanceValue(Integer idKpiInstanceValue) {
        this.idKpiInstanceValue = idKpiInstanceValue;
    }

    public SbiKpiInstance getSbiKpiInstance() {
        return this.sbiKpiInstance;
    }
    
    public void setSbiKpiInstance(SbiKpiInstance sbiKpiInstance) {
        this.sbiKpiInstance = sbiKpiInstance;
    }

    public SbiResources getSbiResources() {
        return this.sbiResources;
    }
    
    public void setSbiResources(SbiResources sbiResources) {
        this.sbiResources = sbiResources;
    }

    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    public Date getBeginDt() {
        return this.beginDt;
    }
    
    public void setBeginDt(Date beginDt) {
        this.beginDt = beginDt;
    }

    public Date getEndDt() {
        return this.endDt;
    }
    
    public void setEndDt(Date endDt) {
        this.endDt = endDt;
    }
   
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getXmlData() {
		return xmlData;
	}

	public void setXmlData(String xmlData) {
		this.xmlData = xmlData;
	}

	public SbiOrgUnit getSbiOrgUnit() {
		return sbiOrgUnit;
	}

	public void setSbiOrgUnit(SbiOrgUnit sbiOrgUnit) {
		this.sbiOrgUnit = sbiOrgUnit;
	}

	public SbiOrgUnitHierarchies getSbiOrgUnitHierarchies() {
		return sbiOrgUnitHierarchies;
	}

	public void setSbiOrgUnitHierarchies(SbiOrgUnitHierarchies sbiOrgUnitHierarchies) {
		this.sbiOrgUnitHierarchies = sbiOrgUnitHierarchies;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
