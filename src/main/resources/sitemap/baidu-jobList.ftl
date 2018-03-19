<?xml version="1.0" encoding="UTF-8"?>
<urlset>
<!-- urlset，urlset用来标记整个文档的开头，最少出现1次 最多出现1次 -->
<#list jobList as job>
	<url>
	<!-- url，url标记每条信息的开始和结束，最少出现0次 最多出现50000次 -->
		<loc>http://m.youlanw.com</loc>
		<!-- loc，网站职位落地页，填写手机站点的地址，此条资源的唯一标识，最少出现1次 最多出现1次，类型为URL地址，最小长度1个字符	最大长度256个字符	必须符合正则表达式(http://)(.+) -->
		<lastmod>${job.updatetimeString}</lastmod>
		<!-- lastmod，指该条数据的最新一次更新时间，格式为UNIX时间戳，即从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数，不考虑闰秒。，最少出现0次 最多出现1次，类型为整数 -->
		<changefreq>always</changefreq>
		<!-- changefreq，指该条数据的更新频率，最少出现0次 最多出现1次，类型为字符串，有效值为：always、hourly、daily、weekly、monthly、yearly、never -->
		<priority>1.0</priority>
		<!-- priority，用来指定此链接相对于其他链接的优先权比值，此值定于0.0-1.0之间，最少出现0次 最多出现1次，类型为小数，最小值为（包含）0.0	最大值为（包含）1.0 -->
		<data>
		<!-- data，，最少出现1次 最多出现1次 -->
			<display>
			<!-- display，，最少出现1次 最多出现1次 -->
				<wapurl>http://m.youlanw.com/job/detail/<![CDATA[${job.id}]]>.html/</wapurl>
				<!-- sourceType2: 二级分类，资源二级分类，不可修改，最少出现1次 最多出现1次，类型为字符串，有效值为：招聘 -->
				<title>${job.title!""}</title>
				<!-- title: 职位名称，一般情况下只需填写职位名称即可，不可包含网站地址，最少出现1次 最多出现1次，类型为字符串，最小长度1个字符	最大长度100个字符 -->
				<depart></depart>
				<number></number>
				<age><#if job.detail??>${job.detail.agefrom}</#if>-<#if job.detail??>${job.detail.ageto}</#if></age>
				<sex><#if job.detail?? && job.detail.gender == 1>男</#if><#if job.detail?? && job.detail.gender == 2>女</#if></sex>
				<!-- trust: 是否为验证岗位，是否为验证岗位，若是，填写1；若不是，填写0，最少出现1次 最多出现1次，类型为整数，有效值为：0、1 -->
				<description><![CDATA[<#if job.detail??>${job.detail.workdesc}</#if>]]></description>
				<!-- description: 职位描述，内容可以为空，内容为普通文本。文本内可以换行，但不得包含字体，字号，间距等格式定义标签。，最少出现1次 最多出现1次，类型为字符串 -->
				<education>本科</education><!-- <#if job.detail??>${job.detail.education}</#if> -->
				<!-- education: 学历要求，如“大专”，“本科”等。若无要求请填写“不限”不得为空。，最少出现1次 最多出现1次，类型为字符串，有效值为：初中、高中、中技、中专、大专、本科、硕士、MBA、EMBA、博士、不限 -->
				<experience></experience>
				<!-- experience: 工作经验，可填区间为1年以上，3年以上，5年以上，10年以上；若无要求请填写“不限”。?，最少出现1次 最多出现1次，类型为字符串 -->
				<startdate>${job.createtime?string("yyyy-MM-dd")}</startdate>
				<!-- startdate: 发布日期，必选，内容不为空；填写职位信息发布日期；必须与站内职位内容页面上显示的日期保持一致；日期格式：yyyy-mm-dd，最少出现1次 最多出现1次，类型为日期，格式为YYYY-MM-DD -->
				<enddate>${job.updatetime?string("yyyy-MM-dd")}</enddate>
				<!-- enddate: 截止日期，必选，内容不为空；填写职位信息截止日期；必须与站内职位内容页面上显示的日期保持一致；日期格式：yyyy-mm-dd，最少出现1次 最多出现1次，类型为日期，格式为YYYY-MM-DD -->
				<salary>${job.salaryfrom!""}-${job.salaryto!""}元</salary>
				<!-- salary: 薪资，面议；1000元以下；1000-2000；2000-3000；3000-5000；5000-8000；8000-12000；12000-20000；20000-25000；25000以上，最少出现1次 最多出现1次，类型为字符串 -->
				<city><#if job.city??>${job.city.cityName}</#if></city>
				<!-- city: 城市，工作城市，必选，内容不为空；正确格式如：北京、北京市等；可支持填写多个城市，每个城市以“|”“/”“\”分隔，最少出现1次 最多出现1次，类型为字符串 -->
				<district><#if job.district??>${job.district.districtName}</#if></district>
				<!-- district: 工作区县，必选，内容不为空；填写区县，正确格式如：朝阳区、延庆县等；可支持填写多个区县，每个区县以“|”“/”“\”分隔，最少出现1次 最多出现1次，类型为字符串 -->
				<location><#if job.district??>${job.district.districtName}</#if></location>
				<!-- location: 工作地址，工作地址，必选，内容不为空，最少出现1次 最多出现1次，类型为字符串 -->
				<type>全职</type>
				<!-- type: 招聘种类，社会招聘、实习、兼职、校园招聘等，最少出现1次 最多出现1次，类型为字符串，有效值为：全职、兼职、实习、社招、校招、临时工、钟点工、暑期工、社会招聘、校园招聘、暑假工、不限 -->
				<officialname><#if job.company??>${job.company.name}</#if></officialname>
				<!-- officialname: 营业执照名称，营业执照名称，必选，内容不为空；填写公司营业执照名称，如：北京百度网讯科技有限公司，最少出现1次 最多出现1次，类型为字符串 -->
				<commonname><#if job.company??>${job.company.abbreviation}</#if></commonname>
				<!-- commonname: 公司俗称，必选，内容可以为空，填写公司俗称，如：百度，阿里巴巴等，最少出现1次 最多出现1次，类型为字符串 -->
				<logo></logo>
				<employerurl></employerurl>
				<companyaddress><#if job.company??>${job.company.address}</#if></companyaddress>
				<!-- companyaddress: 公司地址，必选，内容不能为空，填写公司地址，最少出现1次 最多出现1次，类型为字符串 -->
				<employertype>民营</employertype>
				<size></size>
				<welfare></welfare>
				<companydescription><#if job.company??><![CDATA[${job.company.introduction!""}]]></#if></companydescription>
				<email><#if job.company??>${job.company.email!""}</#if></email>
				<industry><#if job.jobType??>${job.jobType}</#if></industry>
				<secondindustry><#if job.jobType??>${job.jobType}</#if></secondindustry>
				<companyID><#if job.company??>${job.company.id}</#if></companyID>
				<specialtips>无</specialtips>
				<source>优蓝网</source>
				<sourcelink>http://www.youlanw.com</sourcelink>
				<joblink>http://www.youlanw.com/zhaopin_${job.id}.html</joblink>
				<jobwapurl>http://m.youlanw.com/zhaopin_${job.id}.html</jobwapurl>
			</display>
		</data>
	</url>
</#list>
</urlset>
