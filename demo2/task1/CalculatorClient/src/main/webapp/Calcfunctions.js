function calculate() {
	var num1 = document.getElementById('num1').value;
	var num2 = document.getElementById('num2').value;
	
	var select = document.getElementById('operators');
	var operator = select.options[select.selectedIndex].value;
	
	// confirm valid operator picked
	if ( !(operator == "add" || operator == "subtract" || operator == "multiply" || operator == "divide") ) {
		document.getElementById('answer').innerHTML = "error: select one of the operations from selection box..";
		return;
	}
	
	var query_string = 'type=calculation&num1='+num1+'&num2='+num2+'&operator='+operator;
	
	// http request to servlet
	doAjax('Calculator_servlet', query_string, 'calculateCallback', 'post', 0);
}

function calculateCallback(result) {
	
	// if result contains 'error' its not a number
	if (result.includes("error")) {
		document.getElementById('answer').innerHTML = result;
	} else if ( isNaN(Math.sign(parseInt(result))) ) {
		document.getElementById('answer').innerHTML = "error: result not a number..";
	} else if (Math.sign(parseInt(result)) < 0) {
		document.getElementById('answer').innerHTML = "error: cant express negative numbers as words..";
	} else {
		// no use for num2 or operator but lets use same querystring for simplicity
		var query_string = 'type=conversion&num1='+result+'&num2=0&operator=add';
		doAjax('Calculator_servlet', query_string, 'wordsCallback', 'post', 0);
	}
	
}

function wordsCallback(result) {
	// finally show resut as words
	document.getElementById('answer').innerHTML = result;
}