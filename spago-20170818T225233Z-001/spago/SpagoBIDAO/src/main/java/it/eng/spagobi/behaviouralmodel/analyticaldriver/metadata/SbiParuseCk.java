/* SpagoBI, the Open Source Business Intelligence suite

 * Copyright (C) 2012 Engineering Ingegneria Informatica S.p.A. - SpagoBI Competency Center
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0, without the "Incompatible With Secondary Licenses" notice. 
 * If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package it.eng.spagobi.behaviouralmodel.analyticaldriver.metadata;

import it.eng.spagobi.commons.metadata.SbiHibernateModel;





/**
 * SbiParuseCk generated by hbm2java
 */
public class SbiParuseCk  extends SbiHibernateModel {

    // Fields    

     private SbiParuseCkId id;
     private Integer prog;


    // Constructors

    /**
     * default constructor.
     */
    public SbiParuseCk() {
    }
    
    /**
     * constructor with id.
     * 
     * @param id the id
     */
    public SbiParuseCk(SbiParuseCkId id) {
        this.id = id;
    }
   
    
    

    // Property accessors

    /**
     * Gets the id.
     * 
     * @return the id
     */
    public SbiParuseCkId getId() {
        return this.id;
    }
    
    /**
     * Sets the id.
     * 
     * @param id the new id
     */
    public void setId(SbiParuseCkId id) {
        this.id = id;
    }

    /**
     * Gets the prog.
     * 
     * @return the prog
     */
    public Integer getProg() {
        return this.prog;
    }
    
    /**
     * Sets the prog.
     * 
     * @param prog the new prog
     */
    public void setProg(Integer prog) {
        this.prog = prog;
    }



}