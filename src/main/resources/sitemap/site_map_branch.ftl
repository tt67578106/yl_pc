<?xml version="1.0" encoding="UTF-8"?>
<urlset>
	<#list branchList as webPrefix>
		<url>
		<loc>http://${webPrefix}.${domainName}</loc>
		<priority>1.00</priority>
		<lastmod></lastmod>
		<changefreq>monthly</changefreq>
		</url>
		
		<url>
		<loc>http://${webPrefix}.${domainName}/mingqi/</loc>
		<priority>1.00</priority>
		<lastmod></lastmod>
		<changefreq>monthly</changefreq>
		</url>
		
		<url>
		<loc>http://${webPrefix}.${domainName}/zhaopin/</loc>
		<priority>1.00</priority>
		<lastmod></lastmod>
		<changefreq>monthly</changefreq>
		</url>
		
	</#list>
	
	<url>
	<loc>http://www.${domainName}/zone</loc>
	<priority>1.00</priority>
	<lastmod></lastmod>
	<changefreq>monthly</changefreq>
	</url>
	
	<url>
	<loc>http://www.${domainName}/wenda</loc>
	<priority>1.00</priority>
	<lastmod></lastmod>
	<changefreq>monthly</changefreq>
	</url>
	
	<#list 1..6 as m>
		<url>
		<loc>http://www.${domainName}/s_articleType_${m}</loc>
		<priority>1.00</priority>
		<lastmod></lastmod>
		<changefreq>monthly</changefreq>
		</url>
	</#list>
</urlset>
