const {app,BrowserWindow} = require('electron')

let win

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