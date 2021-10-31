/*
 * semanticcms-core-view-content - SemanticCMS view of the content of the current page.
 * Copyright (C) 2021  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-core-view-content.
 *
 * semanticcms-core-view-content is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-core-view-content is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-core-view-content.  If not, see <https://www.gnu.org/licenses/>.
 */
module com.semanticcms.core.view.content {
	exports com.semanticcms.core.view.content;
	// Direct
	requires com.aoapps.collections; // <groupId>com.aoapps</groupId><artifactId>ao-collections</artifactId>
	requires com.aoapps.html.servlet; // <groupId>com.aoapps</groupId><artifactId>ao-fluent-html-servlet</artifactId>
	requires com.aoapps.servlet.util; // <groupId>com.aoapps</groupId><artifactId>ao-servlet-util</artifactId>
	requires com.aoapps.taglib; // <groupId>com.aoapps</groupId><artifactId>ao-taglib</artifactId>
	requires com.aoapps.web.resources.registry; // <groupId>com.aoapps</groupId><artifactId>ao-web-resources-registry</artifactId>
	requires javax.servlet.api; // <groupId>javax.servlet</groupId><artifactId>javax.servlet-api</artifactId>
	requires javax.servlet.jsp.api; // <groupId>javax.servlet.jsp</groupId><artifactId>javax.servlet.jsp-api</artifactId>
	requires org.joda.time; // <groupId>joda-time</groupId><artifactId>joda-time</artifactId>
	requires joda.time.jsptags; // <groupId>joda-time</groupId><artifactId>joda-time-jsptags</artifactId>
	requires com.semanticcms.core.controller; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-controller</artifactId>
	requires com.semanticcms.core.model; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-model</artifactId>
	requires com.semanticcms.core.renderer.html; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-renderer-html</artifactId>
	requires com.semanticcms.core.taglib; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-taglib</artifactId>
	requires taglibs.standard.spec; // <groupId>org.apache.taglibs</groupId><artifactId>taglibs-standard-spec</artifactId>
	// Transitive
	requires com.semanticcms.core.renderer; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-renderer</artifactId>
}
