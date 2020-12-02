window.messagePassing.registerCallbackForMessages((instruction) => {
    console.log(`Received a UI instruction: ${JSON.stringify(instruction, null, 2)}`);
    try {
        executeUiInstruction(instruction);
    } catch (e) {
        console.error("Caught unexpected exception");
        console.error(e);
    }
});

let body;
let elIdx = 1;

const CREATE = 0
const UPDATE = 1
const DELETE = 2

window.addEventListener('DOMContentLoaded', (event) => {
    console.log('DOM fully loaded and parsed');
    body = document.getElementsByTagName("body")[0];
});

/**
 * Execute a UI instruction
 * @param instruction
 */
function executeUiInstruction(instruction) {
    let elementId = instruction.elementId;

    let type = instruction.type;
    if (type === CREATE) {
        // todo
        let el = document.createElement("div");
        el.id = elIdx;
        elIdx++;
        el.innerText = instruction.textContent;
        body.appendChild(el);
        return;
    }

    let element = document.getElementById(elementId);
    if (type === UPDATE) {
        element.innerText = instruction.textContent;
    } else if (type === DELETE) {
        element.parentElement.removeChild(element);
    }
}
