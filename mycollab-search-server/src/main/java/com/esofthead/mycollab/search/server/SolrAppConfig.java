/**
 * This file is part of mycollab-search-server.
 *
 * mycollab-search-server is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-search-server is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-search-server.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.search.server;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.3.2
 *
 */
@Configuration
@EnableSolrRepositories(basePackages={"com.acme.solr"}, multicoreSupport=true)
public class SolrAppConfig {

	@Bean
	public SolrServer solrServer() {
		return new HttpSolrServer("http://localhost:8979/solr");
	}
}
