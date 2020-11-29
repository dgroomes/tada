/**
 * This file is the entrypoint of the Electron application. By contrast, "app.js" is the primary source of JavaScript
 * for the web page itself.
 */

const {app, BrowserWindow} = require('electron')
const path = require('path')

/*
 * Register a listener to create the browser window when Electron is "ready"
 */
app.whenReady().then(() => {
    let messagePassingScript = path.join(__dirname, 'message-passing.js');

    let options = {
        webPreferences: {
            preload: messagePassingScript,
            contextIsolation: true
        }
    };

    let window = new BrowserWindow(options);

    window.loadFile('index.html')
        .then(_void => {
            console.log("The page was loaded!");
        }).catch(error => {
        console.error(`The page failed to load. ${error}`);
    });
});

app.on('window-all-closed', function () {
    app.quit();
});
