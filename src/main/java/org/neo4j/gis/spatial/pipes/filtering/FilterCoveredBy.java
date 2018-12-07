/**
 * Copyright (c) 2010-2017 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j Spatial.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.gis.spatial.pipes.filtering;

import org.neo4j.gis.spatial.pipes.AbstractFilterGeoPipe;
import org.neo4j.gis.spatial.pipes.GeoPipeFlow;

import com.vividsolutions.jts.geom.Envelope;
import com.vividsolutions.jts.geom.Geometry;


/**
 * Find geometries covered by the given geometry
 */
public class FilterCoveredBy extends AbstractFilterGeoPipe {

	private Geometry other;
	private Envelope otherEnvelope;
	
	public FilterCoveredBy(Geometry other) {
		this.other = other;
		this.otherEnvelope = other.getEnvelopeInternal();
	}

	@Override
	protected boolean validate(GeoPipeFlow flow) {
		// check if every point of this geometry is a point of the other geometry
	    return otherEnvelope.covers(flow.getEnvelope()) 
	    		&& flow.getGeometry().coveredBy(other);		
	}

}