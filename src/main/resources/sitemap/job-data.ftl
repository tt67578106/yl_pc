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
		<!-- data最少出现1次 最多出现1次 -->
			<display>
			<!-- display，，最少出现1次 最多出现1次 -->
				<sourceType1>生活</sourceType1>
				<!-- sourceType1: 一级分类，资源的一级分类，不可修改，最少出现1次 最多出现1次，类型为字符串，有效值为：生活 -->
				<sourceType2>招聘</sourceType2>
				<!-- sourceType2: 二级分类，资源二级分类，不可修改，最少出现1次 最多出现1次，类型为字符串，有效值为：招聘 -->
				<title>${job.title!""}</title>
				<!-- title: 职位名称，一般情况下只需填写职位名称即可，不可包含网站地址，最少出现1次 最多出现1次，类型为字符串，最小长度1个字符	最大长度100个字符 -->
				<trust>1</trust>
				<!-- trust: 是否为验证岗位，是否为验证岗位，若是，填写1；若不是，填写0，最少出现1次 最多出现1次，类型为整数，有效值为：0、1 -->
				<description><#if job.detail??><![CDATA[${job.detail.workdesc}]]></#if></description>
				<!-- description: 职位描述，内容可以为空，内容为普通文本。文本内可以换行，但不得包含字体，字号，间距等格式定义标签。，最少出现1次 最多出现1次，类型为字符串 -->
				<type>全职</type>
				<!-- type: 招聘种类，社会招聘、实习、兼职、校园招聘等，最少出现1次 最多出现1次，类型为字符串，有效值为：全职、兼职、实习、社招、校招、临时工、钟点工、暑期工、社会招聘、校园招聘、暑假工、不限 -->
				<city><#if job.city??>${job.city.cityName}</#if></city>
				<!-- city: 工作城市，工作地点，正确格式如“上海”，只能填写一个城市名称，不能填写到区，城市不要带“市”字，比如上海正确，上海市错误，最少出现1次 最多出现1次，类型为字符串，最小长度1个字符 -->
				<district><#if job.district??>${job.district.districtName}</#if></district>
				<!-- district: 工作辖区，工作辖区，正确格式如：闸北区、虹口区、海淀区等，最少出现1次 最多出现1次，类型为字符串 -->
				<employer><#if job.company??>${job.company.name}</#if></employer>
				<!-- employer: 雇主名称，公司名称 ，最少出现1次 最多出现1次，类型为字符串，最小长度1个字符	最大长度100个字符 -->
				<alias><#if job.company??>${job.company.abbreviation}</#if></alias>
				<!-- alias: 雇主别名，公司简称/别名，最少出现0次 最多出现1次，类型为字符串，最大长度256个字符 -->
				<email><#if job.company??>${job.company.email!""}</#if></email>
				<!-- email: 公司联系Email，，最少出现0次 最多出现1次，类型为字符串 -->
				<jobfirstclass><#if job.jobType??>${job.jobType}</#if></jobfirstclass>
				<!-- jobfirstclass: 职位大类，请严格按照给定的类别表选取，最少出现1次 最多出现1次，类型为字符串，最小长度1个字符 -->
				<jobsecondclass><#if job.jobType??>${job.jobType}</#if></jobsecondclass>
				<!-- jobsecondclass: 职位小类，，最少出现1次 最多出现1次，类型为字符串，最小长度1个字符 -->
				<firstindustry>贸易</firstindustry>
				<!-- firstindustry: 行业大类，如：贸易|批发|零售|租赁业，最少出现1次 最多出现1次，类型为字符串，最小长度1个字符 -->
				<secondindustry>贸易</secondindustry>
				<!-- secondindustry: 行业小类，，最少出现1次 最多出现1次，类型为字符串，最小长度1个字符 -->
				<education>本科</education><!-- <#if job.detail??>${job.detail.education}</#if> -->
				<!-- education: 学历要求，如“大专”，“本科”等。若无要求请填写“不限”不得为空。，最少出现1次 最多出现1次，类型为字符串，有效值为：初中、高中、中技、中专、大专、本科、硕士、MBA、EMBA、博士、不限 -->
				<experience>不限</experience>
				<!-- experience: 工作经验要求，如“3-5年”，“2年以上”。若无要求请填写“不限”不得为空，最少出现1次 最多出现1次，类型为字符串 -->
				<startdate>${job.createtime?string("yyyy-MM-dd")}</startdate>
				<!-- startdate: 职位信息发布日期，必须与页面上的日期保持一致。日期格式：yyyy-mm-dd，最少出现1次 最多出现1次，类型为日期，格式为YYYY-MM-DD -->
				<enddate>${job.updatetime?string("yyyy-MM-dd")}</enddate>
				<!-- enddate: 职位信息截止日期，，最少出现1次 最多出现1次，类型为日期，格式为YYYY-MM-DD -->
				<salary>${job.salaryfrom!""}-${job.salaryto!""}元</salary>
				<!-- salary: 工资，仅可为以下3种格式 1）“面议” 2）“aaaa-bbbb元”  3）“aaaa元” 4）“aaa以上”或“aaa以下”。不得为空。不要加单位，如“元/月”，错误！，最少出现1次 最多出现1次，类型为字符串，最小长度1个字符 -->
				<employertype>私营</employertype>
				<!-- employertype: 公司性质，如“股份制”，“私营”等，最少出现1次 最多出现1次，类型为字符串 -->
				<provider>优蓝网</provider>
				<!-- provider: 网站名称，，最少出现1次 最多出现1次，类型为字符串，最小长度1个字符 -->
				<bags>1</bags>
				<!-- bags: 包吃住，是否包吃住，包写1，不包写0，最少出现0次 最多出现1次，类型为整数，有效值为：0、1 -->
				<insurance>1</insurance>
				<!-- insurance: 五险一金，是否提供五险一金，提供写1，不提供写0，最少出现0次 最多出现1次，类型为整数，有效值为：0、1 -->
			</display>
		</data>
	</url>
</#list>
</urlset>
