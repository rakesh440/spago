/**

SpagoBI - The Business Intelligence Free Platform

Copyright (C) 2005-2010 Engineering Ingegneria Informatica S.p.A.

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

**/
package it.eng.spagobi.dataset.cache.impl.sqldbcache.test.hsqldb;

import org.apache.log4j.Logger;

import it.eng.spagobi.dataset.cache.impl.sqldbcache.test.AbstractSQLDBCacheTest;
import it.eng.spagobi.dataset.cache.test.TestConstants;
import it.eng.spagobi.dataset.cache.test.TestDataSourceFactory;

/**
 * @author Marco Cortella (marco.cortella@eng.it)
 *
 */
public class HsqldbSQLDBCacheTest extends AbstractSQLDBCacheTest {
	
	static private Logger logger = Logger.getLogger(HsqldbSQLDBCacheTest.class);


	//Create Datasources specific for this test
	@Override
	public void createDataSources(){
		dataSourceReading = TestDataSourceFactory.createDataSource(TestConstants.DatabaseType.MYSQL, false);
		dataSourceWriting = TestDataSourceFactory.createDataSource(TestConstants.DatabaseType.HSQLDB, true);
	}
}
