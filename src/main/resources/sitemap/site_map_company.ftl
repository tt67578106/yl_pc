<?xml version="1.0" encoding="UTF-8"?>
<urlset>
	<#list companyIdList as company>
		<url>
		<loc>http://${company?split("_")[1]}.${domainName}/qiye_${company?split("_")[0]}.html</loc>
		<priority>1.00</priority>
		<lastmod></lastmod>
		<changefreq>daily</changefreq>
		</url>
		
		<url>
		<loc>http://${company?split("_")[1]}.${domainName}/company/job_${company?split("_")[0]}/</loc>
		<priority>1.00</priority>
		<lastmod></lastmod>
		<changefreq>daily</changefreq>
		</url>
		
		<url>
		<loc>http://${company?split("_")[1]}.${domainName}/company/album_${company?split("_")[0]}/</loc>
		<priority>1.00</priority>
		<lastmod></lastmod>
		<changefreq>daily</changefreq>
		</url>
		
	</#list>
</urlset>
