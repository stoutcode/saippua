
let accesstoken = null;
let accountId = null;

function connect() {
	doAjax('Connect_servlet', "", 'connectCallback', 'post', 0);
	console.log("connecting...");
	
}

function connectCallback(result) {
	window.location = result;
	
}

function getToken() {
	var code = new URLSearchParams(window.location.search).get('code');
	
	doAjax('Token_servlet', 'code='+code, 'getTokenCallback', 'post', 0);
	
}

function getTokenCallback(result) {
	console.log(result);
	
	document.getElementById('results').innerHTML = result;
	
	try {
		parsed = JSON.parse("" + result);
		accesstoken = parsed['access_token'];
		accountId = parsed['account_id'];
	} catch(error) {
		console.log(error);
	}
	
}

function accountInfo() {
	console.log(accesstoken);
	console.log(accountId);
	
	query_str = 'token='+accesstoken+'&accountid=' + accountId;
	
	doAjax('Accountinfo_servlet', query_str, 'accountInfoCallback', 'post', 0);
	
}

function accountInfoCallback(result) {
	console.log(result);
	
	document.getElementById('results').innerHTML = result;

}

function upload() {
	console.log("uploading...");
}