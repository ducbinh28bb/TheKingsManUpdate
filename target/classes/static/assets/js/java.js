function search() {
	var number = document.getElementById("keyword").value;
	window.location.assign("/kingsman/account/history/search?keyword" + "=" + number);
}