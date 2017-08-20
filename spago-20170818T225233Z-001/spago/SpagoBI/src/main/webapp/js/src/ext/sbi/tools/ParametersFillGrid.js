/** SpagoBI, the Open Source Business Intelligence suite

 * Copyright (C) 2012 Engineering Ingegneria Informatica S.p.A. - SpagoBI Competency Center
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0, without the "Incompatible With Secondary Licenses" notice. 
 * If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/. **/
 
  
 
  

/**
 * Object name
 * 
 * [description]
 * 
 * 
 * Public Properties
 * 
 * [list]
 * 
 * 
 * Public Methods
 * 
 * [list]
 * 
 * 
 * Public Events
 * 
 * [list]
 * 
 * Authors - Chiara Chiarelli
 */
Ext.ns("Sbi.tools");

Sbi.tools.ParametersFillGrid = function(config) { 
		
	// create the editor grid
	    var grid = {
	    	xtype: 'grid',
	    	title:  LN('sbi.ds.fillPars'),
	        width: 450,
	        height: 250,
	        autoScroll: true,
	        source: config.pars,
	        forceLayout: true,
	        frame: true,
	        deferRowRender : false,
	        propertyNames: {
	            tested: 'QA',
	            borderWidth: 'Border Width'
	        },
	        viewConfig : {
	            forceFit: true,
	            scrollOffset: 2 // the grid will never have scrollbars
	        }
	        };

    var c = Ext.apply( {}, config, grid);
    
    // constructor
    Sbi.tools.ParametersFillGrid.superclass.constructor.call(this, c);
    
    this.on('beforeedit', function(e) {
    	var t = Ext.apply({}, e);
    	var col = t.column;
    	this.currentRowRecordEdited = t.row;	
    	
    }, this);
    
    this.on('afteredit', function(e) {
    	
    	var col = e.column;
    	var row = e.row;	
    	
    }, this);

};

Ext.extend(Sbi.tools.ParametersFillGrid, Ext.grid.PropertyGrid, {
  
	fillParameters: function(parsList){
	/*	var valueToInsert = null;*/
		//	remove preceding content
	//	this.store.removeAll(true);
	this.store.remove(record);
	Sbi.trace("store......." + this.getStore().getCount());
	var count = this.getStore().getCount();for(j=0; j<count.length; j++){this.store.removeAt(j);}
		if(parsList){
			for(i = 0; i<parsList.length;i++){
				var singlePar = parsList[i];
				
				//var valueToInsert = null;
			
				var typeData = singlePar.type;
				if(!typeData){
					typeData = 'STRING';
					Sbi.trace("STRING.......if typedata false");
				}
				else{
					typeData = typeData.toUpperCase();
					Sbi.trace("no STRING.......");
				}
				//convert value to the right type
				if(typeData == 'STRING'){
					if(valueToInsert == null){
						valueToInsert = '';
						Sbi.trace("STRING.......");
					}
				}else if (typeData == 'NUMBER'){
					if(valueToInsert == null){
						valueToInsert = parseFloat(0);	
						Sbi.trace("number.......");
					}
					else{
						valueToInsert = parseFloat(valueToInsert);
						Sbi.trace("number.......not null");
					}						
				} else {
					// case text
					if(valueToInsert == null){
						valueToInsert = '';
						Sbi.trace("else.......null");
						//this.store.remove(record);
					}
				}
			
				var tempRecord = new Ext.data.Record({"name": singlePar.name,"type": typeData, "value": valueToInsert});
				Sbi.trace("brefore store.add");
				this.store.add(tempRecord);
				Sbi.trace("after store.add"+tempRecord);
				Sbi.trace("store......." + this.getStore().getCount());
				/*var count = this.getStore().getCount();
				for(j=0; j<count.length; j++){
					
			}*/
			
			}
			var count = this.getStore().getCount();
			for(j=0; j<count.length; j++){
				
		}
			this.store.removeAt(0);this.store.removeAt(1);}
	  this.getView().refresh();
	}
	
	// return array with values in grid
	,getParametersValues: function(){
	    var arrayPars = new Array();
			var storePars = this.getStore();
			var length = storePars.getCount();
			for(var i = 0;i< length;i++){
				var item = storePars.getAt(i);
				var data = item.data;
				arrayPars.push(data);
			}
		return arrayPars;
	}
	
});

