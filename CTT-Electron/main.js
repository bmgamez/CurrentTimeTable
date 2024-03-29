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

    if (process.env.NODE_ENV=="developement") {
        win.webContents.openDevTools({ mode: 'detach' });
    }
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

    const spawn = require('child_process').spawn;
    const ls = spawn('java', ['-jar', dirname + '/CTT-Java.jar']);

    ls.stdout.on('data', (data) => {
        subjects.push(data)
        console.log(`stdout: ${data}`);
    });

    ls.stderr.on('data', (data) => {
        console.error(`stderr: ${data}`);
    });

    ls.on('close', (code) => {
        event.sender.send("fromMain", true)
    });
}