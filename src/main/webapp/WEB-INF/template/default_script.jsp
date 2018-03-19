<%@ page contentType="text/html;charset=UTF-8"%>
<script type="text/javascript">
if (/(iPhone|iPad|iPod|iOS)/i.test(navigator.userAgent)||/(Android)/i.test(navigator.userAgent)) {
	window.location.href = "http://m.youlanw.com/?jumper="	+ window.location.pathname;
}

var _vds = _vds || [];
window._vds = _vds;
(function(){
  _vds.push(['setAccountId', '844010516d2d97c9']);
  _vds.push(['setCS1', 'user_id', '${sessionScope.session_user_key.id}']);
  _vds.push(['setCS3', 'user_name', '${sessionScope.session_user_key.username}']);
  (function() {
    var vds = document.createElement('script');
    vds.type='text/javascript';
    vds.async = true;
    vds.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'dn-growing.qbox.me/vds.js';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(vds, s);
  })();
})();
</script>