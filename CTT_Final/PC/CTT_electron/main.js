const {app,BrowserWindow} = require('electron')
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
        }
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
        }
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
        }
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
})

ipcMain.on('open:window:stats', (event, args) => {
    console.log("opening Stats")
    createWindowStats()
})

ipcMain.on('open:window:settings', (event, args) => {
    console.log("opening Settings")
    createWindowSetings()
})