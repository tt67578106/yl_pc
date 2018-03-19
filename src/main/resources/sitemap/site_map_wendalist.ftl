<?xml version="1.0" encoding="UTF-8"?>
<urlset>
	<#list communityCompanyIdList as ccompanyId>
		<url>
		<loc>http://www.${domainName}/wenda/so_${ccompanyId?c}_0_0_1/</loc>
		<priority>1.00</priority>
		<lastmod></lastmod>
		<changefreq>daily</changefreq>
		</url>
	</#list>
	
	<#list jobCompanyIdList as jcompanyId>
		<url>
		<loc>http://www.${domainName}/wenda/so_${jcompanyId?c}_0_0_1/</loc>
		<priority>1.00</priority>
		<lastmod></lastmod>
		<changefreq>daily</changefreq>
		</url>
	</#list>
</urlset>
