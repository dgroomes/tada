/*
 * Detects versions of Chromium, Node, and Electron and shares those values via the "contextBridge" API to the renderer
 * process/Chromium. Read about the "contextBridge" at https://www.electronjs.org/docs/tutorial/context-isolation and
 * https://www.electronjs.org/docs/api/context-bridge
 *
 * This JavaScript code will be run as a "preload" script. For more information about how "preload" scripts work see the
 * documentation for BrowserWindow and specifically scroll down to the "webPreferences" option: https://www.electronjs.org/docs/api/browser-window
 */

const {contextBridge} = require('electron')

contextBridge.exposeInMainWorld("myData", {
    chromeVersion: process.versions['chrome'],
    nodeVersion: process.versions['node'],
    electronVersion: process.versions['electron']
});