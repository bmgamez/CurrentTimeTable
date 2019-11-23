const {
    app,
    BrowserWindow
} = require('electron')
const ipcMain = require('electron').ipcMain

let win
let winStats
let winSettings

function createWindow() {

    win = new BrowserWindow({
        width: 600,
        height: 800,
        minWidth: 400,
        minHeight: 800,
        webPreferences: {
            nodeIntegration: true
        },
        autoHideMenuBar: true
    })

    win.loadFile('views/index.html')

    win.on('closed', () => {
        win = null
    })
}

function createWindowStats() {

    winStats = new BrowserWindow({
        width: 400,
        height: 400,
        minWidth: 400,
        minHeight: 400,
        webPreferences: {
            nodeIntegration: true
        },
        autoHideMenuBar: true
    })

    winStats.loadFile('views/stats.html')

    winStats.on('closed', () => {
        win = null
    })
}

function createWindowSetings() {

    winSettings = new BrowserWindow({
        width: 400,
        height: 400,
        minWidth: 400,
        minHeight: 400,
        webPreferences: {
            nodeIntegration: true
        },
        autoHideMenuBar: true
    })

    winSettings.loadFile('views/settings.html')

    winSettings.on('closed', () => {
        win = null
    })
}

app.on('ready', createWindow)

app.on('window-all-closed', () => {

    if (process.platform !== 'darwin') {
        app.quit()
    }
})

app.on('activate', () => {

    if (win === null) {
        createWindow()
    }
})

ipcMain.on('open:window:plan', (event, args) => {
    console.log("opening Plan")
    loadPlan(event)
})

ipcMain.on('open:window:stats', (event, args) => {
    console.log("opening Stats")
    createWindowStats()
})

ipcMain.on('open:window:settings', (event, args) => {
    console.log("opening Settings")
    createWindowSetings()
})

function loadPlan(event) {

    var exec = require('child_process').exec;

    var dirname = __dirname.replace(/\\/g, '/')
    var call = "cd " + dirname + " &&" + " java -jar CTT-Java.jar"
    console.log(call)

    exec(call, function callback(error, stdout, stderr) {
        var output = stdout
        var finalString = "<tr><th>Stunde</th><th>Fach</th></tr>"
        console.log(output)
        if (process.platform == "darwin") {
            var subjects = stdout.split("\n")
        } else {
            var subjects = stdout.split("\r\n")
        }
        //console.log(subjects)
        var i = 1;
        for (var subject in subjects) {
            //if (subjects[i-1] == " " || subjects[i-1] == "") {
            //    break;
            //}
            var String = "<tr><th>" + i + "</th> <th>" + subjects[i - 1] + "</th> </tr>"
            finalString += String;
            i++
        }
        console.log(finalString)
        event.sender.send("fromMain", finalString)
        return finalString;
    });
}