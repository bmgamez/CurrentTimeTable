function getDownloadPage(day,callback) {

    var settings = require("./resources/settings.json");

    var request = require('request');

    request({
        uri: "https://schulinternes.de/dato40/server/hp-lazy-auth.php?data={\"schule\":\"" + settings.school + "\",\"user\":\"" + settings.username + "\",\"passwd\":\"" + settings.password + "\"}",
    }, function (error, response, body) {
        
        request({
            uri: "https://schulinternes.de/dato40/server/hp-vertretungen-s.php?data=" + body,
        }, function (error, response, body) {
            
            var cheerio = require('cheerio');
            var $ = cheerio.load(body);
        
            var links = []
        
            $($('a')).each(function (i, link) {
                current = $(link).attr('href');
                current = current.replace("./hp-show.php?data=","");
                current = "https://schulinternes.de/dato40/server/hp-show.php?data=" + current;

                links.push(current);
            });


            var d=new Date();
            if(d.getDay() > 5 && d.getHours() > 9) {
                if (day == 0) {
                    callback(links[0]);
                }
                else if (day == 1){
                    callback (links[1]);
                }
            }
            else {
                callback(links[day]);
            }
        });
    });

}

getDownloadPage(1,(url) =>{
    console.log(url)
});