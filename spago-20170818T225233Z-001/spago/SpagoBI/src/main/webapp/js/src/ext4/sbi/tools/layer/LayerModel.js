/** SpagoBI, the Open Source Business Intelligence suite

 * Copyright (C) 2012 Engineering Ingegneria Informatica S.p.A. - SpagoBI Competency Center
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0, without the "Incompatible With Secondary Licenses" notice. 
 * If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/. **/


Ext.define('Sbi.tools.layer.LayerModel', {
	extend: 'Ext.data.Model',

	proxy:{
		type: 'rest',
		url : Sbi.config.serviceRegistry.getRestServiceUrl({serviceName: 'layers'}),
		reader: {
			type: 'json',
			root: 'root'
		}
	},
	fields: [
	         "type",
	         "id",
	         "name",
	         "descr",
	         "label", 
	         "baseLayer",
	         "propsFile",
	         "propsUrl",
	         "propsName",
	         "propsLabel",
	         "propsZoom",
	         "propsId",
	         "propsCentralPoint",
	         "propsParams",
	         "propsOptions"
	         ]
});