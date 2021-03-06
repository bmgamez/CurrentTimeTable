    const ipcRenderer = require('electron').ipcRenderer

    var loadTodayBtn = document.getElementById('loadToday')
    var loadTomorrowBtn = document.getElementById('loadTomorrow')
    var openPlanBtn = document.getElementById('openPlan')
    var openStatsBtn = document.getElementById('openStats')
    var openSettingsBtn = document.getElementById('openSettings')
    var table = document.getElementById('main-table')

    loadTodayBtn.addEventListener('click', () => {
        console.log("loading Today")
    });

    loadTomorrowBtn.addEventListener('click', () => {
        console.log("loading Tomorrow")
    });

    openPlanBtn.addEventListener('click', () => {
        console.log("opening Plan")
        table.innerHTML = ""
        ipcRenderer.send('open:window:plan')
    });

    openStatsBtn.addEventListener('click', () => {
        console.log("opening Stats")
        ipcRenderer.send('open:window:stats')
    });

    openSettingsBtn.addEventListener('click', () => {
        console.log("opening Settings")
        ipcRenderer.send('open:window:settings')
    });

    ipcRenderer.on('fromMain', (event, messages) => {
        
    });

    table.innerHTML = ""
    ipcRenderer.send('open:window:plan')