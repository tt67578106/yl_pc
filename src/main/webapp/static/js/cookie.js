var Cookie={
	// 保存cookie cName:cookie名,cValue:cookie值,cDays:保存期限(天数)
	setCookie : function(cName, cValue, cDays) {
		var date = new Date();
		date.setTime(date.getTime() + cDays * 24 * 3600 * 1000);
		var cookieStr = escape(cName) + "=" + escape(cValue) + "; expires="
				+ date.toGMTString() + ";path=/";
		document.cookie = cookieStr;
	},
	// 根据cookie名取得其值
	getCookie : function(cName) {
		var cookieStr = document.cookie;
		var cookies = cookieStr.split("; ");
		for (var i = 0; i < cookies.length; i++) {
			var cookie = cookies[i];
			var theCookie = cookie.split("=");
			var theValue = unescape(theCookie["1"]);
			if (theValue == "undefined" || theValue == null) {
				theValue = "";
			}
			if (theCookie["0"] == escape(cName)) {
				return theValue;
				break;
			}
		}
		return "";
	},
	// 根据cookie名删除
	delCookie : function(cName) {
		var date = new Date();
		date.setTime(date.getTime() - 1000);
		var cookieStr = escape(cName) + "=; expires=" + date.toGMTString()
				+ ";";
		document.cookie = cookieStr;
	}
}
