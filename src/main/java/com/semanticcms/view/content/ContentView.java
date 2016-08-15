/*
 * semanticcms-view-content - SemanticCMS view of the content of the current page.
 * Copyright (C) 2016  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-view-content.
 *
 * semanticcms-view-content is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-view-content is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-view-content.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.semanticcms.view.content;

import com.aoindustries.servlet.http.Dispatcher;
import com.semanticcms.core.model.Page;
import com.semanticcms.core.servlet.SemanticCMS;
import com.semanticcms.core.servlet.View;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.SkipPageException;

/**
 * The default content view.
 */
public class ContentView extends View {

	static final String VIEW_NAME = SemanticCMS.DEFAULT_VIEW_NAME;

	private static final String JSPX_TARGET = "/semanticcms-view-content/view.inc.jspx";

	@Override
	public Group getGroup() {
		return Group.FIRST;
	}

	@Override
	public String getDisplay() {
		return "Content";
	}

	@Override
	public String getName() {
		return VIEW_NAME;
	}

	@Override
	public String getTitle(Page page) {
		return page.getTitle() + TITLE_SEPARATOR + page.getPageRef().getBook().getTitle();
	}

	@Override
	public String getDescription(Page page) {
		return page.getDescription();
	}

	@Override
	public String getKeywords(Page page) {
		return page.getKeywords();
	}

	@Override
	public List<String> getCssLinks() {
		return Collections.singletonList("/styles/toc.css");
	}

	/**
	 * TODO: Add "allowRobots" property to page, too, and exclude any views to a non-robot page.
	 *       Also exclude from automatic site maps.
	 */
	@Override
	public boolean getAllowRobots(Page page) {
		return true;
	}

	@Override
	public void doView(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException, SkipPageException {
		Dispatcher.include(
			servletContext,
			JSPX_TARGET,
			request,
			response,
			Collections.singletonMap("page", page)
		);
	}
}
