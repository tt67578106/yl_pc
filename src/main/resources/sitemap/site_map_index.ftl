<?xml version="1.0" encoding="UTF-8"?>

<sitemapindex>
	<sitemap>
       <loc>http://www.${domainName}/sitemap/city.xml</loc>
       <lastmod></lastmod>
    </sitemap>
    
    <#list companyIndustryids as industryid>
    <sitemap>
       <loc>http://www.${domainName}/sitemap/firmlist${industryid}.xml</loc>
       <lastmod></lastmod>
    </sitemap>
    </#list>
    
    <#list jobTypeIdList as typeId >
    <sitemap>
       <loc>http://www.${domainName}/sitemap/joblistbytype${typeId}.xml</loc>
       <lastmod></lastmod>
    </sitemap>
	</#list>
	
	<#list jobTageIdList as tagId >
    <sitemap>
       <loc>http://www.${domainName}/sitemap/joblistbylabel${tagId}.xml</loc>
       <lastmod></lastmod>
    </sitemap>
	</#list>
	
	<#list 1..companyPageCount as i>
	<sitemap>
       <loc>http://www.${domainName}/sitemap/firm${i}.xml</loc>
       <lastmod></lastmod>
    </sitemap>
    </#list>
    	
	<#list 1..jobPageCount as j>
	<sitemap>
       <loc>http://www.${domainName}/sitemap/job${j}.xml</loc>
       <lastmod></lastmod>
    </sitemap>
	</#list>
	
	<#list 1..articlePageCount as k>
	<sitemap>
       <loc>http://www.${domainName}/sitemap/news${k}.xml</loc>
       <lastmod></lastmod>
    </sitemap>
	</#list>
	
	<#list 1..wendalistPageCount as l>
	<sitemap>
       <loc>http://www.${domainName}/sitemap/wendalist${l}.xml</loc>
       <lastmod></lastmod>
    </sitemap>
	</#list>
	
	<#list 1..wendaPageCount as m>
	<sitemap>
       <loc>http://www.${domainName}/sitemap/wenda${m}.xml</loc>
       <lastmod></lastmod>
    </sitemap>
	</#list>
	
	
</sitemapindex>
