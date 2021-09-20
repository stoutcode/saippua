
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
	document.getElementById('results').innerHTML = result;
	
	try {
		parsed = JSON.parse("" + result);
		accesstoken = parsed['access_token'];
		accountId = parsed['account_id'];
		
		
		str = "<br> Found token: <br>";
		str += "<br> Uid: " + parsed['uid'];
		str += "<br> Token type: " + parsed['token_type'];
		str += "<br> Token: " + parsed['access_token'];
		str += "<br> Expires in: " + parsed['expires_in'];
		str += "<br> Scope" + parsed['scope'];
		
		document.getElementById('results').innerHTML = str;
		

	} catch(error) {
		console.log(error);
	}
	
}

function accountInfo() {
	query_str = 'token='+accesstoken+'&accountid=' + accountId;
	
	doAjax('Accountinfo_servlet', query_str, 'accountInfoCallback', 'post', 0);
	
}

function accountInfoCallback(result) {
	document.getElementById('results').innerHTML = result
	;
	str = ""
	try {
		parsed = JSON.parse("" + result);
		
		str = "<br> Account info: <br>";
		str += "<br> ID: " + parsed['account_id'];
		str += "<br> Name: " + parsed['name']['display_name'];
		str += "<br> Email: " + parsed['email']
		
		document.getElementById('results').innerHTML = str;
		
	} catch(error) {
		console.log(error);
	}
	
	
	
	

}

function upload() {
	query_str = 'token='+accesstoken+'&path=/home/stout/Documents/Code/saippua/demo2/task2/DropBoxClient/psyduck.png';

	doAjax('Upload_servlet', query_str, 'uploadCallback', 'post', 0);

}

function uploadCallback(result) {
	document.getElementById('results').innerHTML = result;

}


function accountStorage() {
	query_str = 'token='+accesstoken;
	
	doAjax('Storage_servlet', query_str, 'accountStorageCallback', 'post', 0);
	
}

function accountStorageCallback(result) {
	document.getElementById('results').innerHTML = result;
	
	str = ""
	try {
		parsed = JSON.parse("" + result);
		
		str = "<br> Account storage: <br>";
		str += "<br> Used: " + parsed['used'] + " bytes";
		str += "<br> Allocated: " + parsed['allocation']['allocated'] + " bytes";
		
		document.getElementById('results').innerHTML = str;
	} catch(error) {
		console.log(error);
	}
	
	
	
}

function makehtml(arr) {
	
	var ret = "";
	for (var obj in arr) {
		ret += "<br>" + obj + ": '" + arr[obj] + "'";
	}
	
	return ret
}


