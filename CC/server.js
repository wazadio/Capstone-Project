const express = require('express');
const fs = require('fs')
const updatedata = require('update-json-file')

const app = express();
app.use(express.json())




let buses = {
	'bus1': '0',
	'bus2': '0'
}
/*
//sign up route
app.get('/signup', (req, res) => {
	let username = req.body.username
	let password = req.body.password
	  
	// Storing the JSON format data in myObject
	var data = fs.readFileSync("user.json");
	var myObject = JSON.parse(data);
	  
	// Adding the new data to our object
	myObject[username] = [password, false];
	  
	// Writing to our JSON file
	var newData2 = JSON.stringify(myObject);
	fs.writeFile("user.json", newData2, (err) => {
	  // Error checking
	  if (err) throw err;
	  console.log("New data added");
	});
	var data = fs.readFileSync("user.json");
	console.log(data)

  return res.send("ok");
});

app.get('/signin', (req, res) => {
	//read the database
	let data = JSON.parse(fs.readFileSync('user.json'));
	let user = req.body.username
	let pass = req.body.password
	try{
		let password = data[user][0]
		if (pass == password){
		
			const jsonfile = './user.json'
			updatedata (jsonfile, (data2) => {
				data2[user][1] = true
				return data2
			})

			let data = JSON.parse(fs.readFileSync('user.json'));
			console.log(data)

			return res.send("ok");
		}
		else{
			res.send('invalid accout')
		}
	}

	catch{
		return res.send('invalid account')
	}


});
*/

app.get('/insidebus', (req, res) => {
	//let username = req.body.username
	//let busid = req.body.busid
	res.json({
		"busId":"tj01",
		"koridor":"Blok M - Masjid Agung",
		"photo":"https://indonesia.go.id/assets/upload/headline//1561012526_2366356759.jpeg",
		"availseat":3,"passanger":18
	})
});


app.post('/report', (req, res) => {
	
	let people = req.body.people
	let busid = req.body.busid

	console.log(people)
	console.log(busid)

	buses[busid] = people

	console.log(buses)

	res.send('ok')
});


app.listen(3000, () =>
  console.log(`Example app listening on port 3000`),
);
