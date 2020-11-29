/**
 * This file is the primary source of JavaScript for the web page (i.e. the renderer process and Chromium). By contrast,
 * "index.js" is the entrypoint for the Electron application's "Main" process which executes in the Node.js runtime
 * environment.
 */

/**
 * When the DOM is loaded then paint the environmental version information.
 */
window.addEventListener('DOMContentLoaded', () => {
    {
        let element = document.getElementById(`chrome-version`);
        let version = window.myData.chromeVersion;
        element.innerText = `Version: ${version}`;
    }

    {
        let element = document.getElementById(`node-version`);
        let version = window.myData.nodeVersion;
        element.innerText = `Version: ${version}`;
    }

    {
        let element = document.getElementById(`electron-version`);
        let version = window.myData.electronVersion;
        element.innerText = `Version: ${version}`;
    }
});