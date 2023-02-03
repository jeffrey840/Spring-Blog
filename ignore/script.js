
// endpoints

//testing an endpoint
const options = {
	method: 'GET',
	headers: {
		'X-RapidAPI-Key': '',
		'X-RapidAPI-Host': 'zillow-com1.p.rapidapi.com'
	}
};

fetch('https://zillow-com1.p.rapidapi.com/propertyExtendedSearch?location=santa%20monica%2C%20ca&home_type=Houses', options)
	.then(response => response.json())
	.then(response => console.log(response))
	.catch(err => console.error(err));