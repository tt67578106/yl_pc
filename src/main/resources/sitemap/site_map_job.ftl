<?xml version="1.0" encoding="UTF-8"?>
<urlset>
	<#list jobIdList as job>
		<url>
		<loc>http://${job?split("_")[1]}.${domainName}/zhaopin_${job?split("_")[0]}.html</loc>
		<priority>1.00</priority>
		<lastmod></lastmod>
		<changefreq>daily</changefreq>
		</url>
	</#list>
</urlset>
