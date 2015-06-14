
function forward() {
  Demo.getInclude(function(data) {
    dwr.util.setValue("forward", data, { escapeHtml:false });
  });
  Demo.getIncludeJsp(function(data) {
	  dwr.util.setValue("forward2", data, { escapeHtml:false });
  });
}
