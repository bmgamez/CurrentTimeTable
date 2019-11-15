const http = require('http');
const fs = require('fs');

const file = fs.createWriteStream("data/tmp/plan.pdf");
const request = http.get("http://www.schulinternes.de/dato40/hp-show.php?schule=68766cfg&tag=mi&key=", function(response) {
  response.pipe(file);
});