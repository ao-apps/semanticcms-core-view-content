/*
 * semanticcms-core-view-content - SemanticCMS view of the content of the current page.
 * Copyright (C) 2016, 2017, 2020  AO Industries, Inc.
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
 * along with semanticcms-core-view-content.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.semanticcms.core.view.content;

import com.aoindustries.html.Html;
import com.aoindustries.servlet.http.Dispatcher;
import com.aoindustries.util.AoArrays;
import com.aoindustries.web.resources.registry.Registry;
import com.semanticcms.core.controller.PageUtils;
import com.semanticcms.core.controller.SemanticCMS;
import com.semanticcms.core.model.Link;
import com.semanticcms.core.model.Page;
import com.semanticcms.core.renderer.html.HtmlRenderer;
import com.semanticcms.core.renderer.html.Theme;
import com.semanticcms.core.renderer.html.View;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.SkipPageException;
import org.joda.time.ReadableInstant;

/**
 * The default content view.
 */
public class ContentView extends View {

	/**
	 * @see  Link#DEFAULT_VIEW_NAME
	 */
	public static final String NAME = Link.DEFAULT_VIEW_NAME;

	private static final String JSPX_TARGET = "/semanticcms-core-view-content/view.inc.jspx";

	@WebListener("Registers the \"" + NAME + "\" view in HtmlRenderer.")
	public static class Initializer implements ServletContextListener {
		@Override
		public void contextInitialized(ServletContextEvent event) {
			HtmlRenderer.getInstance(event.getServletContext()).addView(new ContentView());
		}
		@Override
		public void contextDestroyed(ServletContextEvent event) {
			// Do nothing
		}
	}

	private ContentView() {}

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
		return NAME;
	}

	@Override
	public boolean getAppliesGlobally() {
		return false;
	}

	/**
	 * The last modified time of a page is the most recent of:
	 * <ol>
	 *   <li>{@link Page#getDateCreated()}</li>
	 *   <li>{@link Page#getDatePublished()}</li>
	 *   <li>{@link Page#getDateModified()}</li>
	 * </ol>
	 * Note: {@link Page#getDateReviewed()} is left out because a change in its value alone does not indicate a change in content.
	 */
	@Override
	public ReadableInstant getLastModified(
		ServletContext servletContext,
		HttpServletRequest request,
		HttpServletResponse response,
		Page page
	) throws ServletException, IOException {
		return AoArrays.maxNonNull(
			page.getDateCreated(),
			page.getDatePublished(),
			page.getDateModified()
		);
	}

	@Override
	public String getTitle(
		ServletContext servletContext,
		HttpServletRequest request,
		HttpServletResponse response,
		Page page
	) {
		String bookTitle = SemanticCMS.getInstance(servletContext).getBook(page.getPageRef().getBookRef()).getTitle();
		if(bookTitle != null && !bookTitle.isEmpty()) {
			return page.getTitle() + TITLE_SEPARATOR + bookTitle;
		} else {
			return page.getTitle();
		}
	}

	@Override
	public String getDescription(Page page) {
		return page.getDescription();
	}

	@Override
	public String getKeywords(Page page) {
		return page.getKeywords();
	}

	/**
	 * Uses the page settings.
	 * 
	 * @see  PageUtils#findAllowRobots(javax.servlet.ServletContext, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, com.semanticcms.core.model.Page)
	 */
	@Override
	public boolean getAllowRobots(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, Page page) throws ServletException, IOException {
		return PageUtils.findAllowRobots(servletContext, request, response, page);
	}

	@Override
	public void configureResources(ServletContext servletContext, HttpServletRequest req, HttpServletResponse resp, Theme theme, Page page, Registry requestRegistry) {
		super.configureResources(servletContext, req, resp, theme, page, requestRegistry);
		// TODO: Add and activate the page-scope registry from the page that will be written
	}

	@Override
	public void doView(ServletContext servletContext, HttpServletRequest request, HttpServletResponse response, Html html, Page page) throws ServletException, IOException, SkipPageException {
		Dispatcher.include(
			servletContext,
			JSPX_TARGET,
			request,
			response,
			Collections.singletonMap("page", page)
		);
	}
}
