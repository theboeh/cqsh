/*
 * Copyright 2005 Andrew Bruno <aeb@qnot.org> 
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at 
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.marklogic.shell;

import com.marklogic.xqrunner.XQDataSource;
import com.marklogic.xqrunner.XQException;

public class version implements Command {

	public String getName() {
		return "version";
	}

	public String getHelp() {
		StringBuffer help = new StringBuffer();
		help.append("usage: version" + Environment.NEWLINE);
		help.append("Displays Mark Logic version information." + Environment.NEWLINE);
		return help.toString();
	}

	public void execute(Environment env, String options) {
		try {
	        String query = "string-join((xdmp:product-name(),\"-\","
				            +"xdmp:product-initials(),"
				            +"xdmp:product-edition(),"
				            +"xdmp:version(),"
				            +"concat(\"(\",xdmp:platform(), \")\")), \" \")";
			XQDataSource dataSource = env.getDataSource();
			env.runXQuery(dataSource.newQuery(query));
		} catch(XQException e) {
			env.printError(e);
		}
	}
}
